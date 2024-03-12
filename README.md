# ServeRest API Testes Automatizado
## Como rodar o projeto
Tenha a imagem do docker:
```bat
    docker run -p 3000:3000 paulogoncalvesbh/serverest:latest
```
Cadastre no end point de usuario o usuario:
```json
{
  "nome": "Funcionario",
  "email": "funcionario@funcionario.com",
  "password": "teste",
  "administrador": "false"
}
```