 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.langUtil;

public class STyBool extends SType
{
    private static STyBool st = new STyBool();

    private STyBool(){}

    public static STyBool newSTyBool(){ return st; }


    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyBool);
    }

    public String toString(){
        return "Bool";
    }
}
