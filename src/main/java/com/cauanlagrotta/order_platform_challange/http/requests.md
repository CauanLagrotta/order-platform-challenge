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


