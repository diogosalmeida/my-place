# My Place

## Descrição

**My Place** é uma aplicação Spring Boot que utiliza o banco de dados Neo4j para gerenciar informações sobre usuários e os lugares que eles visitaram ou planejam visitar. Esta aplicação permite aos usuários registrar lugares visitados, planejar futuras visitas e visualizar um histórico de todos os locais visitados e planejados.

## Características

- **Cadastro de usuários**: Usuários podem se cadastrar na plataforma fornecendo informações básicas.
- **Registro de lugares**: Lugares podem ser adicionados ao sistema com detalhes como nome e descrição.
- **Marcação de visitas**: Usuários podem marcar lugares como visitados com datas específicas.
- **Planejamento de visitas**: Usuários podem adicionar lugares que planejam visitar com datas futuras.
- **Consulta de visitas**: Usuários podem consultar seu histórico de visitas e planejamentos.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java com microserviços.
- **Neo4j**: Banco de dados de grafos que facilita relações complexas e consultas eficientes.
- **Maven**: Ferramenta de automação de build.

## Configuração do Ambiente

### Pré-requisitos

- Java JDK 11 ou superior
- Maven 3.6 ou superior
- Neo4j Database 4.0 ou superior
- IDE de sua preferência (recomendado: IntelliJ IDEA ou Eclipse)

### Configuração do Neo4j

1. Instale o Neo4j Desktop da [página oficial de download](https://neo4j.com/download/).
2. Crie um novo projeto e inicie uma instância do banco de dados.
3. Configure o `application.properties` do Spring Boot para conectar-se ao banco de dados Neo4j.

```properties
spring.neo4j.uri=bolt://localhost:7687
spring.neo4j.authentication.username=neo4j
spring.neo4j.authentication.password=your_password
