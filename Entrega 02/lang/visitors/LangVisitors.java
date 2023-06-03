package lang.visitors;

import lang.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;
import lang.parser.*;

public class LangVisitors extends langBaseVisitor<SuperNode> {

    @Override
    public SuperNode visitProgram(langParser.ProgramContext e) {
        Data[] datas = null;
        Func[] funcs = null;
        if (e.data().size() != 0) {
            datas = new Data[e.data().size()];
            for(int i = 0; i < e.data().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                datas[i] = (Data) this.aggregateResult(this.defaultResult(), e.data(i).accept(this));
            }
        }
        if (e.func().size() != 0) {
            funcs = new Func[e.func().size()];
            for(int i = 0; i < e.func().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                funcs[i] = (Func) this.aggregateResult(this.defaultResult(), e.func(i).accept(this));
            }
        }
        return new Program(e.getStart().getLine(),e.getStart().getCharPositionInLine(),datas,funcs);
    }

    @Override
    public SuperNode visitData(langParser.DataContext e) {
        Decl[] decl = null;
        if (e.decl().size() != 0) {
            decl = new Decl[e.decl().size()];
            for(int i = 0; i < e.decl().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                decl[i] = (Decl) this.aggregateResult(this.defaultResult(), e.decl(i).accept(this));
            }
        }
        return new Data(e.getStart().getLine(),e.getStart().getCharPositionInLine(), e.IDTYPE().getText(), decl);
    }

    @Override
    public SuperNode visitDecl(langParser.DeclContext e) {
        if (e.ID().getText() != null) {
            return new Decl(e.getStart().getLine(), e.getStart().getCharPositionInLine(),e.ID().getText(), (Type) e.type().accept(this));
        }
        return new Decl(e.getStart().getLine(),e.getStart().getCharPositionInLine(),e.IDTYPE().getText(), (Type) e.type().accept(this));
    }

    @Override
    public SuperNode visitFunc(langParser.FuncContext e) {
        Param[] params = null;
        if(e.params() != null) {
            if (e.params().param().size() != 0) {
                params = new Param[e.params().param().size()];
                for(int i = 0; i < e.params().param().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                    params[i] = (Param) this.aggregateResult(this.defaultResult(), e.params().param(i).accept(this));
                }
            }
        }
        Type[] returns = null;
        if (e.type().size() != 0) {
            returns = new Type[e.type().size()];
            for(int i = 0; i < e.type().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                returns[i] = (Type) this.aggregateResult(this.defaultResult(), e.type(i).accept(this));
            }
        }
        Cmd[] cmd = null;
        if (e.cmd().size() != 0) {
            cmd = new Cmd[e.cmd().size()];
            for(int i = 0; i < e.cmd().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                cmd[i] = (Cmd) this.aggregateResult(this.defaultResult(), e.cmd(i).accept(this));
            }
        }
        if (e.ID().getText() != null) {
            return new Func(e.getStart().getLine(), e.getStart().getCharPositionInLine(), e.ID().getText(), params, returns, cmd);
        }
        return new Func(e.getStart().getLine(),e.getStart().getCharPositionInLine(),e.IDTYPE().getText(), params, returns, cmd);
    }

    @Override public SuperNode visitParams(langParser.ParamsContext e) {
        return visitChildren(e);
    }

    @Override
    public SuperNode visitParam(langParser.ParamContext e) {
        if (e.ID().getText() != null) {
            return new Param(e.getStart().getLine(), e.getStart().getCharPositionInLine(), e.ID().getText(), (Type) e.type().accept(this));
        }
        return new Param(e.getStart().getLine(), e.getStart().getCharPositionInLine(), e.IDTYPE().getText(), (Type) e.type().accept(this));
    }

    @Override
    public SuperNode visitType(langParser.TypeContext e) {
        return new Type(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (BType) e.btype().accept(this), e.brace().size());
    }

    @Override
    public SuperNode visitBrace(langParser.BraceContext e) {
        return visitChildren(e);
    }

    @Override
    public SuperNode visitTyInt(langParser.TyIntContext e) {
        return new TyInt(e.getStart().getLine(),e.getStart().getCharPositionInLine());
    }

    @Override
    public SuperNode visitTyChar(langParser.TyCharContext e) {
        return new TyChar(e.getStart().getLine(),e.getStart().getCharPositionInLine());
    }

    @Override
    public SuperNode visitTyBool(langParser.TyBoolContext e) {
        return new TyBool(e.getStart().getLine(),e.getStart().getCharPositionInLine());
    }

    @Override
    public SuperNode visitTyFloat(langParser.TyFloatContext e) {
        return new TyFloat(e.getStart().getLine(),e.getStart().getCharPositionInLine());
    }

    @Override
    public SuperNode visitTyID(langParser.TyIDContext e) {
        return new TyID(e.getStart().getLine(),e.getStart().getCharPositionInLine(),e.IDTYPE().getText());
    }

