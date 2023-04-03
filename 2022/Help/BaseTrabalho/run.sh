
# Gerando o arquivo de analisador lexico e sintatico
cd ./lang/parser
java -jar antlr-4.8-complete.jar lang.g4
cd ..
cd ..


# TODO

# Deletando os arquivos gerados automaticamente
#rm -R parser/*Listener.java parser/langLexer* parser/langParser.java parser/lang.interp parser/lang.tokens

# Deletando os arquivos compilados
#find . -type f -name "*.class" -delete
#find . -type f -name "*~" -delete