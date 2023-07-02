 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class Decl extends SuperNode {

	private int line, column;
  private String id;
  private Type t;

  public Decl(int line, int column, String id, Type t) {
    this.line = line;
    this.column = column;
    this.id = id;
    this.t = t;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getId() {
    return id;
  }

  public Type getType() {
    return t;
  }
  
  public void accept(Visitor v) { 
		v.visit(this); 
	}
}
