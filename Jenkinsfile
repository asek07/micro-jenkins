#!/bin/env groovy
node {
    //find the user that did the last commit to the repo
    committerEmail = sh (
            script: 'git --no-pager show -s --format=\'%ce\'',
            returnStdout: true
    ).trim()

    committerName = sh (
            script: 'git --no-pager show -s --format=\'%cn\'',
            returnStdout: true
    ).trim()

    try {
       //Checking out the git repo
       stage ("Checkout") {
           ansiColor('vga') {
               echo "\033[1;34mStage 1: Checking out repository.\033[0m"
               checkout scm
               echo "\033[1;32mCheckout complete.\033[0m"
           }
       }

       stage ("Build & Test") {
           ansiColor('vga') {
               echo "\033[1;34mStage 2: Cleaning build...\033[0m"

               output = sh (
                       script: 'date',
                       returnStdout: true
               ).trim()

               echo "Date: ${output}"

               echo "ls -l"

               sh "date"

               sh "mvn clean"
               echo "\033[1;32mClean complete.\033[0m"
               echo "\033[1;34mStage 2.1: Running tests.\033[0m"
               sh "mvn test"
               echo "\033[1;32mTests complete.\033[0m"
           }
       }

       stage ("Packaging") {
           ansiColor('vga') {
               echo "\033[1;34mStage 3: Packinging project.\033[0m"
               sh "mvn package"
               echo "\033[1;32mPackaging complete.\033[0m"
           }
       }

       stage ("Archival") {
           ansiColor('vga') {
               echo "\033[1;34mArchiving existing JAR snapshot.\033[0m"
               archiveArtifacts 'target/*.jar'
               echo "\033[1;32mArchival successful!\033[0m"
               currentBuild.result = 'SUCCESS'
           }
           //emailUser("SUCCESS")
       }
    }
    catch(err) {
       currentBuild.result = 'FAILURE'
       //emailUser("FAILED")
       errorMessage(err)
       throw err
    }
    finally {
        ansiColor('vga') {
            echo "\033[1;33mDuration of build: ${currentBuild.durationString}\033[0m"
            if(currentBuild.result == 'SUCCESS') {
                echo "\033[1;32mPipeline has completed successfully! \033[0m"
                echo "Commiter Name: ${committerName}"
                echo "Commiter Email: ${committerEmail}"
            }
        }
    }
}

def errorMessage(err) {
    ansiColor('vga') {
        echo "\033[1;31mAn error has occured: ${err} \033[0m"
        echo "\033[1;31mConsole output can be found here ${env.BUILD_URL} \033[0m"
    }
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
            to: "${committerEmail}",
            subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """
                ${outcome}
                <h3>
                    Check console output <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>
                </h3>
                <h2>Last commit information</h2>
                <h4>Name: ${committerName}</h4>
                <h4>Email: ${committerEmail}</h4>
                """
    )
}