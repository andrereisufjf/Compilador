 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
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