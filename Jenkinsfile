pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        JAVA_HOME = 'C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.17.10-hotspot'

        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'sakarihonkavaara/fuel-calculator'
        DOCKER_IMAGE_TAG = 'latest'
        SONAR_HOST_URL = 'http://192.168.10.51:9000'
        SONAR_PROJECT_KEY = 'sakari-fuel-calculator'
        SONAR_PROJECT_NAME = 'fuel-calculator-localization'
    }

    stages {

        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                git url: 'https://github.com/sakarihonkavaara/fuel-calculator-localization.git',
                    branch: 'master'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                bat 'if exist .scannerwork rmdir /s /q .scannerwork'
                bat 'if exist target\\sonar rmdir /s /q target\\sonar'

                withSonarQubeEnv('SonarQubeServer') {
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        bat """
                            sonar-scanner ^
                            -Dsonar.projectKey=${SONAR_PROJECT_KEY} ^
                            -Dsonar.projectName=${SONAR_PROJECT_NAME} ^
                            -Dsonar.sources=src ^
                            -Dsonar.java.binaries=target/classes ^
                            -Dsonar.java.test.binaries=target/test-classes ^
                            -Dsonar.junit.reportPaths=target/surefire-reports ^
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml ^
                            -Dsonar.host.url=${SONAR_HOST_URL} ^
                            -Dsonar.token=%SONAR_TOKEN% ^
                            -Dsonar.working.directory=.scannerwork
                        """
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    script {
                        def reportFile = '.scannerwork/report-task.txt'
                        def taskId = null

                        if (fileExists(reportFile)) {
                            def content = readFile(reportFile)
                            def lines = content.split('\n')
                            for (line in lines) {
                                if (line.startsWith('ceTaskId=')) {
                                    taskId = line.split('=')[1].trim()
                                    break
                                }
                            }
                        }

                        if (!taskId) {
                            error "Could not extract SonarQube task ID from ${reportFile}"
                        }

                        echo "SonarQube task ID: ${taskId}"

                        timeout(time: 5, unit: 'MINUTES') {
                            def status = 'PENDING'
                            while (status == 'PENDING' || status == 'IN_PROGRESS') {
                                sleep(time: 3, unit: 'SECONDS')

                                def response = bat(
                                    returnStdout: true,
                                    script: """
                                        @echo off
                                        curl -s -u %SONAR_TOKEN%: "${SONAR_HOST_URL}/api/ce/task?id=${taskId}" | findstr /C:"\\"status\\""
                                    """
                                ).trim()

                                if (response.contains('SUCCESS')) {
                                    status = 'SUCCESS'
                                } else if (response.contains('IN_PROGRESS')) {
                                    status = 'IN_PROGRESS'
                                } else if (response.contains('PENDING')) {
                                    status = 'PENDING'
                                } else if (response.contains('FAILED') || response.contains('CANCELLED')) {
                                    status = 'FAILED'
                                } else {
                                    status = 'UNKNOWN'
                                }

                                echo "Task status: ${status}"
                            }

                            if (status != 'SUCCESS') {
                                error "SonarQube task failed with status: ${status}"
                            }

                            def qgResponse = bat(
                                returnStdout: true,
                                script: """
                                    @echo off
                                    curl -s -u %SONAR_TOKEN%: "${SONAR_HOST_URL}/api/qualitygates/project_status?projectKey=${SONAR_PROJECT_KEY}" | findstr /C:"\\"projectStatus\\""
                                """
                            ).trim()

                            if (qgResponse.contains('"status":"OK"')) {
                                echo "Quality Gate PASSED"
                            } else if (qgResponse.contains('"status":"ERROR"')) {
                                error "Quality Gate FAILED"
                            } else {
                                error "Could not determine Quality Gate status"
                            }
                        }
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker logout'
                bat "docker build --no-cache -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKERHUB_CREDENTIALS_ID}",
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat """
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}