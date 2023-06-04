/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class New extends Expr {

  private int line,  column;
  private Type t;
  private Expr exp;

  public New(int line, int column, Type t, Expr exp) {
    this.line = line;
    this.column = column;
    this.t = t;
    this.exp = exp;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Type getType() {
    return t;
  }

  public Expr getExpression() {
    return exp;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
