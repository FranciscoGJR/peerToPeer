#!/bin/sh
echo "Iniciando peer com argumentos: $PEER_ARGS"
exec java -jar app.jar $PEER_ARGS
