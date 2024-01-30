# KCPHelper

Ferramenta de apoio ao ambiente KeyCloak CCEE para a obtenção do número de telefone do usuário (agente)
que deverá receber o SMS.

# Configuração da Imagem

Estão disponíveis as seguintes variáveis de ambiente na imagem

Variável|Utilização
--|--
BEARER CODE|Código bearer a ser utilizado na solicitação REST
JDBC_URL| Url com protocolo JDBC para conexão com o banco de dados
JDBC_USERNAME|Usuário de conexão com o banco de dados
JDBC_PASSWORD| Senha de conexão com o banco
SQL_QUERY|Consulta SQL no formato JDBC que recebe como parâmetro o nome do usuário e retorna o número de telefone

Note que a aplicação sempre retornará a primeira coluna da consulta especificada em ```SQL_QUERY``` e ela deve estar no formado a ser retornado ao solicitante, uma vez que a aplicação não realiza nenhuma transformação no resultado como no exemplo abaixo:

```sql
select num_tel from tabela1 where usuario = ?
```

O nome do usuário será sempre injetado como parâmetro. Caso a consulta possua mais de um parâmetro, a aplicação irá falhar.
