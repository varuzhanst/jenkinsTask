pipeline{

    agent any

    stages{

        stage('Checkout from git'){

            steps{
                 git(url: 'https://github.com/varuzhanst/jenkinsTask.git', branch: 'master')
            }

        }

        stage('Build')
        {
            steps{
                sh '''
                mvn clean
                mvn install
                '''

            }
        }

      stage('Code analysis'){
          steps{
              sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=JenkinsTask  -Dsonar.projectName=\'JenkinsTask\' -Dsonar.host.url=http://localhost:9000  -Dsonar.token=sqp_f6c0cdd671a401d5afab2f1a16894e1838bc1f2a'
          }
      }

          stage('Test')
        {
            steps{
              sh 'mvn test'
            }
        }
        stage('Deploy to Tomcat') {
        steps {
           deploy adapters: [tomcat9(url: 'http://localhost:9999/',
                              credentialsId: '98930494-b4a5-4723-839d-dd2c571e2de8')],
                     war: 'target/*.war',
                     contextPath: 'app'
        }
    }
    }
}