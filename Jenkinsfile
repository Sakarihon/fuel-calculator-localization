pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/YOUR_USERNAME/fuel-calculator-localization.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("sakarihonkavaara/fuelcalculator:latest", ".")
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