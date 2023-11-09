pipeline {
    agent any
     environment {
        MYSQLDB_LOCAL_PORT = '3306'
        MYSQLDB_DOCKER_PORT = '3306'
        SPRING_LOCAL_PORT = '8081'
        SPRING_DOCKER_PORT = '8081'
        // Other environment variables
    }

    stages {
        stage('Getting project from Git') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/rimMabrouki5sae6']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/WassimB12/5SAE6_Wamya_SkyStation.git']]
                ])
            }
        }

        stage('Cleaning the project') {
            steps {
                sh "mvn -B -DskipTests clean"
            }
        }

        stage('Artifact Construction') {
            steps {
                sh "mvn -B -DskipTests package"
            }
        }



        stage('Unit Tests') {
            steps {
                sh "mvn clean test"
            }
        }

        // Add more stages if needed

        stage('Code Quality Check via SonarQube') {
            steps {
                withSonarQubeEnv(installationName: 'sql') {
                    sh "mvn clean verify sonar:sonar -Dsonar.projectKey=devops -Dsonar.projectName='devops' -Dsonar.host.url=http://192.168.1.2:9000 -Dsonar.login=$SONARQUBE_TOKEN"
                }
            }
        }

        stage('Publish to Nexus') {
            steps {
                sh 'mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=gestion-station-ski -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=maven-releases -Durl=http://192.168.1.2:8081/repository/maven-releases/ -Dfile=target/gestion-station-ski-1.0.jar'
            }
        }


        stage('Building Image') {
            steps {
                script {
                    sh 'docker build -t rimmabrouki/spring-app:rimmabrouki .'
                }
            }
        }

        stage('login dockerhub') {
            steps {
                sh 'docker login -u rimmabrouki -p dckr_pat_ThIbsRvdEwOSVYMAk_4zck_22v0'
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push rimmabrouki/spring-app:rimmabrouki'
            }
        }

        stage('Run Spring && MySQL Containers') {
            steps {
                script {
                    sh 'docker compose up -d'
                }
            }
        }

        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
    }
}
