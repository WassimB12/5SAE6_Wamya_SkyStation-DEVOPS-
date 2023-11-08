pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }


        stage('Docker cleanup') {
            steps {
                sh "docker image prune -f"
            }
        }
        
        stage('Docker Build') { 
            steps { 
                script{
                 app = docker.build("msadaayessine_wamya_skystation")
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Assuming SonarQube is running on http://localhost:9000
                    withSonarQubeEnv(installationName: 'sonarqube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
        stage('Nexus') {
            steps {
                script {
                    sh "mvn clean deploy -DskipTests -Drepository.url='http://192.168.33.10:8081/' -s settings.xml"
                    sh "${MAVEN_HOME}/bin/mvn deploy:deploy-file \
                        -Durl=http://192.168.33.10:8081/repository/maven-releases/ \
                        -DrepositoryId=nexus \
                        -Dfile=target/gestion-station-ski-1.0.jar \
                        -DgroupId=tn.esprit.spring \
                        -DartifactId=gestion-station-ski \
                        -Dversion=1.0 \
                        -Dpackaging=jar \
                        -DupdateReleaseInfo=true"
                }
            }
        }

       // stage('Run Unit Tests with Mockito') {
         //   steps {
           //     sh 'mvn test'
            //}
       // }
    }
}
