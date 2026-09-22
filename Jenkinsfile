pipeline {
    agent {
      node {
        label 'built-in'
        customWorkspace 'E:\\JenkinsWorkspace\\github-pipeline-demo'
      }
    }

    stages {
        stage('Check Workspace') {
            steps {
                bat 'dir /s'
            }
        }
    }
}
