 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

import lang.visitors.Visitor;

public class AccessData extends Access{

	private int line, column;
  private String index;

  public AccessData(int line, int column, String index){
    this.line = line;
    this.column = column;
    this.index = index;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getIndex() { 
		return index; 
	}

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}
