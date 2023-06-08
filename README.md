# spring-crud
## Banco de dados Postgres com docker

- Instalar o docjer no linux utilizando o seguinte [link](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04-pt);
- Iniciar um container do postgres com o comando: 
- `docker container run -d --name=pg -p 5432:5432 -e POSTGRES_PASSWORD=1234 -e PGDATA=/pgdata -v /pgdata:/pgdata postgres`

## Configurando Intellij
- Configurar o IntellijIDEA para utilizar java 17:
Em `File > Project Structure > SDK > Baixe o SDK 17`
Em `File > Settings > Graddle > Gradle JVM > Use JDK`
