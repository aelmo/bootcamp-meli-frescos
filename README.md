# IT Bootcamp Meli Frescos
## Dependências
- Java 11
- Maven
- Docker
## Documentação
A documentação dos endpoints pode ser encontrada em https://documenter.getpostman.com/view/16130840/Tzm8FvZX. 
Além disso em docs/ você irá encontrar o Modelo Lógico, a coleção do postman do projeto e a User Story referente ao requisito 6.

## Rodar a aplicação
Para rodar a aplicação é necessário que não haja nenhum processo rodando na porta 3306, e então rodar o comando abaixo

docker run --detach --name=mysqldb --env="MYSQL_ROOT_PASSWORD=root" --publish 3306:3306 mysql:latest

Após este processo você pode rodar a aplicação em sua IDE de preferência e o banco irá subir com alguns dados iniciais.

## Autenticação
Todos os endpoints necessitam de autenticação, no momento existem dois usuários criados:
#### Representante
 * username: agentuser
 * password: 12345678
#### Comprador
 * username: buyeruser
 * password: 12345678


