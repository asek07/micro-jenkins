#!/bin/env groovy

node {
   try {
       //Checking out the git repo
       stage ("Checkout") {
           echo "this is the first stage of our groovy script."
           checkout scm
       }

       //Calling Maven to compile with error so it can be caught
       stage ("Maven test") {
           sh "mvn compileeee"
       }
   }
   catch(err) {
       currentBuild.result = 'FAILURE'
       emailUser("FAILED")
       errorMessage(err)
       throw err
   }
}

def errorMessage(err) {
    echo "Error has occured: ${err}"
    echo "Console output can be found here ${env.BUILD_URL}"
}
def emailUser(status){
    emailext (
            to: "andy.sek94@gmail.com",
            subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """<p>${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
    )
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