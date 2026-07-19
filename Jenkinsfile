pipeline {
    agent any

    tools {
        maven 'Maven'   // Make sure you configured Maven in Jenkins global tools
        jdk 'Java17'    // Ensure Java 17 is installed and configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Zaid6786/lol.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn sonar:sonar \
                          -Dsonar.projectKey=hi \
                          -Dsonar.host.url=http://18.61.3.254:9000 \
                          -Dsonar.login=sqa_8b5059f596df0e1ed2ba0adf6578d6ca7bd00959
                    '''
                }
            }
        }
    }
}
