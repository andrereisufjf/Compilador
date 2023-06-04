/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
