 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */

package lang.ast;

import lang.visitors.Visitable;

public abstract class SuperNode implements Visitable {
    public SuperNode() { }
    public abstract int getLine();
    public abstract int getColumn();
}


