/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