    @Override
    public SuperNode visitStmtList(langParser.StmtListContext e) {
        Cmd[] stmtList = null;
        if (e.cmd().size() != 0) {
            stmtList = new Cmd[e.cmd().size()];
            for(int i = 0; i < e.cmd().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                stmtList[i] = (Cmd) this.aggregateResult(this.defaultResult(), e.cmd(i).accept(this));
            }
        }
        return new StmtList(e.getStart().getLine(),e.getStart().getCharPositionInLine(),stmtList);
    }

    @Override
    public SuperNode visitIf(langParser.IfContext e) {
        return new If(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.exp().accept(this), (Cmd) e.cmd().accept(this));
    }

    @Override
    public SuperNode visitIfElse(langParser.IfElseContext e) {
        return new If(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.exp().accept(this), (Cmd) e.cmd(0).accept(this), (Cmd) e.cmd(1).accept(this));
    }

    @Override
    public SuperNode visitIterate(langParser.IterateContext e) {
        return new Iterate(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.exp().accept(this), (Cmd) e.cmd().accept(this));
    }

    @Override
    public SuperNode visitRead(langParser.ReadContext e) {
        return new Read(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (LValue) e.lvalue().accept(this));
    }

    @Override
    public SuperNode visitPrint(langParser.PrintContext e) {
        return new Print(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.exp().accept(this));
    }

    @Override
    public SuperNode visitReturn(langParser.ReturnContext e) {
        Expr[] exps = null;
        if (e.exp().size() != 0) {
            exps = new Expr[e.exp().size()];
            for(int i = 0; i < e.exp().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                exps[i] = (Expr) this.aggregateResult(this.defaultResult(), e.exp(i).accept(this));
            }
        }
        return new Return(e.getStart().getLine(), e.getStart().getCharPositionInLine(), exps);
    }

    @Override
    public SuperNode visitAttr(langParser.AttrContext e) {
        return new Attr(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (LValue) e.lvalue().accept(this), (Expr) e.exp().accept(this));
    }

    @Override
    public SuperNode visitCallCmd(langParser.CallCmdContext e) {
        Expr[] expressions = null;
        LValue[] lvalues = null;
        if(e.exps() != null) {
            if (e.exps().exp().size() != 0) {
                expressions = new Expr[e.exps().exp().size()];
                for(int i = 0; i < e.exps().exp().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                    expressions[i] = (Expr) this.aggregateResult(this.defaultResult(), e.exps().exp(i).accept(this));
                }
            }
        }
        if(e.lvalue() != null) {
            if (e.lvalue().size() != 0) {
                lvalues = new LValue[e.lvalue().size()];
                for(int i = 0; i < e.lvalue().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                    lvalues[i] = (LValue) this.aggregateResult(this.defaultResult(), e.lvalue(i).accept(this));
                }
            }
        }
        if (e.ID().getText() != null) {
            return new CallCmd(e.getStart().getLine(),e.getStart().getCharPositionInLine(),e.ID().getText(), expressions, lvalues);
        }
        return new CallCmd(e.getStart().getLine(),e.getStart().getCharPositionInLine(),e.IDTYPE().getText(), expressions, lvalues);
    }

    @Override
    public SuperNode visitCallExpr(langParser.CallExprContext e) {
        Expr[] params = null;
        if(e.exps() != null) {
            if (e.exps().exp().size() != 0) {
                params = new Expr[e.exps().exp().size()];
                for(int i = 0; i < e.exps().exp().size() && this.shouldVisitNextChild(e, this.defaultResult()); ++i) {
                    params[i] = (Expr) this.aggregateResult(this.defaultResult(), e.exps().exp(i).accept(this));
                }
            }
        }
        if (e.ID().getText() != null) {
            return new CallExpr(e.getStart().getLine(),e.getStart().getCharPositionInLine(),e.ID().getText(), params, (Expr) e.exp().accept(this));
        }
        return new CallExpr(e.getStart().getLine(),e.getStart().getCharPositionInLine(),e.IDTYPE().getText(), params, (Expr) e.exp().accept(this));
    }

    @Override
    public SuperNode visitExps(langParser.ExpsContext e) {
        return visitChildren(e);
    }

    @Override
    public SuperNode visitAnd(langParser.AndContext e) {
        return new And(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.exp(0).accept(this), (Expr) e.exp(1).accept(this));
    }

    @Override public SuperNode visitRExpr(langParser.RExprContext e) {
        return visitChildren(e);
    }

    @Override
    public SuperNode visitLt(langParser.LtContext e) {
        return new Lt(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.aexp(0).accept(this), (Expr) e.aexp(1).accept(this));
    }

    @Override
    public SuperNode visitEq(langParser.EqContext e) {
        return new Eq(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.rexp().accept(this), (Expr) e.aexp().accept(this));
    }

    @Override
    public SuperNode visitNeq(langParser.NeqContext e) {
        return new Neq(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.rexp().accept(this), (Expr) e.aexp().accept(this));
    }

