// Generated from lang.g4 by ANTLR 4.8

    package lang.parser;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link langParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface langVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link langParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(langParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData(langParser.DataContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(langParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(langParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(langParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(langParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#btype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBtype(langParser.BtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(langParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(langParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#rexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRexp(langParser.RexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAexp(langParser.AexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#mexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMexp(langParser.MexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#sexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSexp(langParser.SexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#pexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPexp(langParser.PexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue(langParser.LvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link langParser#exps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExps(langParser.ExpsContext ctx);
}