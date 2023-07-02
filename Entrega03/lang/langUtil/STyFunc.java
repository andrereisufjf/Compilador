 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.langUtil;

public class STyFunc {
    private SType[] retorno;
    private SType[] parametro;
    private String id;
    public SType[] getRetorno() {
        return retorno;
    }
    public SType[] getParametro() {
        return parametro;
    }
    public String getId() {
        return id;
    }
    public STyFunc( String id, SType[] p, SType[] retorno) {
        this.id = id;
        this.retorno = retorno;
        this.parametro = p;
    }
}
