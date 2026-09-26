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
                bat 'java -jar target\\demo-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}
