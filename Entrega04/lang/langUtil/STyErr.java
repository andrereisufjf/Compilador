 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
  
package lang.langUtil;

public class STyErr extends SType {
     
      private static STyErr st = new STyErr();
     
     private STyErr(){}
     
     public static STyErr newSTyErr(){ 
          return st; 
     }
     
     public boolean match(SType v){
          return true;
     }
     
     public String toString(){
         return "TyError";
     }
}
