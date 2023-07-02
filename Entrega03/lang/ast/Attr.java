 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class Attr extends Cmd{

  private int line, column;
  private LValue lvalue;
  private Expr exp;

  public Attr(int line, int column, LValue lvalue, Expr exp)	{
    this.line = line;
    this.column = column;
    this.lvalue = lvalue;
    this.exp = exp;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public LValue getValue() {
    return lvalue;
  }

  public Expr getExpression() {
    return exp;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}