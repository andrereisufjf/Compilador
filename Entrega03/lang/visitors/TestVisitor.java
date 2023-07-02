 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  

package lang.visitors;

import java.io.*;
import lang.ast.SuperNode;
import lang.parser.*;
import java.util.List;

// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 

public class TestVisitor {
    private ParseAdaptor adp;
    private String okSrcs = "testes/semantica/certo/";
    private File f;
   
    public TestVisitor(ParseAdaptor adp){
        this.adp = adp;
        f = new File(okSrcs);
        runOkTests();
    }
   
    private String filler(int n){
        String s = "";
        for(int i =0; i< n; i++){ s += " "; }
        return s;
    }
   
    public void runOkTests(){
        File inst[];
        int flips, flops;
        flips = 0;
        flops = 0;
        try{
            if( f.isDirectory() ){
                String pth;
                inst = f.listFiles();
                for(File s : inst){
                    pth = s.getPath();
                    System.out.println("\nTestando: " + pth + filler(50 -pth.length()));
					SuperNode result = adp.parseFile(s.getPath());
                    if(result != null){
                        System.out.println("Parser: [  OK  ]");
                        TypeCheckVisitor t = new TypeCheckVisitor();
                        result.accept(t);
                        if(t.getNumErrors() > 0){  
                            System.out.println("Type: [FALHOU]"); 
                            t.printErrors();
                            flops++;
                        } else {
                            System.out.println("Type:   [  OK  ]");
                            System.out.println("Output: ");
                            InterpretVisitor v = new InterpretVisitor();
                            result.accept(v);
                            flips++;
                        }
                    }
					System.out.println("\n----------------------------");
                }
                System.out.println("Total de acertos: " + flips );
                System.out.println("Total de erros: " + flops );
            } else {
                System.out.println("O caminho " + f.getPath() + " não é um diretório ou não existe.");
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
   }
}