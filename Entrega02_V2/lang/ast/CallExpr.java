/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class CallExpr extends Expr {

  private int line, column;
  private String n;
  private Expr[] exps;
  private Expr ret;

  public CallExpr(int line, int column, String n, Expr[] exps, Expr ret) {
    this.line = line;
    this.column = column;
    this.n = n;
    this.ret = ret;
    this.exps = exps;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getName() {
    return n;
  }

  public Expr[] getExpressions() {
    return exps;
  }

  public Expr getReturn() {
    return ret;
  }


  public void accept(Visitor v) {
    v.visit(this);
  }
}
