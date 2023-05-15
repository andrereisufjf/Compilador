
# Gerando o arquivo de analisador lexico e sintatico
cd lang/parser/
java -jar antlr-4.8-complete.jar lang.g4

cd ..
cd ..

javac -cp ".;antlr-4.8-complete.jar" lang/LangCompiler.java

#javac -cp ".;antlr-4.8-complete.jar" lang/parser/*.java


java -cp ".;antlr-4.8-complete.jar" lang/LangCompiler $1 $2




# Deletando os arquivos gerados automaticamente
cd lang
rm -R parser/*Listener.java parser/langLexer* parser/langParser.java parser/lang.interp parser/lang.tokens
cd ..

# Deletando os arquivos compilados
find . -type f -name "*.class" -delete
find . -type f -name "*~" -delete