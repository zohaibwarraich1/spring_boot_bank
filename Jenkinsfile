pipeline {
    agent any
    stages {
        stage("Clone") {
            steps {
                git url:"https://github.com/zohaibwarraich1/spring_boot_bank.git", branch: "my-devops"
                echo "Successfully Cloned!"
            }
        }
        stage("Build") {
            steps {
                sh 'docker build -t bank-app:latest .'
                echo 'Successfully Build!'
            }
        }
        stage("Push") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'echo "$PASSWORD" | docker login -u "$USERNAME" --password-stdin'
                    sh 'docker tag bank-app:latest ${USERNAME}/bank-app:latest'
                    sh 'docker push ${USERNAME}/bank-app:latest'
                }
                echo 'Successfully Pushed!'
            }
        }
        stage("Deploy") {
            steps {
                sh "docker-compose up -d"
                echo 'Successfully Deployed!'
            }
        }
    }
    post{
        failure {
            script {
                emailext (
                    from: "raveeddogar10@gmail.com",
                    subject: "Social Scope App build Successful",
                    body: "Spring Boot Bank App build and deployed Successfully!",
                    to: "muhammadzohaibwarraich0@gmail.com"
                    )
            }
        }
    }
}
