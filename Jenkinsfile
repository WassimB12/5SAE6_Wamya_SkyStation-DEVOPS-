pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Run Unit Tests with Mockito') {
            steps {
                sh 'mvn test'
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
  stage('login dockerhub') {
             steps {
    			sh 'docker login -u yassinemsadaa --password Yassine@1995'
             }
        }
        stage('Docker Build & PUSH') {
            steps {
                script{
                    sh'docker build -t yassinemsadaa/msadaayessine_wamya_skystation .'
                    sh 'docker push yassinemsadaa/msadaayessine_wamya_skystation'
                }
            }
        }
         stage('prometheus and grafana') {
                                    steps {
                                        script {
                                          sh 'docker compose up -d'
                                        }
                                    }
                                }

        stage('SonarQube Analysis') {
            steps {
                script {
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

        
    }
}
