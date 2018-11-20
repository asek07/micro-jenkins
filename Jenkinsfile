#!/bin/env groovy

output="The current Job Name is: ${env.JOB_NAME}"
pipeline {
    agent any
    stages {
        stage('Stage 1') {
            steps {
                echo "This is working!"
                sh 'mvn --version'
                echo "${output}"
            }
        }

        stage('Job Info') {
            echo "Current build number is: ${env.BUILD_NUMBER}"
        }
    }
}