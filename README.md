# Clonar repositório git
---

ssh:

```
git@github.com:FranciscoGJR/peerToPeer.git
```

https:

```
https://github.com/FranciscoGJR/peerToPeer.git
```


# Ferramentas necessárias
---

`docker`


# Como executar
---

Construir imagem docker:

```
docker build -t eachare .
```

Executar container:

```
docker run eachare <endereco_ip>:<porta>
```

# Decisões de projeto

O paradigma POO foi escolhido como base fundamental para o desenvolvimento do sistema peer-to-peer devido às suas características intrínsecas que se alinham perfeitamente com os requisitos do projeto.

## Organização dos pacetes

Foi utilizado uma implementação de arquitetura em camadas com classes Controller e Model, seguindo os princípios de separação de responsabilidades e organização do código.

```
└── eachare
```
