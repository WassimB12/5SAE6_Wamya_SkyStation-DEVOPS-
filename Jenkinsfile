pipeline{
    agent any



    stages {


        stage('Getting project from Git') {
            steps{
      			checkout([$class: 'GitSCM', branches: [[name: '*/hadhemitrabelssi-5sae6_wamya']],
			extensions: [],
			userRemoteConfigs: [[url: 'https://github.com/WassimB12/5SAE6_Wamya_SkyStation.git']]])
            }
        }


       stage('Cleaning the project') {
            steps{
                	sh "mvn -B -DskipTests clean  "
            }
        }



        stage('Artifact Construction') {
            steps{
                	sh "mvn -B -DskipTests package "
            }
        }


/*
         stage('Unit Tests') {
            steps{
               		 sh "mvn test "
            }
        }
*/


        stage('Code Quality Check via SonarQube') {
            steps{

             		sh " mvn clean verify sonar:sonar -Dsonar.projectKey=cicd -Dsonar.projectName='cicd' -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.token=sqp_ac3bcb9add489b83e39479622a500c6caca89e6c"

            }
        }


        stage('Publish to Nexus') {
            steps {


  sh 'mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=gestion-station-ski -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=maven-releases -Durl=http://192.168.33.10:8081/repository/maven-releases/ -Dfile=target/gestion-station-ski-1.0.jar'

            }
        }

stage('Build Docker Image') {
                      steps {
                          script {
                            sh 'docker build -t hadhemii/spring-app-gl:second .'
                          }
                      }
                  }

                  stage('login dockerhub') {
                                        steps {
				sh 'docker login -u hadhemii --password dckr_pat_IWHnvrfUZKUoGFxg8MigwDiRveQ'
                                            }
		  }

	                      stage('Push Docker Image') {
                                        steps {
                                   sh 'docker push hadhemii/spring-app-gl:second'
                                            }
		  }


		   stage('Run Spring && MySQL Containers') {
                                steps {
                                    script {
                                      sh 'docker-compose up -d'
                                    }
                                }
                            }

	    



     
}

	    
        post {
		/*success{
		mail bcc: '', body: '''Dear Med Aziz, 
we are happy to inform you that your pipeline build was successful. 
Great work ! 
-Jenkins Team-''', cc: '', from: 'mohamedaziz.benhaha@esprit.tn', replyTo: '', subject: 'Build Finished - Success', to: 'mohamedaziz.benhaha@esprit.tn'
		}
		
		failure{
mail bcc: '', body: '''Dear Med Aziz, 
we are sorry to inform you that your pipeline build failed. 
Keep working ! 
-Jenkins Team-''', cc: '', from: 'mohamedaziz.benhaha@esprit.tn', replyTo: '', subject: 'Build Finished - Failure', to: 'mohamedaziz.benhaha@esprit.tn'
		}*/

       always {
		//emailext attachLog: true, body: '', subject: 'Build finished',from: 'mohamedaziz.benhaha@esprit.tn' , to: 'mohamedaziz.benhaha@esprit.tn'
            cleanWs()
       }
    }

    
	
}
       
