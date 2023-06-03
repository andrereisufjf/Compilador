 /*
  * DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004A
  *  Lucca Oliveira Schröder - 201765205C
  */

 package lang.visitors;

 public interface Visitable {
     public void accept(Visitor v);
 }