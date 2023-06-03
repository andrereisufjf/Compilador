/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
