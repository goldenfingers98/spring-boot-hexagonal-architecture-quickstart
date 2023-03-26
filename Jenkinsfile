pipeline {
    agent any
    tools {
        maven '3.9.0'
    }
    stages {
        stage('Git checkout') {
            steps {
                git credentialsId: '02293762-3141-4165-9f13-8ff3d2411726', url: 'https://ksoftwares@dev.azure.com/ksoftwares/WASSALNY%20APP/_git/wassalny-auth-ms'
            }
        }
        stage('Unit test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Maven Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('SonarQube analysis') {
            steps {
                script {
                    withSonarQubeEnv(credentialsId: 'sonarqube-api-key') {
                        sh 'mvn clean package sonar:sonar'
                    }
                }
            }
        }
        stage('Quality Gate') {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
            }
          }
    }
}