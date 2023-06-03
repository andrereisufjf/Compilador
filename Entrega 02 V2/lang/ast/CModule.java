/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class CModule extends BinOp {

  public CModule(int line, int column, Expr l, Expr r) {
    super(line, column, l, r);
  }


  public void accept(Visitor v) {
    v.visit(this);
  }
}
