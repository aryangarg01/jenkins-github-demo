pipeline {
    agent {
      node {
        label 'built-in'
        customWorkspace 'E:\\JenkinsWorkspace\\github-pipeline-demo'
      }
    }
    
    environment{
		DOCKERHUB_CREDENTIALS = credentials("dockerhub-credentials")
		TAG_ID = "${GIT.COMMIT.take(7)}"
		IMAGE_NAME = "%DOCKERHUB_CREDENTIALS_USR%/demo"
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
        
        stage("Docker Build"){
			steps{
				bat 'docker build -t demo:%TAG_ID% .'
				bat 'docker tag demo:%TAG_ID% %IMAGE_NAME%:%TAG_ID%'
			}
		}
		
		stage("Docker Push"){
			steps{
				withCredentials([usernamePassword(
					usernameVariable: 'DOCKER_USERNAME',
					passwordVariable: 'DOCKER_PASSWORD',
					credentialsId: 'dockerhub-credentials'
				)]){
					bat 'docker login -u "%DOCKER_USERNAME%" -p "%DOCKER_PASSWORD%"'
				}
				bat 'docker push %IMAGE_NAME%:%TAG_ID%'
			}
		}

        stage("Deploy") {
            steps{
                bat 'docker stop demo-container || exit 0'
                bat 'docker rm demo-container || exit 0'
                bat 'docker run -d --name demo-container -p 8081:8081 demo:1.0'
            }
        }
    }
}
