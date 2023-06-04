/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Param extends SuperNode {

  private int line, column;
  private String id;
  private Type t;

  public Param(int line, int column, String id, Type t) {
    this.id = id;
    this.t = t;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getID() {
    return id;
  }

  public Type getType() {
    return t;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
