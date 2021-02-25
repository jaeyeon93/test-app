pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Build stage'
            }
            steps {
                echo 'Build stage 2nd steps'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing stage'
                sh 'make check || true'
                junit '**/target/*.xml'
            }
        }
        stage('Deploy') {
            when {
                expression {
                    currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
            steps {
                echo 'deploy stage'
            }

        }
    }
}