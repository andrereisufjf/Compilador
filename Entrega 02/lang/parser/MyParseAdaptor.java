 /*
  * DCC045 - Teoria dos Compiladores - 2022.1
  *  André Luiz dos Reis - 201965004AC
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.parser;

import java.io.*;
import lang.ast.SuperNode;
import lang.ast.MySuperNode;
import lang.parser.*;

import java.util.List;
import java.util.HashMap;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Parser;


// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 

public class MyParseAdaptor implements ParseAdaptor{
   public SuperNode parseFile(String path){
	   
	   try{
		// Create a ANTLR CharStream from a file
		CharStream stream = CharStreams.fromFileName(path);
		// create a lexer that feeds off of stream
		langLexer lex = new langLexer(stream);
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lex);
		// create a parser that feeds off the tokens buffer
		langParser parser = new langParser(tokens);
	   
	   //ParseTree tree = parser.prog();
	   
	   
	   
		// cria instancia do objeto de erro
		SyntaxError syntaxError = new SyntaxError();           
		
		//remove ConsoleErrorListener
//		parser.removeErrorListeners();
//		lex.removeErrorListeners();
		
		// adiciona obj para guardar os erros
//		parser.addErrorListener(syntaxError);
//		lex.addErrorListener(syntaxError);
		
		// roda as validações
		final ParseTree tree = parser.prog();
		
//		//valida se ha um erro, retorna null em caso positivo
//		if(syntaxError.isError() == true){
//			return null;
//			//System.out.println("ERRO - REIS");
//		}

		   //valida se ha um erro, retorna null em caso positivo
		   if(parser.getNumberOfSyntaxErrors() != 0){
			   return null;
			   //System.out.println("ERRO - REIS");
		   }
	   
	   // se nao houver erros, retorna um SuperNOde
		return new MySuperNode();
	   }catch(Exception e){
           e.printStackTrace();
       }
	   
	   return null;
	   
	   
   };
   
}



