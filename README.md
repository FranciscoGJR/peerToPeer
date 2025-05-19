## Ferramentas necessárias


`maven 3.5.2` ou superior.

`java 1.8.0` ou superior.


## Compilar e executar o código
A princípio, baixe as dependências do maven ao executar o comando abaixo:

```
mvn clean install
```

Em seguida, para complilar o projeto, executar comando abaixo:

```
mvn package
```

Para executar o projeto, rode o comando a baixo na raiz do diretório:

```
java -jar target/peerToPeer-0.0.1-SNAPSHOT.jar <endereco_ip>:<porta> <arquivo_com_vizinhos> <diretorio_compartilhado>
```

   Onde:
   
   - `<endereço:porta>` é o endereço IP e porta que este peer usará (ex: 127.0.0.1:8080)
       
   - `<arquivo_vizinhos>` é o caminho para um arquivo contendo a lista de vizinhos iniciais (um por linha, formato IP:porta)
       
   - `<diretório_compartilhado>` é o caminho para o diretório que este peer compartilhará com a rede
