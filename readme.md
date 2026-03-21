# 🛒 Orders Platform Challenge

Plataforma simplificada de e-commerce com processamento assíncrono de pedidos via RabbitMQ, controle de estoque transacional e resiliência com Dead Letter Queue.

---

## 🚀 Tecnologias

- Java 21
- Spring Boot 3.4.x
- Spring Web
- Spring Data JPA
- Spring AMQP (RabbitMQ)
- PostgreSQL
- Lombok
- Validation
- springdoc-openapi
- Testcontainers
- Docker Compose

---

## ✅ Checklist de Desenvolvimento

### 🐳 Infraestrutura

- [x] `docker-compose.yml` com PostgreSQL e RabbitMQ
- [x] `application.properties` com datasource, JPA e configurações do RabbitMQ

---

### 🗂️ Entidades

- [x] `Customer` (id, name, email)
- [x] `Product` (id, name, price, stockQuantity) com `@Version` para Optimistic Locking
- [x] `Order` (id, customerId, status, createdAt)
- [x] `OrderItem` (id, orderId, productId, quantity, unitPrice)
- [x] Enum `OrderStatus` (`PENDING`, `PROCESSING`, `CONFIRMED`, `SHIPPED`, `CANCELLED`)

---

### 📦 DTOs

- [x] `CustomerRequestDTO` / `CustomerResponseDTO`
- [ ] `ProductRequestDTO` / `ProductResponseDTO`
- [ ] `OrderRequestDTO` (customerId, List\<OrderItemDTO\>)
- [ ] `OrderItemDTO` (productId, quantity)
- [ ] `OrderResponseDTO` (id, customerId, status, items)

---

### 🗄️ Repositórios

- [x] `CustomerRepository`
    - [x] `existsByEmail(String email)`
- [ ] `ProductRepository`
- [ ] `OrderRepository`
    - [ ] `findByCustomerId(UUID customerId)`
- [ ] `OrderItemRepository`

---

### ⚙️ Serviços

- [x] `CustomerService`
    - [x] `create` — valida e-mail único, salva cliente
- [ ] `ProductService`
    - [ ] `create` — cadastra produto com estoque inicial
    - [ ] `findAll` — lista com paginação
    - [ ] `findById` — busca por ID
- [ ] `OrderService`
    - [ ] `create` — valida cliente e produtos, salva pedido como `PENDING`, publica na fila
    - [ ] `findById` — busca pedido com itens
    - [ ] `findByCustomer` — lista pedidos de um cliente
- [ ] `StockService`
    - [ ] `reserve` — reserva estoque de todos os itens atomicamente (`@Transactional`)
    - [ ] `rollback` — reverte reserva em caso de falha

---

### 🌐 Controllers

- [x] `CustomerController`
    - [x] `POST /customers`
- [ ] `ProductController`
    - [ ] `POST /products`
    - [ ] `GET /products` (paginado)
    - [ ] `GET /products/{id}`
- [ ] `OrderController`
    - [ ] `POST /orders`
    - [ ] `GET /orders/{id}`
    - [ ] `GET /orders?customerId=uuid`

---

### 📨 Mensageria (RabbitMQ)

- [ ] `RabbitMQConfig.java`
    - [ ] Declarar exchange, filas e bindings
    - [ ] Fila `orders.created`
    - [ ] Fila `orders.confirmed`
    - [ ] Fila `orders.failed` (DLQ)
    - [ ] Configurar TTL e max-retries para a DLQ
- [ ] `OrderPublisher.java`
    - [ ] `publishOrderCreated(OrderMessage message)`
    - [ ] `publishOrderConfirmed(OrderMessage message)`
- [ ] `OrderCreatedConsumer.java`
    - [ ] Lê fila `orders.created`
    - [ ] Tenta reservar estoque via `StockService`
    - [ ] Sucesso → status `CONFIRMED` + publica em `orders.confirmed`
    - [ ] Falha → status `CANCELLED` + rollback de estoque
- [ ] `OrderConfirmedConsumer.java`
    - [ ] Lê fila `orders.confirmed`
    - [ ] Aguarda 5 segundos e muda status para `SHIPPED`

---

### 🛡️ Resiliência

- [ ] Dead Letter Queue — após 3 falhas a mensagem vai para `orders.failed`
- [ ] Consumer da DLQ cancela o pedido automaticamente
- [ ] Idempotência — reprocessar a mesma mensagem não duplica ações (verificar status antes de processar)
- [ ] Optimistic Locking com `@Version` na entidade `Product`

