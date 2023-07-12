 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.langUtil;

public class STyInt extends SType
{
    private static STyInt st = new STyInt();

    private STyInt(){}

    public static STyInt newSTyInt() { return st; }

    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyInt);
    }

    public String toString()
    {
        return "Int";
    }
}
