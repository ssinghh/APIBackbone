## Prerequisites
* Java 17
* Mongo DB

## Setup
* Install Java 17
* Create a free Mongo Atlas account and setup free MongoDB database collection
* Use MongoDB Compass
* Initial SpringBoot setup via [Spring Initializr](https://www.smartresponse.org/)

#### Note
Refer freeCodeCamp.org for needed help

## Build Docker image and Deploy on AWS EC2
 
*  Create a Dockerfile for API Spring Boot Project
*  Build the Docker image using docker build.
*  Create an AWS ECR repository and authenticate with Docker.
*  Tag and push the Docker image to ECR using docker push.
*  Run the image from ECR on EC2 instance

1. Create a Dockerfile for API Spring Boot Project
   In the root directory of API Spring Boot project, create a file called Dockerfile. 
 
2. Build the Docker Image
   Once the Dockerfile is in place, you can build the Docker image. Run the following commands:
     ```shell
   docker build -t api-backbone-app .
    ```
 
3. Push the Docker Image to AWS ECR
   Step 3.1: Set Up AWS CLI
   Ensure you have the AWS CLI installed and configured. You can install the AWS CLI by following the official installation guide.

   Configure the AWS CLI with your credentials:
    ```shell
   aws configure
    ```
   Step 3.2: Create an ECR Repository
   Go to the Amazon ECR Console
   ```shell
   aws ecr create-repository --repository-name api-backbone-app-repo --region <your-region>
    ```
   Step 3.3: Authenticate Docker to AWS ECR
   AWS ECR requires authentication to push Docker images.
      ```shell
   aws ecr get-login-password --region <your-region> | docker login --username AWS --password-stdin <aws-account-id>.dkr.ecr.<your-region>.amazonaws.com
    ```
   Step 3.4: Tag the Docker Image
   Before pushing the image to ECR, you need to tag it with the ECR repository URI.
      ```shell
   docker tag api-backbone-app:latest <aws-account-id>.dkr.ecr.<your-region>.amazonaws.com/api-backbone-app-repo:latest
    ```
   Step 3.5: Push the Docker Image to ECR
   ```shell
   docker push <aws-account-id>.dkr.ecr.<your-region>.amazonaws.com/api-backbone-app-repo:latest
    ```

4. Run the Docker Image from ECR
   Once the image is pushed to ECR, you can run it from an EC2 instance(Launch EC2 with custom Inbound Security Group 8080 port)
   Login to ECR
   ```shell
   aws ecr get-login-password --region <your-region> | docker login --username AWS --password-stdin <aws-account-id>.dkr.ecr.<your-region>.amazonaws.com
   ```
    Pull the image from ECR:
   ```shell
   docker pull <aws-account-id>.dkr.ecr.<your-region>.amazonaws.com/api-backbone-app-repo:latest
    ```
   Run the image:  This will run the Spring Boot application inside a Docker container and expose it on port 8080.
   ```shell
   docker run -p 8080:8080 <aws-account-id>.dkr.ecr.<your-region>.amazonaws.com/api-backbone-app-repo:latest
    ```
  
5. Test API on browser

    http://<ec2-public-ip>:8080/api/v1/movies


