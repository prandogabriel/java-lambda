# Spring Lambda com JWT e Web Adapter

Este projeto implementa uma aplicação **Spring Boot** para rodar no **AWS Lambda** utilizando o **AWS Lambda Web Adapter**. A aplicação é empacotada como uma imagem Docker e gerenciada com o **AWS SAM CLI**.

## Requisitos

### Ferramentas Necessárias
- [Java 21+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://www.docker.com/)
- [AWS CLI](https://aws.amazon.com/cli/)
- [AWS SAM CLI](https://aws.amazon.com/serverless/sam/)

### Configuração de Credenciais AWS
Certifique-se de que suas credenciais AWS estão configuradas corretamente com as seguintes permissões:
  - Acesso ao **Amazon ECR** (para repositórios de imagens Docker).
  - Acesso ao **AWS Lambda** e **API Gateway**.

```bash
aws configure
```

### Deploy com SAM
```bash
sam build

sam deploy --guided
```