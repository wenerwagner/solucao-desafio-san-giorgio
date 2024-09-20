# Documento de Requisitos - Caso de Uso: Avaliação de Candidato a Desenvolvedor Java

## Requisitos Funcionais

### 1. Receber objeto contendo código do vendedor e lista de pagamentos
- [x] Receber o código através do controller.
- [x] Verificar valores nulos e vazios.
  - [ ] Verificar quando não é enviado o request body.
  - [ ] Verificar quando não é enviado client_id.
  - [ ] Verificar quando não é enviado payment_items.
  - [ ] Verificar quando payment_items é uma lista vazia.
  - [ ] Verificar quando não é enviado um payment_id.
  - [ ] Verificar quando não é enviado um payment_value.
- [ ] Verificar valores com formatos errados, diferentes do esperado.
  - [ ] Verificar request body.
  - [ ] Verificar client_id.
  - [ ] Verificar payment_items.
  - [ ] Verificar payment_id.
  - [ ] Verificar payment_value.

### 2. Validar existência do vendedor
O sistema deve verificar se o vendedor informado no objeto existe na base de dados. Caso não exista, o sistema deve retornar uma mensagem de erro informando que o vendedor não foi encontrado.
- [x] Criar abstração de um repositório de dados PaymentRepository.
- [x] Criar ClientNotFoundException.
- [x] Verificar quando o client_id não existe na base.
- [ ] Verificar o erro retornado no controller.

### 3. Validar existência do código da cobrança
Para cada pagamento realizado na lista, o sistema deve verificar se o código da cobrança informado existe na base de dados. Caso não exista, o sistema deve retornar uma mensagem de erro informando que o código da cobrança não foi encontrado.
- [x] Criar PaymentNotFoundException.
- [x] Verificar quando o payment_id não existe na base.
- [ ] Verificar quando o payment_id existe na base, mas não é daquele client_id.
- [ ] Verificar o erro retornado no controller.

### 4. Validar valor do pagamento
O sistema deve comparar o valor do pagamento recebido na requisição com o valor original cobrado, a fim de determinar se o pagamento é parcial, total ou excedente.

### 5. Enviar objeto para fila SQS
De acordo com a situação de pagamento (parcial, total ou excedente), o sistema deve enviar o objeto para uma fila SQS distinta. Essa fila será responsável por processar o objeto de acordo com a situação de pagamento.

### 6. Preencher status de pagamento
Após o processamento do objeto, o sistema deve preencher a informação do status de pagamento no mesmo objeto recebido. Essa informação indicará se o pagamento foi parcial, total ou excedente.

## Requisitos Não Funcionais
Os requisitos não funcionais descrevem características do sistema que não estão diretamente relacionadas às funcionalidades, mas afetam seu desempenho, segurança, usabilidade, entre outros aspectos.

### 1. Teste unitários
O caso de uso deve ser testavel através de testes unitários.

### 2. Tratamento de resposta e status code
O sistema deve retornar uma resposta com status code 200 em caso de sucesso e 4XX em caso de erro.

## Pontos a serem avaliados
- Qualidade do código
- Testes unitários
- Arquitetura
- Abstração

