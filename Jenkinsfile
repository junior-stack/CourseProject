pipeline {
    agent any

    stages {
        stage("pull"){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'd684f729-599b-45ab-adbf-172c59ea2c31', url: 'git@github.com:junior-stack/CourseProject.git']]])
            }
        }
    }
}