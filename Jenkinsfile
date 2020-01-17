pipeline {
  agent any
  stages {
    stage('compile') {
      steps {
        sh 'mvn clean install'
      }
    }

  }
  environment {
    MAVEN_HOME = '/opt/apache-maven-3.6.3'
  }
}