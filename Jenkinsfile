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
           sh "mvn compilez"
           emailUser("SUCCESS")
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
    //style the email accordingly
    def outcome = ""
    if (status == "SUCCESS") {
        outcome = """<h1 style='color:#3CB371'>\\u2705 ${status}: 
                        <span style='color:#696969'>Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</span>
                    </h1>"""
    } else {
        outcome = """<h1 style='color:#DC143C'>\\u274C ${status}: 
                        <span style='color:#696969'>Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</span>
                    </h1>"""
    }
    emailext (
            to: "andy.sek94@gmail.com",
            subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """
                ${outcome}
                <h3>
                    Check console output <a href='${env.BUILD_URL}'>here</a>
                </h3>
                """,
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