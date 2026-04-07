pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/sakarihonkavaara/fuel-calculator-localization.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

    }

    post {
        always {
            cleanWs()
        }
    }
}