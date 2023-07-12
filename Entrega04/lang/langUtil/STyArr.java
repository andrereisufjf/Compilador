 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.langUtil;

public class STyArr extends SType
{
    private SType a;

    public STyArr(SType t)
    {
        a = t;
    }

    public SType getArg()
    {
        return a;
    }

    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyNull) || (v instanceof STyArr) && (a.match( ((STyArr)v).getArg() ));
    }

    public String toString() {
        return a.toString() + "[]";
    }
}
