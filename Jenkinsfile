node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'Default Maven';
    dir('backend') {
      withSonarQubeEnv() {
        sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=hello"
      }
    }
  }
  stage('Deploy to Docker') {
    sh "docker system prune -f"
    sh "docker-compose down || true"
    sh "docker-compose up -d --build"
  }
}
