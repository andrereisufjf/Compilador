/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
