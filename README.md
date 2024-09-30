# Documentação para Rodar o Projeto Localmente

Pré-requisitos
Antes de começar, certifique-se de ter os seguintes itens instalados:

Git - Para clonar o repositório.
JDK 17 - Para rodar o backend Spring Boot.
Node.js (v18.x ou superior) - Para rodar o frontend React.
Maven - Para gerenciar dependências do Spring Boot.
Docker (opcional) - Para rodar a aplicação em containers.
Postman - Para testar as rotas da API.

Passo a Passo para Rodar o Projeto:

1. Clonar o Repositório
Clone o repositório para sua máquina local:

2. Copiar código
git clone https://github.com/stcf29/gerenciador-arquivos

logo em seguida, entrar na pasta raiz do projeto "gerenciador-arquivos"

3. Rodar o Backend Localmente
Para rodar o backend em springboot, foi utilizado a IDE do IntelliJ,, pois nela tanto o JDK 17 quanto a base de dados H2 Database
(esta que roda em memória junto com o Springboot ao executar) já são configuradas ao rodar o projeto.

Ao entrar na IDEA do IntelliJ, basta localizar a pasta "backend" na raiz do projeto, e selecionar a opção 'Run' no canto superior direito da IDE:

![alt text](image-1.png)

O backend estará disponível em http://localhost:8080.

Caso o Maven esteja instalado localmente, basta navegar até o repositório 'backend' e digitar os comandos para executar a aplicação spring:
mvn clean install
mvn spring-boot:run

3.1. Endpoints da API
GET /api/diretorios: Retorna todos os diretórios e seus arquivos.
POST /api/diretorios: Cria um novo diretório.
PUT /api/diretorios/{id}: Atualiza um diretório existente.
DELETE /api/diretorios/{id}: Remove um diretório.

4. Rodar o frontend localmente

Para rodar o frontend, foi utilizado o Visual Studio Code. Basta navegar até a pasta "front-rep-arquivos", abrir o terminal do Visual studio Code e digitar o comando npm run dev para executar
o frontend do projeto, o mesmo estará disponível em 'http://localhost:5173/'. Vale ressaltar que o frontend já está configurado para apontar para o backend.

5. Rodar com Docker (Opcional)
Se preferir rodar a aplicação com Docker, siga os passos abaixo.

5.1 Build das Imagens Docker
No diretório raiz do projeto, execute o seguinte comando para construir as imagens do backend e frontend:
docker-compose up --build

Após o build, inicie os containers com:
docker-compose up

O backend estará disponível em http://localhost:8080.
O frontend estará disponível em http://localhost:5174.

6. Testar a API
Você pode testar os endpoints da API utilizando Postman.

Será disponibilizado na pasta raiz do projeto a coleção do Postman 'Gerenciar-arquivos.postman_collection' com as requisições já prontas para serem executadas, para que as mesmas 
sejam executadas nos testes.

6.1. Exemplos de requisição para testes.
6.1.1 requisição Post sem subdiretorio:

{
    "nome": "Documentos",
    "parentDirectory": null,
    "files": [
        {
            "nome": "Arquivo1.txt",
            "tamanho": "15KB"
        }

    ]
}

6.1.2 requisição Post com subdiretório:
{
    "nome": "Documentos_teste",
    "parentDirectory": null,
    "subDirectories": [
        {
            "nome": "Subdiretório 1",
            "files": [{
            "nome": "Arquivo1.txt",
            "tamanho": "15KB"
        }] // ou uma lista de arquivos, se necessário
        }],
    "files": [
        {
            "nome": "Arquivo1.txt",
            "tamanho": "15KB"
        }
    ]
}

Os endpoints da api se encontram no tópico 3.1


7. Rodando os Testes
Testes Backend
Para rodar os testes no backend (Spring Boot), use o seguinte comando na pasta 'backend':

mvn test

Testes Frontend
No frontend (React), você pode rodar os testes com o comando na pasta 'front-rep-arquivos':
npm test

Seguindo estes passos, a aplicação será executada localmente.