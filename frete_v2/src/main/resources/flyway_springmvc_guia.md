
# 🛠️ Como Adicionar FlywayDB em um Projeto Spring MVC

Este guia explica como adicionar o FlywayDB a um projeto **Spring MVC** para gerenciar a versão da base de dados, usando como exemplo a criação da tabela `cliente`.

---
## ✅ Etapa 1: Adicionar a dependência Flyway

No arquivo `pom.xml`, adicione:

```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-core</artifactId>
</dependency>
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-mysql</artifactId>
</dependency>
```

---

## ✅ Etapa 2: Configurar o Banco de Dados

No arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seubanco?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=seuusuario
spring.datasource.password=suasenha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Habilita o Flyway
spring.flyway.enabled=true

# Local padrão das migrations
spring.flyway.locations=classpath:db/migration
```

> Substitua `seubanco`, `seuusuario` e `suasenha` pelos seus dados reais.

---

## ✅ Etapa 3: Criar a Migration da Tabela `cliente`

Crie o arquivo `V1__create_table_cliente.sql` em:
```
src/main/resources/db/migration/
```

Conteúdo:

```sql
 CREATE TABLE cliente (
         id integer not null auto_increment,
         nome varchar(255) not null,
         endereco varchar(255),
         telefone varchar(20) not null,
         primary key (id)
    )
```

> Use a convenção de nome: `V1__nome_descritivo.sql`: V01__cria_tabela_cliente.sql
> (o número indica a versão e os underlines duplos separam a versão do nome).

---

## ✅ Etapa 4: Rodar a Aplicação

Ao iniciar a aplicação Spring:

- O Flyway cria automaticamente a tabela `flyway_schema_history` no banco.
- A migration `V1__create_table_cliente.sql` é executada.
- A versão é registrada na tabela de histórico.

---

## 🧪 Verificação

Verifique no banco:

- A existência da tabela `cliente`.
- A existência da tabela `flyway_schema_history` com registro da migration.

---
## ⚠️ Boas Práticas

- **Nunca edite uma migration já aplicada.**  
  Para mudanças, crie uma nova migration (ex: `V2__add_column_email.sql`).

- Evite a exclusão ou alteração da tabela `flyway_schema_history`.

- Use versionamento de banco de dados junto com controle de versão do código (Git).

---

## ✅ Exemplo de Próxima Migration

```sql
-- V2__add_column_email.sql
ALTER TABLE cliente ADD COLUMN email VARCHAR(255);
```
