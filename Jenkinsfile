pipeline {
    agent any

    tools {
        // Use the "M2_HOME" tool (Maven)
        maven "M2_HOME"
    }

    stages {
        stage('Checkout and Build') {
            steps {
                script {
                    def gitUrl = 'https://github.com/WassimB12/5SAE6_Wamya_SkyStation.git'
                    def gitBranch = 'rimMabrouki5sae6' // Specify your branch here

                    git branch: gitBranch, url: gitUrl
                }
                sh "mvn clean package"
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
