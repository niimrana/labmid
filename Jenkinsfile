pipeline {
    agent any

    environment {
        GITHUB_REPO = https://github.com/niimrana/labmid.git
        AWS_ACCESS_KEY_ID = credentials('aws-access-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
        ELASTIC_BEANSTALK_ENV_NAME = 'your-elastic-beanstalk-environment-name'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws-access-key-id'],
                                     [$class: 'AmazonWebServicesCredentialsBinding', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY', credentialsId: 'aws-secret-access-key']]) {
                        sh "aws elasticbeanstalk create-application-version --application-name YourApplicationName --version-label ${env.BUILD_NUMBER} --source-bundle S3Bucket=your-s3-bucket-name,S3Key=your-s3-key.zip"
                        sh "aws elasticbeanstalk update-environment --environment-name ${ELASTIC_BEANSTALK_ENV_NAME} --version-label ${env.BUILD_NUMBER}"
                    }
                }
            }
        }
    }

    post {
        success {
            script {
                echo "Build, Test, and Deployment Successful! Repository: ${GITHUB_REPO}"
            }
        }
    }
}