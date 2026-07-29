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
    sh "docker rm -f collegebus-monolith || true"
    sh "docker build -f backend/Dockerfile -t collegebus-monolith ."
    sh "docker run -d --name collegebus-monolith -p 8085:8085 collegebus-monolith"
  }
}
