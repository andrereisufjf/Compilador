compile: genparser
	javac -cp .:antlr-4.10.1-complete.jar ast/*.java parser/*.java visitors/*.java LangCompiler.java -d .

genparser: parser/lang.g4
	java -jar antlr-4.10.1-complete.jar parser/lang.g4 -visitor
	
run: 
	java -cp .:antlr-4.10.1-complete.jar lang.LangCompiler ${cmd}
	
clean: 
	rm -rf lang
	rm -R parser/langParser.java
	rm -R parser/langLexer*
	rm -R parser/lang.interp
	rm -R parser/lang.tokens
	rm -R parser/langListener.java
	rm -R parser/langBaseListener.java
	rm -R parser/langBaseVisitor.java
	rm -R parser/langVisitor.java

all: compile run clean