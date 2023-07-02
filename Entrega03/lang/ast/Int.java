 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class Int extends Expr {

  private int line, column;
  private int v;

	public Int(int line, int column, int v) {
    this.v = v;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public int getValue() {
    return v;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
