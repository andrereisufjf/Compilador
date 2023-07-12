 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class Program extends SuperNode {

  private int line, column;
  private Data[] d;
  private Func[] f;

  public Program(int line, int column, Data[] d, Func[] f) {
    this.line = line;
    this.column = column;
    this.f = f;
    this.d = d;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Func[] getFuncs() {
    return f;
  }

  public Data[] getDatas() {
    return d;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
