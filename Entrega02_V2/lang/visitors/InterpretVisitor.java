 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */
package lang.visitors;

import lang.ast.*;
import java.util.*;

public class InterpretVisitor extends Visitor{

  	private Stack<HashMap<String, Object>> env;
    private HashMap<String, Func> funcs;
	private Stack<Object> operands;
    private boolean retMode, debug;
	private HashMap<String, Data> types;
    private ArrayList<Object> returns;

    public InterpretVisitor() {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Func>();
        operands = new Stack<Object>();
        retMode = false;
        debug = false;
		types = new HashMap<String, Data>();
        returns = new ArrayList<Object>();
    }

    public InterpretVisitor(boolean debug) {
        this();
        this.debug = debug;
    }

    public void visit(Program p) {
        Func main = null;
        if (p.getDatas() != null) {
            for (Data data : p.getDatas()) {
                types.put(data.getId(), data);
            }
        }
        for (Func func : p.getFuncs()) {
            funcs.put(func.getID(), func);
            if (func.getID().equals("main")) {
                main = func;
            }
        }
        if (main == null) {
            throw new RuntimeException("There is no main function! Aborted!");
        }
        main.accept(this);
    }

    public void visit(Data e) { }

    @Override
    public void visit(Decl e) { }

    public void visit(AccessData e) {
        try {
            operands.push(e.getIndex());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(StmtList e) {
        if (retMode) {
            return;
        }
        try {
            for (Cmd cmd : e.getList()) {
                cmd.accept(this);
                if (retMode) {
                    return;
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(CallCmd e) {
        try {
            if (funcs.get(e.getName()) != null) {
                for (Expr exp : e.getExpressions()) {
                    exp.accept(this);
                }
                funcs.get(e.getName()).accept(this);
                if (e.getReturn() != null) {
                    LValue[] s = e.getReturn();
                    for (int i = 0; i < s.length; i++) {
                        LValue lvalue = s[i];
                        Object obj = env.peek().get(lvalue.getId());
                        if (!lvalue.getAccess().isEmpty()) {
                            for (int k = 0; k < lvalue.getAccess().size() - 1; k++) {
                                lvalue.getAccess().get(k).accept(this);
                                Object select = operands.pop();
                                if (lvalue.getAccess().get(k) instanceof AccessData) {
                                    obj = ((HashMap<String, Object>) obj).get(select);
                                } else {
                                    obj = ((ArrayList) obj).get((Integer) select);
                                }
                            }
                            lvalue.getAccess().get(lvalue.getAccess().size() - 1).accept(this);
                            Object select = operands.pop();
                            if (lvalue.getAccess().get(lvalue.getAccess().size() - 1) instanceof AccessData) {
                                ((HashMap<String, Object>) obj).put((String) select, returns.get(i));
                            } else {
                                ((ArrayList) obj).set((Integer) select, returns.get(i));
                            }
                        } else {
                            env.peek().put(lvalue.getId(), returns.get(i));
                        }
                    }
                }
                returns.clear();
            } else {
                throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") Undefined function " + e.getName());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(CallExpr e) {
        try {
            if (funcs.get(e.getName()) != null) {
                for (Expr exp : e.getExpressions()) {
                    exp.accept(this);
                }
                funcs.get(e.getName()).accept(this);
                e.getReturn().accept(this);
                operands.push(returns.get((Integer) operands.pop()));
                returns.clear();
            } else {
                throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") Undefined function " + e.getName());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

	public void visit(Attr e) {
        try {
            LValue lvalue = e.getValue();
            e.getExpression().accept(this);
            Object val = operands.pop();
            Object obj = null;
            if (env.peek().containsKey(lvalue.getId())) {
                obj = env.peek().get(lvalue.getId());
            }
            if (!lvalue.getAccess().isEmpty()) {
                for (int k = 0; k < lvalue.getAccess().size() - 1; k++) {
                    lvalue.getAccess().get(k).accept(this);
                    Object select = operands.pop();
                    if (lvalue.getAccess().get(k) instanceof AccessData) {
                        obj = ((HashMap<String, Object>) obj).get(select);
                    } else {
                        obj = ((ArrayList) obj).get((Integer) select);
                    }
                }
                lvalue.getAccess().get(lvalue.getAccess().size() - 1).accept(this);
                Object select = operands.pop();
                if (lvalue.getAccess().get(lvalue.getAccess().size() - 1) instanceof AccessData) {
                    ((HashMap<String, Object>) obj).put((String) select, val);
                } else {
                    ((ArrayList) obj).set((Integer) select, val);
                }
            } else {
                env.peek().put(lvalue.getId(), val);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(Func e) {
        HashMap<String, Object> lv = new HashMap<String, Object>();
        if (e.getParam() != null) {
            for (int i = e.getParam().length - 1; i >= 0; i--) {
                lv.put(e.getParam()[i].getID(), operands.pop());
            }
        }
        env.push(lv);
        for (Cmd cmd : e.getBody()) {
            if (retMode) {
                break;
            }
            cmd.accept(this);
        }
        if (debug && e.getID().equals("main")) {
            Object[] obj = env.peek().keySet().toArray();
            System.out.println("-------------- Memoria ----------------");

            for (int i = 0; i < obj.length; i++) {
                System.out.print(((String) obj[i]) + " : " + env.peek().get(obj[i]).toString());
            }
        }
        env.pop();
        retMode = false;
    }

    public void visit(Param e) { }

    public void visit(Print e) {
        try {
            e.getExpression().accept(this);
            System.out.print(operands.pop());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Read e) {
        try {
            Scanner scan = new Scanner(System.in);
            Object Input = scan.nextLine();
            if (!e.getValue().getAccess().isEmpty()) {
                Object obj = (Object) env.peek().get(e.getValue().getId());
                for (int k = 0; k < e.getValue().getAccess().size() - 1; k++) {
                    e.getValue().getAccess().get(k).accept(this);
                    Object s = operands.pop();
                    if (e.getValue().getAccess().get(k) instanceof AccessData) {
                        obj = ((HashMap<String, Object>) obj).get(s);
                    } else {
                        obj = ((ArrayList) obj).get((Integer) s);
                    }
                }
                e.getValue().getAccess().get(e.getValue().getAccess().size() - 1).accept(this);
                Object s = operands.pop();
                if (e.getValue().getAccess().get(e.getValue().getAccess().size() - 1) instanceof AccessData) {
                    ((HashMap<String, Object>) obj).put((String) s, Input);
                } else {
                    ((ArrayList) obj).set((Integer) s, Input);
                }
            } else {
                env.peek().put(e.getValue().getId(), Input);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Return e) {
        ArrayList<Object> aux = new ArrayList<Object>();
        for (Expr exp : e.getExpressions()) {
            exp.accept(this);
            aux.add(operands.pop());
        }
        for (int i = 0; i < aux.size(); i++) {
            returns.add(aux.get(i));
        }
        retMode = true;
    }
    
    public void visit(LValue e) {
        try {
            if (env.peek().containsKey(e.getId())) {
                Object obj = env.peek().get(e.getId());
                if (e.getAccess().size() != 0) {
                    for (Access lv : e.getAccess()) {
                        lv.accept(this);
                        if (lv instanceof AccessData) {
                            obj = ((HashMap<String, Object>) obj).get((String) operands.pop());
                        } else if (lv instanceof Array) {
                            obj = ((ArrayList) obj).get((Integer) operands.pop());
                        }
                    }
                }
                operands.push(obj);
            } else {
                throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") not declared" + e.getId());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Int e) {
        try {
            operands.push(Integer.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(FloatV e) {
        try {
            operands.push(Float.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Char e) {
        try {
            operands.push(e.getValue());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Iterate e) {
        try {
            e.getExpression().accept(this);
            Object obj = operands.pop();
            Object objClass = obj.getClass();
            if (objClass == Integer.class) {

                for (int i = 0; i < (Integer) obj; i++) {
                    e.getBody().accept(this);
                    e.getExpression().accept(this);
                }
            } else {
                throw new RuntimeException("Dados se diferenciam");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(New e) {
        try {
            BType bType = e.getType().getBtype();
            Integer t = 0;
            ArrayList<Object> val = null;
            ArrayList<Object> aux = null;
            boolean idType = false;
            HashMap<String, Object> a = null;
            if (bType instanceof TyID) {
                idType = true;
                TyID tyId = (TyID) bType;
                a = new HashMap<>();
                Data data = types.get(tyId.getIdType());
                for (Decl declaration : data.getTypes()) {
                    a.put(declaration.getId(), null);
                }
            }
            if (e.getExpression() != null) {
                e.getExpression().accept(this);
                t = (Integer) operands.pop();
                if (idType) {
                    val = new ArrayList<Object>(t);
                    for (int i = 0; i < t; i++) {
                        val.add(a);
                    }
                } else {
                    val = new ArrayList<Object>(t);
                    for (int i = 0; i < t; i++) {
                        val.add(null);
                    }
                }
                aux = null;
                for (int i = 0; i < e.getType().getBraces(); i++) {
                    aux = new ArrayList<Object>();
                    aux.add(val);
                    val = aux;
                }
                operands.push(val);
            } else {
                if (e.getType().getBraces() != 0) {
                    val = new ArrayList<Object>();
                    if (idType) {
                        val.add(a);
                    } else {
                        val.add(null);
                    }
                    aux = null;
                    for (int i = 0; i < e.getType().getBraces() - 1; i++) {
                        aux = new ArrayList<Object>();
                        aux.add(val);
                        val = aux;
                    }
                    operands.push(val);
                } else {
                    if (idType) {
                        operands.push(a);
                    } else {
                        operands.push(null);
                    }
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Array e) {
        try {
            e.getIndex().accept(this);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(CModule e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object right = operands.pop();
            Object left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left % (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left % (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left % (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left % (Float) right);
			} else {
				throw new RuntimeException("Invalid");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Div e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object right = operands.pop();
            Object left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left / (Integer) right);
            } else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left / (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left / (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left / (Float) right);
			} else {
			    throw new RuntimeException("Invalid");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Mult e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object right = operands.pop();
            Object left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left * (Integer) right);
            } else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left * (Float) right);
            } else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left * (Integer) right);
            } else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left * (Float) right);
            } else {
                throw new RuntimeException("Invalid");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Add e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object right = operands.pop();
            Object left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left + (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left + (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left + (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left + (Float) right);
			} else {
				throw new RuntimeException("Invalid");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Sub e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left, right;
            right = operands.pop();
            left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left - (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left - (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left - (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left - (Float) right);
			} else {
				throw new RuntimeException("Invalid");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(SMinus e) {
        try {
            e.getExpression().accept(this);
            Number exp = (Number) operands.pop();
            if (exp.getClass() == Integer.class) {
                operands.push(-exp.intValue());
            } else if (exp.getClass() == Float.class) {
                operands.push(-exp.floatValue());
            } else {
                throw new RuntimeException("Invalid");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Not e) {
        try {
            e.getExpression().accept(this);
            operands.push(!(Boolean) operands.pop());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Neq e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            operands.push(!operands.pop().equals(operands.pop()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(And e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left = operands.pop();
            Object right = operands.pop();
            operands.push(Boolean.valueOf((Boolean) left && (Boolean) right));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Eq e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            operands.push(Boolean.valueOf(operands.pop().equals(operands.pop())));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
	public void visit(Lt e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object right = operands.pop();
            Object left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left < (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left < (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left < (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left < (Float) right);
			} else {
				throw new RuntimeException("Invalid");
			}	
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(If e) {
        try {
            e.getExpression().accept(this);
            if ((Boolean) operands.pop()) {
                e.getThen().accept(this);
            } else if (e.getElse() != null) {
                e.getElse().accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(False e) {
        try {
            operands.push(Boolean.valueOf(false));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(True e) {
        try {
            operands.push(Boolean.valueOf(true));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Null e) {
        try {
            operands.push(null);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(Type t) { }
    
    public void visit(TyBool t) { }
		
    public void visit(TyChar t) { }

    public void visit(TyFloat t) { }

    public void visit(TyID t) { }

    public void visit(TyInt t) { }
}