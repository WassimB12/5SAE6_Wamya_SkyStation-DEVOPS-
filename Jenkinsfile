pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

    stage('test') {
    	agent any
      steps {
      	sh 'pwd'
      }
    }
        stage('tesettes') {
    	agent any
      steps {
      	sh 'ls -l'
      }
    }
        
    stage('Docker Build') {
    	agent any
      steps {
      	sh 'docker build -t msadaayessine_wamya_skystation:latest .'
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

        stage('Run Unit Tests with Mockito') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
