pipeline {
    agent any

    tools {
        maven 'MAVEN_3_9'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'awsspring-boot-jenkinsfile',
                    url: 'https://github.com/Saravanan-Jayaraman/springwithjenkinsfile.git'
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Verify JAR') {
            steps {
                sh 'ls -l target'
            }
        }

        stage('Docker Build (No Dockerfile)') {
            steps {
                sh '''
cat << 'EOF' | docker build -t springwithjenkinsfile:${BUILD_NUMBER} -f - .
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
EOF
                '''
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker rm -f springboot || true
                docker run -d --name springboot -p 8081:8080 springwithjenkinsfile:${BUILD_NUMBER}
                '''
            }
        }
    }
}
