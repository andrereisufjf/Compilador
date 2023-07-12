 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.visitors;

import lang.ast.*;
import lang.langUtil.*;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

public class JavaVisitor extends Visitor {

	private STGroup groupTemplate;
	private ST type, stmt, expr;
	private List<ST> funcs, params, datas, decls;
	private ArrayList<STyFunc> styfuncs;
	private String fileName;
	private TyEnv<LocalEnv<SType>> env;  
    private ArrayList<TyEnv<LocalEnv<SType>>> envs;
	private FileWriter file;
	
	public JavaVisitor(String fileName, ArrayList<TyEnv<LocalEnv<SType>>> envs, ArrayList<STyFunc> f) throws IOException{
		groupTemplate = new STGroupFile("./lang/template/js.stg");
		this.fileName = fileName;
		file = new FileWriter("./lang/code_js/" + this.fileName + ".js");
		this.envs = envs;
		this.styfuncs = f;
	}

	private void criaArquivo(ST template) throws IOException {
        file.write(template.render()); 
        file.close();
    }

	public void visit(Program p) {
		ST template = groupTemplate.getInstanceOf("program");
		template.add("name", fileName);
		datas = new ArrayList<ST>();
		if(p.getDatas()!=null) {
			for (Data d : p.getDatas()) {
				d.accept(this);
			}
		}
		template.add("datas", datas);
		funcs = new ArrayList<ST>();
		if(p.getFuncs()!=null) {
			int i = 0;
			for (Func f : p.getFuncs()) {
				env = envs.get(i);
				f.accept(this);
				i++;
			}
		}
		template.add("funcs", funcs);
		try {
            criaArquivo(template);
        } catch (IOException e) {
            throw new RuntimeException("Code generate failed!");
        }
	}
	
	@Override
	public void visit(Data d) {
		ST aux = groupTemplate.getInstanceOf("data");
        aux.add("name", d.getId());
        decls = new ArrayList<ST>();
        for(Decl dec : d.getTypes()){
            dec.accept(this);
        }
		aux.add("decl", decls);	
        datas.add(aux);
	}

	@Override
	public void visit(Decl e) {
		ST dec = groupTemplate.getInstanceOf("decl");
        e.getType().accept(this);
        dec.add("name", e.getId());
        decls.add(dec);
	}

	@Override
	public void visit(AccessData e) {
		ST aux = groupTemplate.getInstanceOf("data_access");
        aux.add("expr", e.getIndex());
        expr = aux;
	}

	@Override
	public void visit(StmtList e) {
		ST aux = groupTemplate.getInstanceOf("stmt_list"); 
		ArrayList<ST> stmts = new ArrayList<ST>();
		if (e.getList() != null) {
			for (Cmd c : e.getList()) {
				c.accept(this);
				stmts.add(stmt);
			}
		}
		aux.add("stmts", stmts);
		stmt = aux; 
	}

	public void visit(CallCmd e) { 
		ST aux = groupTemplate.getInstanceOf("call_cmd");
		aux.add("name", e.getName());
        for(Expr exp : e.getExpressions()) {
            exp.accept(this);
            aux.add("args", expr);
		}
		if(e.getReturn() != null) {
			int indexRet = 0;
			for(LValue lv : e.getReturn()) {
				lv.accept(this);
				aux.addAggr("exp.{id, index}", expr, indexRet);
				indexRet++;
			}
		}
        stmt = aux;
	}

	@Override
	public void visit(CallExpr e) { 
		ST aux = groupTemplate.getInstanceOf("call_expr");
		aux.add("name", e.getName());
		if(e.getExpressions()!=null) {
			for(Expr exp : e.getExpressions()) {
				exp.accept(this);
				aux.add("args", expr);
			}
		}
		styfuncs.remove(0);
		e.getReturn().accept(this);
		aux.add("expr", expr);
		expr = aux;
	}

	public void visit(Attr e) {
		stmt = groupTemplate.getInstanceOf("attr");
		e.getValue().accept(this);
		stmt.add("expr", expr);
		e.getExpression().accept(this);
		stmt.add("value", expr);
	}

	public void visit(Func f) { 
		ST fun = groupTemplate.getInstanceOf("func");
		fun.add("name", f.getID());
		Set<String> keys = env.getKeys();
		params = new ArrayList<ST>();
		if (f.getParam() != null) {
			for (Param p : f.getParam()) {
				ST key = groupTemplate.getInstanceOf("key");
				key.add("value", p.getID());
				keys.remove(p.getID());
				p.accept(this);
			}
		}
		fun.add("params", params);
		ArrayList<ST> stmts = new ArrayList<ST>();
		if (f.getBody() != null) {
			for (Cmd c : f.getBody()) {
				c.accept(this);
				stmts.add(stmt);
			}
		}
		fun.add("stmt", stmts);
		funcs.add(fun);
	}

