pipeline{
    
    agent any
    
     tools {
        maven 'MAVEN_3_9_12'
    }
    
     environment {
        COMSPEC = 'C:\\Windows\\System32\\cmd.exe'
        GIT_HOME = 'M:\\Git'
        PATH = "C:\\Windows\\System32;${GIT_HOME}\\cmd;${env.PATH}"
        IMAGE_NAME = 'jenkins-pipeline-jenkinsfiledemo'
        IMAGE_TAG  = 'latest'
        DOCKER = 'C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe'
    }
    
    stages{
        
        stage('Check CMD') {
            steps {
                bat '%COMSPEC% /c echo CMD OK'
            }
        }
        
        stage('Check Git Version'){
            steps{
                bat 'git --version'
            }
        }
        
         stage('Clone Repo') {
            steps {
                git branch: 'main', 
                credentialsId: 'github_jenkins_piplinedemo', 
                url: 'https://github.com/Saravanan-Jayaraman/jenkinspipeline.git'
            }
        }
        
           stage('Maven Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        
        stage('List Target') {
            steps {
                bat 'dir'
            }
        }
        
           stage('Docker Version') {
            steps {
                bat '"%DOCKER%" --version'
            }
        }
        
        stage('Create Dockerfile Dynamically') {
            steps {
                writeFile file: 'Dockerfile', text: '''
                                FROM eclipse-temurin:21-jre
                                WORKDIR /app
                                COPY target/*.jar app.jar
                                EXPOSE 8080
                                ENTRYPOINT ["java","-jar","app.jar"]
                                '''
            }
        }
          stage('Docker Build') {
            steps {
                 bat """
                "%DOCKER%" build -t %IMAGE_NAME%:%BUILD_NUMBER% -t %IMAGE_NAME%:latest .
                """
            }
        }
        
         stage('Run Container') {
           
     steps {
       bat """
                "%DOCKER%" rm -f demo-container 2>nul
                "%DOCKER%" run -d -p 8083:8080 --name demo-container %IMAGE_NAME%:%BUILD_NUMBER%
                "%DOCKER%" ps
                """
    }

        }
    }
    }
     
