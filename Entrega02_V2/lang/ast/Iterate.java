/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Iterate extends Cmd{

  private int line, column;
  private Expr exp;
  private Cmd c;

  public Iterate(int line, int column, Expr exp, Cmd c){
    this.line = line;
    this.column = column;
    this.exp = exp;
    this.c = c;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Expr getExpression() {
    return exp;
  }

  public Cmd getBody() {
    return c;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}