	public void visit(Add e) {
		ST aux = groupTemplate.getInstanceOf("add_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Sub e) {
		ST aux = groupTemplate.getInstanceOf("sub_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Mult e) {
		ST aux = groupTemplate.getInstanceOf("mul_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Div e) {
		ST aux = groupTemplate.getInstanceOf("div_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(CModule e) {
		ST aux = groupTemplate.getInstanceOf("cmodule_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(And e) {
		ST aux = groupTemplate.getInstanceOf("and_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Lt e) {
		ST aux = groupTemplate.getInstanceOf("lt_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Eq e) {
		ST aux = groupTemplate.getInstanceOf("equals_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Not e) {
		ST aux = groupTemplate.getInstanceOf("not_expr");
		e.getExpression().accept(this);
		aux.add("expr", expr);
		expr = aux;
	}

	public void visit(True e) {
		expr = groupTemplate.getInstanceOf("boolean_expr");
		expr.add("value", true);
	}

	public void visit(False e) {
		expr = groupTemplate.getInstanceOf("boolean_expr");
		expr.add("value", false);
	}

	public void visit(Int e) {
		expr = groupTemplate.getInstanceOf("int_expr");
		expr.add("value", e.getValue());
	}

	public void visit(FloatV e) {
		expr = groupTemplate.getInstanceOf("float_expr");
		expr.add("value", e.getValue());
	}

	public void visit(If e) { 
		ST aux = groupTemplate.getInstanceOf("if");
		e.getExpression().accept(this);
		aux.add("expr", expr);
		e.getThen().accept(this);
		aux.add("thn", stmt);
		SuperNode n = e.getElse();
		if (n != null) {
			n.accept(this);
			aux.add("els", stmt);
		}
		stmt = aux;
	}

	public void visit(Print e) {
		stmt = groupTemplate.getInstanceOf("print");
		e.getExpression().accept(this);
		stmt.add("expr", expr);
	}

	public void visit(Return e) {
		stmt = groupTemplate.getInstanceOf("return");
		ArrayList<ST> auxs = new ArrayList<ST>();
		for (Expr ex : e.getExpressions()) {
			ST aux = groupTemplate.getInstanceOf("aux");
			ex.accept(this); 
			aux.add("expr", expr);
			auxs.add(aux);
		}
		stmt.add("aux", auxs);
	}

	public void visit(Param e) {
		ST param = groupTemplate.getInstanceOf("param");
		e.getType().accept(this);
		param.add("name", e.getID());
		params.add(param);
	}

	public void visit(TyInt t) { }

	public void visit(TyFloat t) { }

	public void visit(TyBool t) { }

	

	

	@Override
	public void visit(TyChar t) { }

	@Override
	public void visit(TyID t) {
		type = groupTemplate.getInstanceOf("id_type");
		type.add("value", t.getIdType());
	}

	@Override
	public void visit(Iterate e) {
		ST aux = groupTemplate.getInstanceOf("iterate");
        e.getExpression().accept(this);
        aux.add("expr", expr);
		aux.add("lc", e.getLine()+"_"+e.getColumn());
        e.getBody().accept(this);
        aux.add("stmt", stmt);
        stmt = aux;
	}

	@Override
	public void visit(Char e) {
		expr = groupTemplate.getInstanceOf("char_expr");
		char aux = e.getValue();
		if (aux == '\n'){
			expr.add("value", "\\n");
		} else if (aux == ('\t')) {
			expr.add("value", "\\t");
		} else if (aux == ('\b')) {
			expr.add("value", "\\b");
		} else if (aux == ('\r')) {
			expr.add("value", "\\r");
		} else if (aux == ('\\')) {
			expr.add("value","\\\\");
		} else if (aux == ('\'')) {
			expr.add("value", "\\'");
		} else {
			expr.add("value", e.getValue());
		}
	}

	@Override
	public void visit(LValue e) {
		ST aux = groupTemplate.getInstanceOf("lvalue");
		aux.add("name", e.getId());
		if(e.getAccess() != null){
			for(Access lv : e.getAccess()) {
				lv.accept(this);
				aux.add("array", expr);
			}
		}
        expr = aux;
	}

	@Override
	public void visit(Array e) {
		ST aux = groupTemplate.getInstanceOf("array_access");
        e.getIndex().accept(this);
        aux.add("expr", expr);
        expr = aux;
	}

	@Override
	public void visit(SMinus e) {
		ST aux = groupTemplate.getInstanceOf("sminus_expr");
		e.getExpression().accept(this);
		aux.add("expr", expr);
		expr = aux;

	}

	@Override
	public void visit(Neq e) {
		ST aux = groupTemplate.getInstanceOf("neq_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;

	}

	@Override
	public void visit(New e) {
		ST aux;
		int n = e.getType().getBraces();
        e.getType().getBtype().accept(this);
        if(e.getExpression() != null){
        	aux = groupTemplate.getInstanceOf("new_expr_array");
        } else {
			aux = groupTemplate.getInstanceOf("new_expr_type");
       		aux.add("type", type);	
        }
        expr = aux;
	}

	@Override
	public void visit(Null e) {
		expr = groupTemplate.getInstanceOf("null_expr");
	}

	@Override
	public void visit(Read e) {
		ST aux = groupTemplate.getInstanceOf("read");
        e.getValue().accept(this);
        aux.add("expr", expr);
        stmt = aux;
	}

	@Override
	public void visit(Type e) {}

}
