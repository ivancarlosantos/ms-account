pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage('Test') {
            steps {
                echo 'TEST'
                git 'https://github.com/ivancarlosantos/ms-account.git'
                sh 'mvn test'
                archiveArtifacts artifacts: 'target/surefire-reports/**'
            }
        }
        stage('Build') {
            steps {
                echo 'BUILD'
                sh 'mvn clean package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
        stage('Deploy') {
            when{
               branch 'master'
            }
            steps {
                echo 'DEPLOY'
                sh 'echo Deploy'
            }
        }
    }
}