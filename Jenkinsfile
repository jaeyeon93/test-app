pipeline {
//     agent {
//         docker {
//             image 'gradle:6.8.2'
//         }
//     }
    agent {
        docker {
            image 'node:14-alpine'
        }
    }
    stages {
        stage('Build') {
            steps {
                echo 'Hello Build Stage'
                sh 'node --version'
            }
        }
        stage('Test') {
            steps {
                echo 'Hello Testing stage'
//                 sh 'make check || true'
//                 junit '**/target/*.xml'
            }
        }
        stage('Deploy') {
//             when {
//                 expression {
//                     currentBuild.result == null || currentBuild.result == 'SUCCESS'
//                 }
//             }
            steps {
                echo 'Hello deploy stage'
            }
        }
    }
    post {
        always {
                    echo 'This will always run'
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