    @Override
    public SuperNode visitAExpr(langParser.AExprContext e) {
        return visitChildren(e);
    }

    @Override
    public SuperNode visitAdd(langParser.AddContext e) {
        return new Add(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.aexp().accept(this), (Expr) e.mexp().accept(this));
    }

    @Override
    public SuperNode visitSub(langParser.SubContext e) {
        return new Sub(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.aexp().accept(this), (Expr) e.mexp().accept(this));
    }

    @Override
    public SuperNode visitMExpr(langParser.MExprContext e) {
        return visitChildren(e);
    }

    @Override
    public SuperNode visitMult(langParser.MultContext e) {
        return new Mult(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.mexp().accept(this), (Expr) e.sexp().accept(this));
    }

    @Override
    public SuperNode visitDiv(langParser.DivContext e) {
        return new Div(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.mexp().accept(this), (Expr) e.sexp().accept(this));
    }

    @Override
    public SuperNode visitMod(langParser.ModContext e) {
        return new CModule(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.mexp().accept(this), (Expr) e.sexp().accept(this));
    }

    @Override
    public SuperNode visitSExpr(langParser.SExprContext e) {
        return visitChildren(e);
    }

    @Override
    public SuperNode visitNot(langParser.NotContext e) {
        return new Not(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.sexp().accept(this));
    }

    @Override
    public SuperNode visitSMinus(langParser.SMinusContext e) {
        return new SMinus(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.sexp().accept(this));
    }

    @Override
    public SuperNode visitTrue(langParser.TrueContext e) {
        return new True(e.getStart().getLine(), e.getStart().getCharPositionInLine());
    }

    @Override
    public SuperNode visitFalse(langParser.FalseContext e) {
        return new False(e.getStart().getLine(), e.getStart().getCharPositionInLine());
    }

    @Override
    public SuperNode visitNull(langParser.NullContext e) {
        return new Null(e.getStart().getLine(), e.getStart().getCharPositionInLine());
    }

    @Override
    public SuperNode visitInt(langParser.IntContext e) {
        return new Int(e.getStart().getLine(), e.getStart().getCharPositionInLine(), Integer.parseInt(e.INT().getText()));
    }

    @Override
    public SuperNode visitFloat(langParser.FloatContext e) {
        return new FloatV(e.getStart().getLine(),e.getStart().getCharPositionInLine(),Float.parseFloat(e.FLOAT().getText()));
    }

    @Override
    public SuperNode visitChar(langParser.CharContext e) {
        String s =  e.CHAR().getText().replace("'\\\\'", "'\\'");
        s = s.replace("'\\n'", "'\n'");
        s = s.replace("'\\r'", "'\r'");
        s = s.replace("'\\t'", "'\t'");
        s = s.replace("'\\b'", "'\b'");
        s = s.replace("'\\''", "'''");
        s = s.replace("'\\\"'", "'\"'");
        return new Char(e.getStart().getLine(), e.getStart().getCharPositionInLine(), s.charAt(1));
    }

    @Override public SuperNode visitPExpr(langParser.PExprContext e) {
        return visitChildren(e);
    }

    @Override public SuperNode visitLValues(langParser.LValuesContext e) {
        return visitChildren(e);
    }

    @Override
    public SuperNode visitExpr(langParser.ExprContext e) {
        return (Expr) e.exp().accept(this);
    }

    @Override
    public SuperNode visitNew(langParser.NewContext e) {
        if(e.exp() != null) {
            return new New(e.getStart().getLine(),e.getStart().getCharPositionInLine(),(Type) e.type().accept(this), (Expr) e.exp().accept(this));
        }
        return new New(e.getStart().getLine(),e.getStart().getCharPositionInLine(),(Type) e.type().accept(this), null);
    }

    @Override
    public SuperNode visitLValueIDs(langParser.LValueIDsContext e) {
        if (e.ID().getText() != null) {
            return new LValue(e.getStart().getLine(), e.getStart().getCharPositionInLine(), e.ID().getText());
        }
        return new LValue(e.getStart().getLine(), e.getStart().getCharPositionInLine(), e.IDTYPE().getText());
    }

    @Override
    public SuperNode visitArray(langParser.ArrayContext e) {
        LValue node = (LValue) e.lvalue().accept(this);
        node.add(new Array(e.getStart().getLine(), e.getStart().getCharPositionInLine(), (Expr) e.exp().accept(this)));
        return node;
    }

    @Override
    public SuperNode visitAccessData(langParser.AccessDataContext e) {
        LValue node = (LValue) e.lvalue().accept(this);
        if (e.ID().getText() != null) {
            node.add(new AccessData(e.getStart().getLine(), e.getStart().getCharPositionInLine(), e.ID().getText()));
        } else {
            node.add(new AccessData(e.getStart().getLine(), e.getStart().getCharPositionInLine(), e.IDTYPE().getText()));
        }
        return node;
    }
}