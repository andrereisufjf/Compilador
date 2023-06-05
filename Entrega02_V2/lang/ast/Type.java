 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class Type extends SuperNode {
	
	private int line, column;
  private BType t;
  private int braces;

  public Type(int line, int column, BType t, int braces) {
    this.t = t;
    this.braces = braces;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public BType getBtype() {
    return t;
  };

  public int getBraces() {
		return braces;
  };

  public void accept(Visitor v) {
    v.visit(this);
  }
}
