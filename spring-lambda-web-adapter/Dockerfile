FROM public.ecr.aws/sam/build-java21:latest as build-image
WORKDIR "/task"
COPY src src/
COPY pom.xml ./
RUN mvn -q clean package

FROM public.ecr.aws/docker/library/amazoncorretto:21.0.5-al2023
COPY --from=public.ecr.aws/awsguru/aws-lambda-adapter:0.8.4 /lambda-adapter /opt/extensions/lambda-adapter
ENV PORT=8000
WORKDIR /opt
COPY --from=build-image /task/target/spring-lambda-0.0.1-SNAPSHOT.jar /opt
CMD ["java", "-jar", "spring-lambda-0.0.1-SNAPSHOT.jar", "--server.port=${PORT}"]
