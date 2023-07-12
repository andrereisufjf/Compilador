 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
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
