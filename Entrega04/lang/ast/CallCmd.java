 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class CallCmd extends Cmd {

  private int line, column;
  private String n;
  private Expr[] exps;
  private LValue[] ret;

  public CallCmd(int line, int column, String n, Expr[] exps, LValue[] ret)	{
    this.n = n;
    this.exps = exps;
    this.ret = ret;
    this.line = line;
    this.column = column;
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

  public LValue[] getReturn() {
    return ret;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
