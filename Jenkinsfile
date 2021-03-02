pipeline {
    agent {
        docker {
            image 'gradle:6.8.2'
        }
    }
    environment {
        NAME = 'jimmy'
        AUTH = 'hello auth'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Hello Build Stage'
                sh './gradlew build'
            }
        }
        stage('Test') {
            steps {
                echo 'Hello Testing stage'
                echo "env variable test NAME ${NAME}"
                echo "env variable test AUTH ${AUTH}"
                sh './gradlew check'
                junit '**/target/*.xml'
            }
        }
    }
    post {
            always {
                archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
                junit 'build/reports/**/*.xml'
            }
            success {
                echo 'This will run only if successful'
            }
            failure {
                echo 'This will run only if failed'
            }
            unstable {
                echo 'This will run only if the run was marked as unstable'
            }
            changed {
                echo 'This will run only if the state of the Pipeline has changed'
                echo 'For example, if the Pipeline was previously failing but is now successful'
            }
    }
}