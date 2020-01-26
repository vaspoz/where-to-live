pipeline {
  agent any
  stages {
    stage('compile') {
      steps {
        tool 'maven'
      }
    }

    stage('mvn') {
      steps {
        sh 'mvn clean install'
      }
    }

  }
  tools {
    maven 'maven'
  }
  environment {
    MAVEN_HOME = '/opt/apache-maven-3.6.3'
  }
}