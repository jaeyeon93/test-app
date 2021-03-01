pipeline {
//     agent {
//         docker {
//             image 'gradle:6.8.2'
//         }
//     }
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Hello Build Stage'
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
}