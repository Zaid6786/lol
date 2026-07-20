pipeline {
    agent any

    tools {
        maven 'Maven'   // Matches your Jenkins global tool config
        jdk 'Java21'    // Matches your JDK tool config
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
                dir('backend') {   // Run Maven inside backend folder
                    sh 'mvn clean verify'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                dir('backend') {   // Run SonarQube analysis inside backend folder
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

        stage("Quality Gate") {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                dir('backend') {   // Run Maven inside backend folder
                    sh 'mvn deploy'
                }
            }
        }
    }
}
