 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.ast;

public abstract class BinOp extends Expr {

  private int line, column;
  private Expr l;
  private Expr r;
 
  public BinOp(int line, int column, Expr l, Expr r) {
    this.line = line;
    this.column = column;
    this.l = l;
    this.r = r;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public void setLeft(Expr l) {
    this.l = l;
  }

  public void setRight(Expr r) {
    this.r = r;
  }

  public Expr getLeft() {
    return l;
  }

  public Expr getRight() {
    return r;
  }

}
