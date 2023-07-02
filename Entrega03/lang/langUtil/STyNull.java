 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.langUtil;

public class STyNull extends SType{
    private static STyNull st = new STyNull();

    private STyNull(){}

    public static STyNull newSTyNull(){ return st; }


    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyNull) || (v instanceof STyArr) || (v instanceof STyData);
    }

    public String toString(){
        return "Null";
    }
}
