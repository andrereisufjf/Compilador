 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class FloatV extends Expr {
	
	private int line, column;
  private float v;

  public FloatV(int line, int column, float v) {
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

  public float getValue() {
    return v;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
