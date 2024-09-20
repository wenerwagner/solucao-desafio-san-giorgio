## Esse projeto é a implementação da solução para o [Desafio San Giorgio](Desafio-San-Giorgio.md)
### Algumas considerações
- Atualizei o Java para o 21 (LTS atual).
- Atualizei todas as dependências usadas para a versão mais recente.
- Fiz testes unitários apenas para o domain. Num projeto real, faria testes para o resto da aplicação.
- Uma vez que não foi especificado sobre o database/repositório, eu fiz uma implementação mockada, suficiente para execução da aplicação. Uma vez que foi aplicada Clean Architecture, seria simples de implementar um JPA ou algo assim.
- A implementação da SQS está funcionando. Para isso é necessário preencher o [application.properties](\san-giorgio-api\src\main\resources\application.properties) com os acessos e informações necessários. Para correto funcionamento é necessário fazer a correta configuração de quem pode acessar a queue.
- Separei a aplicação em dois módulos `san-giorgio-api` e `san-giorgio-domain` para explicitar a separação entre o domain, dessa forma a API tem como dependência o domain, mas não o contrário.
- Apliquei os principios do Clean Architecture no projeto.
- Utilizei o SonarLint para verificar a qualidade do código.
