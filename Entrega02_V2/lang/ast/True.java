 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class True extends Expr {

  private int line, column;

  public True(int line, int column) {
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public boolean getValue() {
    return true;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
