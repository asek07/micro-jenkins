#!/bin/env groovy

def committerEmail = sh (
        script: 'git --no-pager show -s --format=\'%ae\'',
        returnStdout: true
).trim()

node {
   try {
       //Checking out the git repo
       stage ("Checkout") {
           echo "Checking out repository."
           checkout scm
       }

       //
       stage ("Build & Test") {
           echo "Cleaning build..."
           sh "mvn clean"
           echo "Clean complete."
           echo "Testing build..."
           sh "mvn test"
           echo "Test complete."
           //emailUser("SUCCESS")
       }

       stage ("Packaging") {
           echo "Attempting to package..."
           sh "mvn package"
           echo "Packaging complete."
       }

       stage ("Archival") {
           echo "Archiving existing JAR snapshot."
           archiveArtifacts 'target/*.jar'
           echo "Archival complete."
           currentBuild.result = 'SUCCESS'
       }
   }
   catch(err) {
       currentBuild.result = 'FAILURE'
       //emailUser("FAILED")
       errorMessage(err)
       throw err
   }
    finally {
        if(currentBuild.result == 'SUCCESS') {
            echo "THE PIPELINE HAS COMPLETED SUCCESSFULLY!"
            echo "${committerEmail}"
        }
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
        outcome = """<h1 style='color:#3CB371'>${status}: 
                        <span style='color:#696969'>Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</span>
                    </h1>"""
    } else {
        outcome = """<h1 style='color:#DC143C'>${status}: 
                        <span style='color:#696969'>Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</span>
                    </h1>"""
    }
    emailext (
            to: "andrew.sekulovski@cognizant.com",
            subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """
                ${outcome}
                <h3>
                    Check console output <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>
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