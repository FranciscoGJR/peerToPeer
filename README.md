Busca peer-to-peer sobre valores chave-valor

# Arquitetura

## Sistema (componente)

Atributos

- Tabela com chave valor
- Lista com mensagens enviadas (model.mensagem)
- Lista de vizinhos conhecidos

Métodos

- Listar vizinhos
- Adicionar novo vizinho
- Vizinho conhecido

- Hello: enviar a mensagem Hello para aglum vizinho específico
- Enviar mensagem
- Receber mensagem
- Repassar mensagem
- Enviar resposta

## Mensagem (model)

- Origem
- Número de sequencia 
- Tempo de vida
- Operação
- Argumentos (opcionais)



# Funcionalidades

- Realizar busca de um valor chave e ter resposta



# Métodos de busca

1. Flooding
2. Random walk
3. Busca em profundidade

## Cenários

- Tentando adicionar vizinho `<endereço>:<porta>`
- Encaminhando mensagem `<mensagem>` para `<endereço:porta destino>`
- Envio feito com sucesso: `<mensagem>`


# Interfaces

Identificador do nó

```
<endereço>:<porta>
```

Inicializacao

```
./ep_distsys <endereco>:<porta> [vizinhos.txt [lista_chave_valor.txt]]
```

**Interface de usuário**: terminal

**Comunicaçao**:
````
<ORIGIN> <SEQNO> <TTL> <OPERACAO>[ ARGUMENTOS]\n
````



# Chave-valor dos nós

**Chave**: String chave
**Valor**: Long valor

````
Katharine_Hepburn 4
Frances_McDormand 3
````

Um espaço em branco separa a chave do valor

