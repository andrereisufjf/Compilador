 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class TyID extends BType {
  private int line, column;
  private String t;

  public TyID(int line, int column, String t) {
    this.line = line;
    this.column = column;
    this.t = t;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getIdType() {
    return t;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}