---

### ❌ Tratamento de Erros

- [x] `RestExceptionHandler` com `@RestControllerAdvice`
    - [ ] `EntityNotFoundException` → 404
    - [x] `EmailAlreadyExistsException` → 409
    - [ ] `InsufficientStockException` → 422
    - [x] `MethodArgumentNotValidException` → 400
    - [ ] `OptimisticLockingFailureException` → 409
    - [x] `HttpMessageNotReadableException` → 422

---

### ⭐ Diferenciais

- [ ] Swagger com todos os endpoints documentados (springdoc-openapi)
- [ ] Outbox Pattern — salvar evento em tabela `outbox` antes de publicar no RabbitMQ
    - [ ] Entidade `OutboxEvent` (id, aggregateId, type, payload, sentAt)
    - [ ] Scheduler que lê eventos pendentes e publica na fila

---

### 🧪 Testes

- [ ] Unitários do `StockService` com falha parcial (Mockito)
- [ ] Unitários do `OrderService` (Mockito)
- [ ] Teste do `OrderCreatedConsumer` simulando falha em um item do pedido
- [ ] Teste de integração do fluxo completo com Testcontainers
    - [ ] Criar pedido → consumir fila → verificar estoque decrementado → status `CONFIRMED`
    - [ ] Criar pedido sem estoque → verificar status `CANCELLED` e estoque intacto

---

## 📁 Estrutura do Projeto

```
src/main/java/com/.../
├── config/
│   └── RabbitMQConfig.java
├── controller/
│   ├── CustomerController.java
│   ├── ProductController.java
│   └── OrderController.java
├── consumer/
│   ├── OrderCreatedConsumer.java
│   └── OrderConfirmedConsumer.java
├── publisher/
│   └── OrderPublisher.java
├── service/
│   ├── CustomerService.java
│   ├── ProductService.java
│   ├── OrderService.java
│   └── StockService.java
├── repository/
├── entity/
│   ├── enums/
│   │   └── OrderStatus.java
│   ├── Customer.java
│   ├── Product.java
│   ├── Order.java
│   └── OrderItem.java
├── dto/
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── RestExceptionHandler.java
│   └── EmailAlreadyExistsException.java
└── OrdersPlatformApplication.java
```

---

## 🗺️ Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/customers` | Criar cliente |
| `POST` | `/products` | Criar produto |
| `GET` | `/products` | Listar produtos (paginado) |
| `GET` | `/products/{id}` | Buscar produto |
| `POST` | `/orders` | Criar pedido |
| `GET` | `/orders/{id}` | Buscar pedido |
| `GET` | `/orders?customerId=uuid` | Listar pedidos do cliente |
| `GET` | `/api-docs.html` | Swagger UI |

---

## 🔄 Fluxo do Pedido

```
POST /orders
     ↓
Salva com PENDING
     ↓
Publica em orders.created
     ↓
OrderCreatedConsumer
     ├── Estoque OK → CONFIRMED → publica em orders.confirmed
     └── Estoque falhou → CANCELLED

orders.confirmed
     ↓
OrderConfirmedConsumer
     ↓
Aguarda 5s → SHIPPED
```

---

## ▶️ Como Rodar

```bash
# Subir PostgreSQL e RabbitMQ
docker-compose up -d

# Rodar a aplicação
./mvnw spring-boot:run
```

- Swagger: [http://localhost:8080/api-docs.html](http://localhost:8080/api-docs.html)
- RabbitMQ Admin: [http://localhost:15672](http://localhost:15672) (guest / guest)

---

## 💡 Pontos para Entrevista

- **Por que RabbitMQ em vez de processar direto?** Desacopla a criação do pedido do processamento, permite retry e não bloqueia o cliente esperando a reserva de estoque.
- **O que é Optimistic Locking?** Usa `@Version` para detectar atualizações concorrentes sem travar o banco — se dois consumers tentarem decrementar o mesmo produto ao mesmo tempo, um deles recebe exception e faz retry.
- **O que é DLQ?** Dead Letter Queue — fila que recebe mensagens que falharam N vezes, evitando loop infinito e permitindo análise posterior.
- **Por que Outbox Pattern?** Garante que o evento só é publicado no RabbitMQ se a transação do banco confirmar — evita pedido salvo sem mensagem publicada (ou vice-versa).
- **O que é idempotência?** A capacidade de processar a mesma mensagem mais de uma vez sem efeitos colaterais — fundamental em sistemas de mensageria onde reentregas são esperadas.