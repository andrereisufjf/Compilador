 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class Return extends Cmd{

  private int line, column;
  private Expr[] exp;

  public Return(int line, int column, Expr[] exp) {
    this.line = line;
    this.column = column;
    this.exp = exp;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Expr[] getExpressions() {
    return exp;
  }

  public void accept(Visitor v) { 
		v.visit(this);
  }
}