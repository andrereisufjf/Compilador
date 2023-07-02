 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.visitors;

import lang.ast.*;

public abstract class Visitor
{
    public abstract void visit(Program p); 
    public abstract void visit(Data e);
    public abstract void visit(AccessData e);
  	public abstract void visit(Decl e);
    public abstract void visit(StmtList e);
    public abstract void visit(CallCmd e);
    public abstract void visit(CallExpr e);
    public abstract void visit(Attr e);
    public abstract void visit(Func f);
    public abstract void visit(Param e);
    public abstract void visit(Print e);
    public abstract void visit(Read e);
    public abstract void visit(Return e);
    public abstract void visit(LValue e);
    public abstract void visit(Int e);
    public abstract void visit(FloatV e);
    public abstract void visit(Char e);
    public abstract void visit(Iterate e);
    public abstract void visit(New e);
    public abstract void visit(Array e);
    public abstract void visit(CModule e);
    public abstract void visit(Div e);
    public abstract void visit(Mult e);
    public abstract void visit(Add e);
    public abstract void visit(Sub e);
    public abstract void visit(SMinus e);
    public abstract void visit(Not e);
    public abstract void visit(Neq e);
    public abstract void visit(And e);
    public abstract void visit(Eq e);
    public abstract void visit(Lt e);
    public abstract void visit(If e);
    public abstract void visit(False e);
    public abstract void visit(True e);
    public abstract void visit(Null e);
    public abstract void visit(Type t);
    public abstract void visit(TyBool t);
    public abstract void visit(TyChar t);
    public abstract void visit(TyFloat t);
    public abstract void visit(TyID t);
    public abstract void visit(TyInt t);
}
