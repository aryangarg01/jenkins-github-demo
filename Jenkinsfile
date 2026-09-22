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
                bat 'echo Build Phase is running'
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
