#!/bin/env groovy

output="The current Job Name is: ${env.JOB_NAME}"
pipeline {
    agent any
    stages {
        stage('Log Info') {
            steps {
                echo "This is working!"
                sh 'mvn --version'
                echo "${output}"
                echo "Current build number is: ${env.BUILD_NUMBER}"
            }
        }

        stage('Compile') {
            steps {
                echo "Attempting to compile"
                sh "mvn compile"
            }
        }

        stage('Cleaning and Packaging') {
            steps {
                echo "Cleaning application and packaging"
                sh "mvn clean package"
            }
        }
    }
    post {
       success {
           archiveArtifacts artifacts: '/target/*.jar', onlyIfSuccessful: true
       }
    }
}