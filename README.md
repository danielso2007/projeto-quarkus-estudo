[![Projeto Quarkus Estudo](https://github.com/danielso2007/projeto-quarkus-estudo/actions/workflows/projeto-quarkus-estudo-maven.yml/badge.svg)](https://github.com/danielso2007/projeto-quarkus-estudo/actions/workflows/projeto-quarkus-estudo-maven.yml)

# projeto-quarkus project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/projeto-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Related guides

- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more

## Provided examples

### RESTEasy JAX-RS example

REST is easy peasy with this Hello World RESTEasy resource.

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

# Gerando docker modo JVM

Este Dockerfile é usado para construir um contêiner que executa o aplicativo Quarkus no modo JVM.

Antes de construir a imagem do contêiner, execute:

`./mvnw package`

Em seguida, crie a imagem com:

`docker build -f src/main/docker/Dockerfile.jvm -t quarkus/projeto-quarkus-jvm .`

Em seguida, execute o contêiner usando:

`docker run -i --rm --network host -p 8080:8080 quarkus/projeto-quarkus-jvm`

Se você quiser incluir a porta de depuração em sua imagem do docker, você terá que expor a porta de depuração (padrão 5005) assim: EXPOSE 8080 5005

Em seguida, execute o contêiner usando:

`docker run -i --rm --network host -p 8080:8080 -p 5005:5005 -e JAVA_ENABLE_DEBUG="true" quarkus/projeto-quarkus-jvm`

# Gerando docker modo Native

Este Dockerfile é usado para construir um contêiner que executa o aplicativo Quarkus no modo nativo (sem JVM).

Antes de construir a imagem do contêiner, execute:

`./mvnw package -Pnative`

Então, construa a imagem com:

`docker build -f src/main/docker/Dockerfile.native -t quarkus/projeto-quarkus .`

Em seguida, execute o contêiner usando:

`docker run -i --network host --rm -p 8080:8080 quarkus/projeto-quarkus`

# Validar consumo com o glances

`glances`