

cd lang/parser/

java -jar ../antlr-4.10.1-complete.jar lang.g4 -visitor

cd ..
cd ..

# Gerando o arquivo de analisador lexico e sintatico
# cd lang/parser/
# java -jar antlr-4.8-complete.jar lang.g4

javac -cp ".;antlr-4.10.1-complete.jar;ST-4.3.1.jar" lang/ast/*.java lang/parser/*.java lang/visitors/*.java lang/langUtil/*.java lang/LangCompiler.java -d .

# Compilando as classes
# javac -cp ".;antlr-4.8-complete.jar" lang/parser/*.java
# javac -cp ".;antlr-4.8-complete.jar" lang/visitors/*.java
# javac -cp ".;antlr-4.8-complete.jar" lang/ast/*.java
# javac -cp ".;antlr-4.8-complete.jar" lang/LangCompiler.java
#
#  DCC045 - Teoria dos Compiladores - 2022.1
#  André Luiz dos Reis - 201965004AC
#  Lucca Oliveira Schröder - 201765205C
#

# Dentro da pasta Entrega 02 V2
# ./run.sh -bs

java -cp ".;antlr-4.10.1-complete.jar;ST-4.3.1.jar" lang/LangCompiler $1 $2

# javac -cp ".;antlr-4.8-complete.jar" lang/parser/*.java


# java -cp ".;antlr-4.8-complete.jar" lang/LangCompiler $1 $2




# Deletando os arquivos gerados automaticamente
cd lang
rm -R parser/*Listener.java parser/langLexer* parser/langParser.java parser/lang.interp parser/lang.tokens
cd ..

# Deletando os arquivos compilados
find . -type f -name "*.class" -delete
find . -type f -name "*~" -delete