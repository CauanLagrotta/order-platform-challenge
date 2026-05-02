# Requests para os endpoints (pelo terminal)

## Customers
- Criar customer
```bash
http post http://localhost:8080/customers name=cauan email=cauan@email.com
```

---

## Products
- Criar product
```bash
http post http://localhost:8080/products name="mouse gamer" price=99.99 stockQuantity=2
```

- Listar products
```bash
http get "http://localhost:8080/products?page=0&pageSize=10"
```

- Buscar product por id
```bash
http get http://localhost:8080/products/{id}
```

---

## Orders
- Criar order
```bash
http post http://localhost:8080/orders customerId=83f0293d-6dab-4a76-a954-2029782d0705 productId=2340f705-fae8-4a83-8471-1a3e734d391f quantity:=2
```

- Buscar order por id
```bash
http get http://localhost:8080/orders/7c942e79-4fb5-49f1-8ac9-91724761fa24
```

- Listar order por customer id
```bash
http get "http://localhost:8080/orders?customerId=83f0293d-6dab-4a76-a954-2029782d0705&page=0&pageSize=10"
```
