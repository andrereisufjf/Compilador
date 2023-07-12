 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.langUtil;

public class STyChar extends SType{
    private static STyChar st = new STyChar();

    private STyChar(){}

    public static STyChar newSTyChar(){ return st; }


    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyChar);
    }

    public String toString(){
        return "Char";
    }
}
