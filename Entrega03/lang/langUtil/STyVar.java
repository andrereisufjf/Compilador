 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.langUtil;

public class STyVar extends SType{
    private static STyVar st = new STyVar();

    private STyVar(){}

    public static STyVar newSTyVar(){ return st; }


    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyVar);
    }

    public String toString(){
        return "Var";
    }
}
