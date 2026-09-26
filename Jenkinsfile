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
                bat 'echo Test Phase is running'
            }
        }

        stage("Deploy") {
            steps{
                bat 'echo Deploy Phase is running'
            }
        }
    }
}
