/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Char extends Expr {

  private int line, column;
  private char v;

  public Char(int line, int column, char v) {
    this.line = line;
    this.column = column;
    this.v = v;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public char getValue() {
    return v;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
