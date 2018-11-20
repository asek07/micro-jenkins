#!/bin/env groovy

NAME="This variable works"
pipeline {
    agent any
    stages {
        stage('Example Build') {
            steps {
                echo "This is working!"
                sh 'mvn --version'
                echo "${NAME}"
            }
        }
    }
}