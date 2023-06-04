 /*
  *  DCC045 - Teoria dos Compiladores - 2023.1
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
 */

grammar lang;

@parser::header
{
    package lang.parser;
}

@lexer::header
{
    package lang.parser;
}

/* Gramatic Rules */

/* start */
program: (data)* (func)+;			

/* data */																				
data: DATA IDTYPE '{' (decl)+ RIGHTBRACKET;
decl: (ID|IDTYPE) DOUBLECOLON type SEMICOLON; 		

/* func */
func: (ID|IDTYPE) LEFTPARENT (params)? RIGHTPARENT (COLON type (COMMA type)*)? LEFTBRACKET (cmd)* RIGHTBRACKET; 			
params: param (COMMA param)*; 
param: (ID|IDTYPE) DOUBLECOLON type;	

/* type */
type: btype (brace)*;		
brace: LEFTBRACE RIGHTBRACE;	

/* btype */	
btype: TYPEINT 		#tyInt
	| TYPECHAR 		#tyChar
	| TYPEBOOL 		#tyBool
	| TYPEFLOAT 	#tyFloat
	| IDTYPE 		#tyID
	;

/* cmd */
cmd: LEFTBRACKET (cmd)* RIGHTBRACKET				#stmtList
	| IF LEFTPARENT exp RIGHTPARENT cmd 			#if
	| IF LEFTPARENT exp RIGHTPARENT cmd ELSE cmd 	#ifElse										
	| ITERATE LEFTPARENT exp RIGHTPARENT cmd		#iterate
	| READ lvalue SEMICOLON							#read
	| PRINT exp SEMICOLON 							#print
	| RETURN exp (COMMA exp)* SEMICOLON 			#return
	| lvalue ATTR exp SEMICOLON 					#attr
	| (ID|IDTYPE) LEFTPARENT (exps)? RIGHTPARENT (LT lvalue (COMMA lvalue)* GT)? SEMICOLON		#callCmd
	;

/* exp */	
exp: exp AND exp		#and
	| rexp				#rExpr
	;
rexp: aexp LT aexp 		#lt
	| rexp EQ aexp		#eq
	| rexp NEQ aexp		#neq
	| aexp 				#aExpr
	;
aexp: aexp ADD mexp		#add
	| aexp SUB mexp		#sub
	| mexp 				#mExpr
	;
mexp: mexp MULT sexp	#mult
	| mexp DIV sexp		#div
	| mexp MOD sexp		#mod
	| sexp 				#sExpr
	;
sexp: NOT sexp			#not
	| SUB sexp			#SMinus
	| TRUE				#true
	| FALSE				#false
	| NULL				#null
	| INT				#int
	| FLOAT				#float
	| CHAR				#char
	| pexp				#pExpr																																													
	;																																																
pexp: lvalue 																	#lValues																						
	| LEFTPARENT exp RIGHTPARENT 												#expr
	| NEW type (LEFTBRACE exp RIGHTBRACE)? 										#new
	| (ID|IDTYPE) LEFTPARENT (exps)? RIGHTPARENT LEFTBRACE exp RIGHTBRACE 		#callExpr
	;
exps: exp ( COMMA exp )*; 

/* lvalue */	
lvalue: (ID|IDTYPE) 							#lValueIDs
	| lvalue LEFTBRACE exp RIGHTBRACE 			#array
	| lvalue DOT (ID|IDTYPE) 					#accessData
	;
																									
/* Lexic Rules */
DATA: 			'data';
TYPEINT: 		'Int';
TYPEFLOAT: 		'Float';
TYPECHAR: 		'Char';
TYPEBOOL: 		'Bool';
TRUE: 			'true';
FALSE: 			'false';
NULL: 			'null';
IF: 			'if';
ELSE: 			'else';
ITERATE: 		'iterate';
READ: 			'read';
PRINT: 			'print';
RETURN: 		'return';
NEW: 			'new';
LEFTPARENT: 	'(';
RIGHTPARENT:	')';
LEFTBRACE: 		'[';
RIGHTBRACE: 	']';
LEFTBRACKET: 	'{';
RIGHTBRACKET: 	'}';
GT: 			'>';
LT: 			'<';
DOT: 			'.';
COMMA: 			',';
COLON: 			':';
SEMICOLON: 		';';
DOUBLECOLON: 	'::';
ATTR: 			'=';
EQ: 			'==';
NEQ:			'!=';
ADD: 			'+';
SUB: 			'-';
MULT: 			'*';
DIV: 			'/';
MOD: 			'%';
AND: 			'&&';
NOT: 			'!';
ID: 			[a-z] [a-zA-Z0-9_]*;
IDTYPE: 		[A-Z] [a-zA-Z0-9_]*;
INT: 			('0'..'9')+;
FLOAT: 			('0'..'9')* '.'('0'..'9')+;
CHAR: 			'\'' ( ~[\\'] | '\\n' | '\\t' | '\\b' | '\\r' | '\\\\' | '\\\'' ) '\'';
ENDLINE: 	        '\r'? '\n' -> skip;
EMPTY: 		        [ \t]+ -> skip;
LINECOMMENT:     	'--' ~[\r\n]* -> channel(HIDDEN);
MULTLINESCOMMENT:   '{-' .*? '-}' -> channel(HIDDEN);