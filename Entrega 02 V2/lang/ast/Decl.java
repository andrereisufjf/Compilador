/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
