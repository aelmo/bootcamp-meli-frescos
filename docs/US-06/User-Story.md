# Requisito 6

## Produtos Best-Sellers 

__Como__ Representante <br>
__Quero__ saber quais são os produtos mais vendidos (por quantidade) <br>
__Para__ fazer análises e projeções de vendas 


__Dado que__ existem produtos vendidos <br>
__Quando__ o representante solicita a lista <br>
__Então__ retorna os top 3 produtos mais vendidos ordenados por quantidade vendida <br>

__Validação__:
Autentique-se como representante e acesse o endpoint. <br>
O endpoint deve retornar o id, nome do produto e quantidade vendida.

# Representação JSON

## Response

```
[
    {
        "productId": 2,
        "quantity": 30,
        "productName": "Apple"
    },
    {
        "productId": 3,
        "quantity": 22,
        "productName": "Potato"
    },
    {
        "productId": 1,
        "quantity": 3,
        "productName": "Banana"
    }
]
```

# Contratos referentes a User Story

| HTTP | Modelo URI | Descrição  | Code |
|---|---|---|---|
| GET  | /api/v1/fresh-prducts/best-sellers/?numberOfProducts  | Obtenha os produtos mais vendidos  |  ml-best-sellers | 

Obs: caso o parâmetro (numberOfProducts) não seja escolhido, serão retornados os 3 produtos mais vendidos. 
