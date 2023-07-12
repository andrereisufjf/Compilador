 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.langUtil;
import java.util.HashMap;
public class STyData extends SType{
    private String id;
    public HashMap<String, SType> elem = new HashMap<String, SType>();
    public STyData(String i){id = i;}
    public String getId() {return id;}
    @Override
    public boolean match(SType v)
    {
        STyData aux = null; 
        boolean a = false;
        if(v instanceof STyData)
        {
            aux = (STyData)v;
            a = aux.getId() == id;
        }
        return (v instanceof STyNull) || a;
    }

    public boolean matchData(SType v, String i)
    {
        return (v instanceof STyNull) || (v instanceof STyData && i == id);
    }

    public String toString(){
        return "Data: " + id;        
    }
}
