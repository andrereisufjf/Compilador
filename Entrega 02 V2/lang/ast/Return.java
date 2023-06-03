/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Return extends Cmd{

  private int line, column;
  private Expr[] exp;

  public Return(int line, int column, Expr[] exp) {
    this.line = line;
    this.column = column;
    this.exp = exp;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Expr[] getExpressions() {
    return exp;
  }

  public void accept(Visitor v) { 
		v.visit(this);
  }
}