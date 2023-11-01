pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "mvn clean install -Dmaven.test.skip=true"
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
