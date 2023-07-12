 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class StmtList extends Cmd{
	private int line, column;
  private Cmd[] c;

  public StmtList(int line , int column, Cmd[] c)	{
    this.line = line;
    this.column = column;
    this.c = c;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Cmd[] getList() { 
	  return c; 
	}

  public void accept(Visitor v) {
		v.visit(this); 
	}
}
