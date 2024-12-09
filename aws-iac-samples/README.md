# Exemplos de comandos AWS CLI

## Amazon S3

### Criar um Bucket

```bash
aws s3 mb s3://meu-novo-bucket
```

### Listar Buckets

```bash
aws s3 ls
```

### Enviar um Arquivo para o Bucket

```bash
aws s3 cp meu-arquivo.txt s3://meu-novo-bucket/
Baixar um Arquivo do Bucket

```bash
aws s3 cp s3://meu-novo-bucket/meu-arquivo.txt .
```

### Excluir um Bucket

```bash
aws s3 rb s3://meu-novo-bucket --force
```

## Amazon EC2

### Listar Imagens AMI Disponíveis

```bash
aws ec2 describe-images --owners amazon
```

### Criar uma Instância

```bash
aws ec2 run-instances \
    --image-id ami-0c02fb55956c7d316 \
    --count 1 \
    --instance-type t2.micro \
    --key-name minha-chave-ec2 \
    --security-group-ids sg-12345678 \
    --subnet-id subnet-12345678
```

### Listar Instâncias

```bash
aws ec2 describe-instances
```

### Parar uma Instância

```bash
aws ec2 stop-instances --instance-ids i-1234567890abcdef0
```

### Excluir uma Instância

```bash
aws ec2 terminate-instances --instance-ids i-1234567890abcdef0
```

## Cloudformation 

### Como Implantar o Template

#### Valide o Template:

```bash
aws cloudformation validate-template --template-body file://cloudformation/ec2-instance.yaml
``` 

#### Crie o Stack:

```bash
aws cloudformation create-stack \
    --stack-name MyEC2Stack \
    --template-body file://cloudformation/ec2-instance.yaml
``` 

#### Verifique o Status do Stack:

```bash
aws cloudformation describe-stacks --stack-name MyEC2Stack
```

#### Excluir o Stack:

```bash
aws cloudformation delete-stack --stack-name MyEC2Stack
```