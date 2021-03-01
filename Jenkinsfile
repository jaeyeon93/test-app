pipeline {
    agent {
        docker {
            image 'gradle:6.8.2'
        }
    }

    stages {
        stage('Build') {
            steps {
                echo 'Build stage print gradle version'
                sh 'docker -v'
                sh 'gradle -v'
            }
        }
//         stage('Test') {
//             steps {
//                 echo 'Testing stage'
//                 sh 'make check || true'
//                 junit '**/target/*.xml'
//             }
//         }
//         stage('Deploy') {
//             when {
//                 expression {
//                     currentBuild.result == null || currentBuild.result == 'SUCCESS'
//                 }
//             }
//             steps {
//                 echo 'deploy stage'
//             }
//         }
    }
}