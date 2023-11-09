pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
    stage('Increment Version') {
            steps {
                script {
                    // Increment the version using Maven Versions Plugin
                    sh 'mvn versions:set -DnewVersion=$(date "+%Y%m%d%H%M%S") -DgenerateBackupPoms=false'
                }
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
