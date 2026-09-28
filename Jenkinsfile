pipeline {
    agent {
      node {
        label 'built-in'
        customWorkspace 'E:\\JenkinsWorkspace\\github-pipeline-demo'
      }
    }

    stages {
        stage("Build") {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage("Test") {
            steps{
                bat 'mvn test'
            }
        }

        stage("Deploy") {
            steps{
                bat 'docker build -t demo:1.0 .'
                bat 'docker stop demo-container || exit 0'
                bat 'docker rm demo-container || exit 0'
                bat 'docker run -d --name demo-container -p 8081:8081 demo:1.0'
            }
        }
    }
}
