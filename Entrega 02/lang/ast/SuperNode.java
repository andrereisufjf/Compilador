 /*
  * DCC045 - Teoria dos Compiladores - 2022.1
  *  André Luiz dos Reis - 201965004AC
  *  Lucca Oliveira Schröder - 201765205C
  */

package lang.ast;

 import lang.visitors.Visitable;
public abstract class SuperNode implements Visitable  {
   
   // The line and column of the node in the input text
   
    public abstract int getLine();
    public abstract int getColumn();

    public void accept(Visitor v) {
        // do nothing
        // v.visit(this);
    }
}


