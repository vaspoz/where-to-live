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
  environment {
    MAVEN_HOME = '/opt/apache-maven-3.6.3'
    M2_HOME = '/opt/apache-maven-3.6.3'
    PATH = '$PATH:/opt/apache-maven-3.6.3/bin'
  }
}