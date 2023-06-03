package lang.visitors;

import java.io.*;
import lang.ast.SuperNode;
import lang.parser.*;
import java.util.List;

// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro.

public class TestVisitor {
    private ParseAdaptor adp;
    private String okSrcs = "../testes/semantica/certo/";
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
                    SuperNode node = adp.parseFile(s.getPath());
                    if(node != null){
                        System.out.println("Output: ");
                        Visitor v = new InterpretVisitor();
                        node.accept(v);
                        flips++;
                    }
                    System.out.println("----------------------------");
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