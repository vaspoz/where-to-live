pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        tool 'maven'
        sh 'mvn clean install -Pprod'
      }
    }

    stage('deploy') {
      steps {
        sh 'mvn tomcat7:deploy -Pprod'
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