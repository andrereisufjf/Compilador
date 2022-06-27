// Generated from parser/lang.g4 by ANTLR 4.8

    package parser;

    import ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class langParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, ID=9, 
		INT=10, NEWLINE=11, WS=12, LINE_COMMENT=13, COMMENT=14;
	public static final int
		RULE_prog = 0, RULE_stmt = 1, RULE_expr = 2, RULE_term = 3, RULE_factor = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "stmt", "expr", "term", "factor"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'?'", "'['", "']'", "':'", "'+'", "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "ID", "INT", "NEWLINE", 
			"WS", "LINE_COMMENT", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public langParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public StmtList ast;
		public StmtContext s1;
		public StmtContext s2;
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			((ProgContext)_localctx).s1 = stmt();
			setState(11);
			match(T__0);
			((ProgContext)_localctx).ast =  new StmtList(((ProgContext)_localctx).s1.ast.getLine(), ((ProgContext)_localctx).s1.ast.getCol(), ((ProgContext)_localctx).s1.ast);
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID || _la==INT) {
				{
				{
				setState(13);
				((ProgContext)_localctx).s2 = stmt();
				setState(14);
				match(T__0);
				((ProgContext)_localctx).ast =  new StmtList(((ProgContext)_localctx).s2.ast.getLine(), ((ProgContext)_localctx).s2.ast.getCol(), _localctx.ast, ((ProgContext)_localctx).s2.ast);
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public Node ast;
		public Token ID;
		public ExprContext expr;
		public Token op;
		public StmtContext s1;
		public StmtContext s2;
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		try {
			setState(48);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				((StmtContext)_localctx).ID = match(ID);
				setState(23);
				match(T__1);
				setState(24);
				((StmtContext)_localctx).expr = expr();
				((StmtContext)_localctx).ast =  new Attr((((StmtContext)_localctx).ID!=null?((StmtContext)_localctx).ID.getLine():0), (((StmtContext)_localctx).ID!=null?((StmtContext)_localctx).ID.getCharPositionInLine():0), new ID((((StmtContext)_localctx).ID!=null?((StmtContext)_localctx).ID.getLine():0), (((StmtContext)_localctx).ID!=null?((StmtContext)_localctx).ID.getCharPositionInLine():0), (((StmtContext)_localctx).ID!=null?((StmtContext)_localctx).ID.getText():null)), ((StmtContext)_localctx).expr.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				((StmtContext)_localctx).expr = expr();
				setState(28);
				((StmtContext)_localctx).op = match(T__2);
				setState(29);
				match(T__3);
				setState(30);
				((StmtContext)_localctx).s1 = stmt();
				setState(31);
				match(T__4);
				setState(32);
				match(T__5);
				setState(33);
				match(T__3);
				setState(34);
				((StmtContext)_localctx).s2 = stmt();
				setState(35);
				match(T__4);
				((StmtContext)_localctx).ast =  new If((((StmtContext)_localctx).op!=null?((StmtContext)_localctx).op.getLine():0), (((StmtContext)_localctx).op!=null?((StmtContext)_localctx).op.getCharPositionInLine():0), ((StmtContext)_localctx).expr.ast, ((StmtContext)_localctx).s1.ast, ((StmtContext)_localctx).s2.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(38);
				((StmtContext)_localctx).expr = expr();
				setState(39);
				((StmtContext)_localctx).op = match(T__2);
				setState(40);
				match(T__3);
				setState(41);
				((StmtContext)_localctx).s1 = stmt();
				setState(42);
				match(T__4);
				((StmtContext)_localctx).ast =  new If((((StmtContext)_localctx).op!=null?((StmtContext)_localctx).op.getLine():0), (((StmtContext)_localctx).op!=null?((StmtContext)_localctx).op.getCharPositionInLine():0), ((StmtContext)_localctx).expr.ast, ((StmtContext)_localctx).s1.ast);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(45);
				((StmtContext)_localctx).expr = expr();
				((StmtContext)_localctx).ast =  new Print(((StmtContext)_localctx).expr.ast.getLine(), ((StmtContext)_localctx).expr.ast.getCol(), ((StmtContext)_localctx).expr.ast);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Expr ast;
		public TermContext term;
		public Token op;
		public ExprContext e;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		try {
			setState(58);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				((ExprContext)_localctx).term = term();
				setState(51);
				((ExprContext)_localctx).op = match(T__6);
				setState(52);
				((ExprContext)_localctx).e = expr();
				((ExprContext)_localctx).ast =  new Add((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getLine():0), (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getCharPositionInLine():0), ((ExprContext)_localctx).term.ast, ((ExprContext)_localctx).e.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				((ExprContext)_localctx).term = term();
				((ExprContext)_localctx).ast =  ((ExprContext)_localctx).term.ast;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Expr ast;
		public FactorContext factor;
		public Token op;
		public TermContext e;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_term);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				((TermContext)_localctx).factor = factor();
				setState(61);
				((TermContext)_localctx).op = match(T__7);
				setState(62);
				((TermContext)_localctx).e = term();
				((TermContext)_localctx).ast =  new Mul((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getLine():0), (((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getCharPositionInLine():0), ((TermContext)_localctx).factor.ast, ((TermContext)_localctx).e.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				((TermContext)_localctx).factor = factor();
				((TermContext)_localctx).ast =  ((TermContext)_localctx).factor.ast;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Expr ast;
		public Token ID;
		public Token INT;
		public TerminalNode ID() { return getToken(langParser.ID, 0); }
		public TerminalNode INT() { return getToken(langParser.INT, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof langListener ) ((langListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_factor);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				((FactorContext)_localctx).ID = match(ID);
				((FactorContext)_localctx).ast =  new ID((((FactorContext)_localctx).ID!=null?((FactorContext)_localctx).ID.getLine():0), (((FactorContext)_localctx).ID!=null?((FactorContext)_localctx).ID.getCharPositionInLine():0), (((FactorContext)_localctx).ID!=null?((FactorContext)_localctx).ID.getText():null));
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				((FactorContext)_localctx).INT = match(INT);
				((FactorContext)_localctx).ast =  new Num((((FactorContext)_localctx).INT!=null?((FactorContext)_localctx).INT.getLine():0), (((FactorContext)_localctx).INT!=null?((FactorContext)_localctx).INT.getCharPositionInLine():0), Integer.parseInt((((FactorContext)_localctx).INT!=null?((FactorContext)_localctx).INT.getText():null)));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\20O\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\24\n\2\f\2"+
		"\16\2\27\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\63\n\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\5\4=\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5G\n"+
		"\5\3\6\3\6\3\6\3\6\5\6M\n\6\3\6\2\2\7\2\4\6\b\n\2\2\2P\2\f\3\2\2\2\4\62"+
		"\3\2\2\2\6<\3\2\2\2\bF\3\2\2\2\nL\3\2\2\2\f\r\5\4\3\2\r\16\7\3\2\2\16"+
		"\25\b\2\1\2\17\20\5\4\3\2\20\21\7\3\2\2\21\22\b\2\1\2\22\24\3\2\2\2\23"+
		"\17\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\3\3\2\2\2\27"+
		"\25\3\2\2\2\30\31\7\13\2\2\31\32\7\4\2\2\32\33\5\6\4\2\33\34\b\3\1\2\34"+
		"\63\3\2\2\2\35\36\5\6\4\2\36\37\7\5\2\2\37 \7\6\2\2 !\5\4\3\2!\"\7\7\2"+
		"\2\"#\7\b\2\2#$\7\6\2\2$%\5\4\3\2%&\7\7\2\2&\'\b\3\1\2\'\63\3\2\2\2()"+
		"\5\6\4\2)*\7\5\2\2*+\7\6\2\2+,\5\4\3\2,-\7\7\2\2-.\b\3\1\2.\63\3\2\2\2"+
		"/\60\5\6\4\2\60\61\b\3\1\2\61\63\3\2\2\2\62\30\3\2\2\2\62\35\3\2\2\2\62"+
		"(\3\2\2\2\62/\3\2\2\2\63\5\3\2\2\2\64\65\5\b\5\2\65\66\7\t\2\2\66\67\5"+
		"\6\4\2\678\b\4\1\28=\3\2\2\29:\5\b\5\2:;\b\4\1\2;=\3\2\2\2<\64\3\2\2\2"+
		"<9\3\2\2\2=\7\3\2\2\2>?\5\n\6\2?@\7\n\2\2@A\5\b\5\2AB\b\5\1\2BG\3\2\2"+
		"\2CD\5\n\6\2DE\b\5\1\2EG\3\2\2\2F>\3\2\2\2FC\3\2\2\2G\t\3\2\2\2HI\7\13"+
		"\2\2IM\b\6\1\2JK\7\f\2\2KM\b\6\1\2LH\3\2\2\2LJ\3\2\2\2M\13\3\2\2\2\7\25"+
		"\62<FL";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}