 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class Data extends SuperNode
{
  private int line, column;
  private String id;
  private Decl[] types;

  public Data(int line, int column, String id, Decl[] types) {
    this.line = line;
    this.column = column;
    this.id = id;
    this.types = types;
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

  public Decl[] getTypes() {
    return types;
  }


  public void accept(Visitor v) { 
		v.visit(this); 
	}
}