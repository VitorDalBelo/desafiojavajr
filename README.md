

## Execução com docker

Afim de evitar problemas de incompatibilidade com a versão do node , do sistema operacional ou qualquer outra particularidade do ambiente , recomendo que execute a aplicação com o docker , para isso é necessário [ter o docker instalado em sua máquina](https://www.docker.com/products/docker-desktop/) e utilizar os seguites comandos :

### o projeto como um todo

```bash
docker compose -f "docker-compose.yml" up -d --build
```


### apenas o back end

```bash
cd crudjava
docker compose -f "docker-compose.yml" up -d --build
```

### apenas o front end

```bash
cd front
docker compose -f "docker-compose.yml" up -d --build
```


após a execução do projeto você poderá ver a documentação da api no seu avegador acessando http://localhost:8189 e o fontend acessando  http://localhost:3030/


é possivél alterar a porta em que roda os serviços para isso cesse os arquivos abaixo e coloque no ludar de [Num porta XXX] o numero da porta em que o serviço deverá rodar em sua máquina


### /docker-compose.yml
```bash
version: '3.9'

networks:
  todo-network:
    name: todo-network
    driver: bridge

services:
  db:
    image: postgres:16
    container_name: postgres_todo6_java
    restart: always
    environment:
      POSTGRES_USER: user
      POSTGRES_PASSWORD: 1010
      POSTGRES_DB: todo
    ports:
      - "[Num porta banco de dados]:5432"
    volumes:
      - postgres_todo6_java:/var/lib/postgresql/data
    networks:
      - todo-network

  api:
    build: 
      context: ./crudjava 
      dockerfile: Dockerfile
    container_name: api_todo_java
    restart: always
    environment:
      SPRING.DATASOURCE.URL: jdbc:postgresql://db:5432/todo
      SPRING.DATASOURCE.USERNAME: user
      SPRING.DATASOURCE.PASSWORD: 1010
    command: mvn spring-boot:run
    ports:
      - "[Num porta api]:8080"
    depends_on:
      - db
    networks:
      - todo-network
  app:
    build: 
      context: ./front 
      dockerfile: Dockerfile
    environment:
      - REACT_APP_BACKEND_URL= http://192.168.1.116:8189
    ports:
      - "[Num porta frontend]:3000"
    depends_on:
      - db
      - api
    networks:
      - todo-network

volumes:
  postgres_todo6_java:

```
### /front/.env

```bash
REACT_APP_API_URL=http://localhost:[num porta api]
```
