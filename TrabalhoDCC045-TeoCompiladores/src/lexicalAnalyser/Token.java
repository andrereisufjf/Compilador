 /*
  * DCC045 - Teoria dos Compiladores - 2022.1
  *  André Luiz dos Reis - 201965004AC
  *  Lucca Oliveira Schröder - 201765205C
  */

package lexicalAnalyser;

public class Token {
    public int l, c;
    public TOKEN_TYPE t;
    public String lexeme;
    public Object info;
    
    public Token(TOKEN_TYPE t, String lex, Object o ,int l, int c){
          this.t = t;
          lexeme = lex;
          info =  o;
          this.l = l;
          this.c = c;
    }
    
    public Token(TOKEN_TYPE t, String lex,int l, int c){
          this.t = t;
          lexeme = lex;
          info =  null;
          this.l = l;
          this.c = c;
    }
    
    public Token(TOKEN_TYPE t,Object o,int l, int c){
          this.t = t;
          lexeme = "";
          info =  o;
          this.l = l;
          this.c = c;
    }
    
    @Override
    public String toString(){
        if(info == null){
            return lexeme;
        }else{
            return t.name() + ":" + info.toString();
        }
       //return "[("+l+","+ c+ ") "+ t.name()  +" \"" + lexeme + "\" : <" + (info == null ? "" : info.toString()) + ">]";
    }
}

 
