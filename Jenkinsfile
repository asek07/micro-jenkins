#!/bin/env groovy

node {
    stage "Stage 1"
    echo "this is the first stage of our groovy script."
}

//output="The current Job Name is: ${env.JOB_NAME}"
//pipeline {
//    agent any
//    stages {
//        stage('Log Info') {
//            steps {
//                echo "This is working!"
//                sh 'mvn --version'
//                echo "${output}"
//                echo "Current build number is: ${env.BUILD_NUMBER}"
//                echo "Workspace path: ${env.WORKSPACE}"
//            }
//        }
//
//        stage('Compile') {
//            steps {
//                echo "Attempting to compile"
//                sh "mvn compilez"
//            }
//            post {
//                failure {
//                    echo "Compile stage has failed!"
//                }
//            }
//        }
//
//        stage('Cleaning and Packaging') {
//            steps {
//                echo "Cleaning application and packaging"
//                sh "mvn clean package"
//            }
//        }
//    }
//    post {
//       success {
//           archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
//       }
//        failure {
//            echo "build has failed "
//        }
//    }
//}