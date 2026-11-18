pipeline {
    agent any

    environment {
    INPUT_FOLDER = "${params.INPUT_FOLDER}"
    PROCESS_FOLDER = "${params.PROCESS_FOLDER}"
    ENCODED_FOLDER = "${params.ENCODED_FOLDER}"
    ERROR_FOLDER = "${params.ERROR_FOLDER}"
    }

    stages {
        stage('Build and Test') {
            steps {
                bat 'mvn clean test'}
        }
    }
}