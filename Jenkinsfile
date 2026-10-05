pipeline {
    agent {
      node {
        label 'built-in'
        customWorkspace 'E:\\JenkinsWorkspace\\github-pipeline-demo'
      }
    }
    
    environment{
		// DOCKERHUB_CREDENTIALS = credentials("dockerhub-credentials")
		TAG_ID = "${GIT_COMMIT.take(7)}"
		IMAGE_NAME = "aryan284/demo"
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
				bat 'docker tag demo:%TAG_ID% %IMAGE_NAME%:latest'
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
				bat 'docker push %IMAGE_NAME%:latest'
			}
		}

        stage("Deploy") {
            steps{
                bat 'docker stop demo-container || exit 0'
                bat 'docker rm demo-container || exit 0'
                bat 'docker pull aryan284/demo:%TAG_ID%'
                bat 'docker run -d --name demo-container -p 8081:8081 aryan284/demo:%TAG_ID%'
                bat '''
                	set READY=0
	            	for /L %%i in (1,1,10) do (
						bat curl --fail http://localhost:8081/actuator/health/readiness
						if not errorlevel 1 (
							set READY=1
							exit /b 0
						)
						echo Waiting for application to become ready... Attempt %%i of 10
						ping 127.0.0.1 -n 3 > nul
					)
	            	if "%READY%"=="0" (
						docker stop demo-container
					)
					exit /b 1
                '''
                bat 'docker ps'
            }
        }
    }
}
