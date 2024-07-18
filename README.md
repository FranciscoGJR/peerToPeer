# Clonar repositório git

ssh.
```
git@github.com:FranciscoGJR/peerToPeer.git
```

https.
```
https://github.com/FranciscoGJR/peerToPeer.git
```

# Ferramentas necessárias

`maven 3.5.2` ou superior.

`java 1.8.0` ou superior.

# Como executar

Para baixar as dependências do maven, executar comando `mvn clean install`.

Em seguida, para complilar o projeto, executar comando `mvn package`.

Para executar o projeto, rode o comando a baixo na raiz do diretório.

```
./ep_distsys <endereco_ip>:<porta> <arquivo_com_vizinhos> <lista_chave_valores>
```

Os parâmetros `arquivo_com_vizinhos` e `lista_chave_valores` são opicionais.

# Decisões de projeto

O paradigma POO foi escolhido como base fundamental para o desenvolvimento do sistema peer-to-peer devido às suas características intrínsecas que se alinham perfeitamente com os requisitos do projeto.

## Organização dos pacetes

Foi utilizado uma implementação de arquitetura em camadas com classes Controller e Model, seguindo os princípios de separação de responsabilidades e organização do código.

```
└── peerToPeer
    ├── Main.java
    ├── No.java
    ├── controller
    │   └── InterfaceUsuario.java
    ├── model
    │   ├── Dados.java
    │   └── ParChaveValor.java
    ├── rede
    │   ├── CaixaMensagens.java
    │   ├── Mensagem.java
    │   ├── Rede.java
    │   └── ThreadComunicacao.java
    └── utils
        ├── Constantes.java
        ├── ThreadComunicacaoUtil.java
        └── TipoMensagemEnum.java
```
