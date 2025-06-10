pipeline {
    agent any
        stages {
            stage("Build") {
                steps {
                    git url: 'https://github.com/cyrille-leclerc/multi-module-maven-project'
                    withMaven {
                        sh "mvn clean verify"
                    }
                }
                stage('Test') {
                    steps {
                        withMaven {
                             sh 'mvn test'
                        }
                    }
                    post {
                        always {
                           junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
            }
        }
    }