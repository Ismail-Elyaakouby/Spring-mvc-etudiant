pipeline {
    agent any
    tools { 
        maven 'Maven3.3.3' 
    }
    stages {
        stage ('Compile Stage') {

            steps {
                    sh 'mvn clean compile'
            }
        }
        stage ('Testing Stage') {

            steps {
                    sh 'mvn test'
            }
        }
        stage ('Deployment Stage') {
            steps {
                    sh 'mvn deploy'
            }
        }
	
		stage('SonarQube analysis') {
			// requires SonarQube Scanner 2.8+
		     steps {
			//sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.66.138:19000 -Dsonar.login=57f1b5c979c1618e43077fdfab801de05d2ede6e"
			//sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.1.24:19000 -Dsonar.login=2c8b36578b5210e7a062ca33f19d8e3111f54c56"
			sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.1.24:19000 -Dsonar.login=admin -Dsonar.password=admin"
            }
	    }
		
		
    }
}
