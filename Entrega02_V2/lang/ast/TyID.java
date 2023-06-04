/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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