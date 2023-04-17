 /*
  *  DCC045 - Teoria dos Compiladores
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */

package lexicalAnalyser;

import java.io.FileReader;
import java.io.IOException;

public class Main{
    
    /*
     * arg [0] = Nome do arquivo a ser processado
    */

     public static void main(String args[]) throws IOException, Exception{

         try {

             String fileName = "";

             // valida a passagem de parametro
             if(args.length != 0){
                 fileName = args[0];
             }else{
//            fileName = "testes/exemplo1.txt";
             }

             if(fileName.isEmpty()){
                 throw new Exception("Informe o nome do arquivo a ser processado!");
             }

             // criação do classe do Analisador Lexico
             LexicalAnalyserLang lx = new LexicalAnalyserLang(new FileReader(fileName));
             Token t = lx.nextToken();

             // processamento do arquivo e busca por novos tokens
             while(t != null){
                 System.out.println(t.toString());
                 t= lx.nextToken();
             }

             // informe do total de tokens lidos
             //System.out.println("Total de tokens lidos " + lx.readedTokens());

         }catch (Exception e){
             System.out.println(e);
         }

     }
}
