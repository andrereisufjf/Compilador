# ------------
# Arquivo de build para compila cao do fonte
#
# DCC045 - Teoria dos Compiladores
# André Luiz dos Reis - 201965004C
# Lucca Oliveira Schröder - 201765205C
# ------------

# Gerando o arquivo de analisador lexico e sintatico
cd lexicalAnalyser
java -jar jflex-full-1.8.2.jar ArquivoJFlexLang.jflex

# cd ..
# cd ..

javac -cp . *.java

cd ..

java lexicalAnalyser/Main $1

cd lexicalAnalyser

# javac -cp ".;antlr-4.8-complete.jar" lang/LangCompiler.java

#javac -cp ".;antlr-4.8-complete.jar" lang/parser/*.java


# java -cp ".;antlr-4.8-complete.jar" lang/LangCompiler $1 $2



# Deletando os arquivos gerados automaticamente
# cd lang
# rm -R parser/*Listener.java parser/langLexer* parser/langParser.java parser/lang.interp parser/lang.tokens
# cd ..

rm -R LexicalAnalyserLang.java

# Deletando os arquivos compilados
find . -type f -name "*.class" -delete
find . -type f -name "*~" -delete