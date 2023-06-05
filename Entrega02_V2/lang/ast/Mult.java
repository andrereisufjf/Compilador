 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class Mult extends BinOp {

  public Mult(int line, int column, Expr l, Expr r){
    super(line, column, l, r);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
