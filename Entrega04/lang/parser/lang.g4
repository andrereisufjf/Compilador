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

/* Regras gramaticais */

/* Início */
program: (data)* (func)+;			

/* Data */
data: 'data' IDTYPE '{' (decl)+ '}';
decl: (ID|IDTYPE) '::' type ';';

/* Funções */
func: (ID|IDTYPE) '(' (params)? ')' (':' type (',' type)*)? '{' (cmd)* '}';
params: param (',' param)*;
param: (ID|IDTYPE) '::' type;

/* Tipos (já tirei a RE) */
type: btype (brace)*;		
brace: '[' ']';

/* Declarações de tipo */
btype: 'Int' 		#tyInt
	| 'Char' 		#tyChar
	| 'Bool' 		#tyBool
	| 'Float' 	    #tyFloat
	| IDTYPE 		#tyID
	;

/* Comandos */
cmd: '{' (cmd)* '}'				#stmtList
	| 'if' '(' exp ')' cmd 			#if
	| 'if' '(' exp ')' cmd 'else' cmd 	#ifElse
	| 'iterate' '(' exp ')' cmd		#iterate
	| 'read' lvalue ';'							#read
	| 'print' exp ';' 							#print
	| 'return' exp (',' exp)* ';' 			#return
	| lvalue '=' exp ';' 					#attr
	| (ID|IDTYPE) '(' (exps)? ')' ('<' lvalue (',' lvalue)* '>')? ';'		#callCmd
	;

/* Expressões */
exp: exp '&&' exp		#and
	| rexp				#rExpr
	;
rexp: aexp '<' aexp 		#lt
	| rexp '==' aexp		#eq
	| rexp '!=' aexp		#neq
	| aexp 				#aExpr
	;
aexp: aexp '+' mexp		#add
	| aexp '-' mexp		#sub
	| mexp 				#mExpr
	;
mexp: mexp '*' sexp	#mult
	| mexp '/' sexp		#div
	| mexp '%' sexp		#mod
	| sexp 				#sExpr
	;
sexp: '!' sexp			#not
	| '-' sexp			#SMinus
	| 'true'			#true
	| 'false'				#false
	| 'null'				#null
	| INT				#int
	| FLOAT				#float
	| CHAR				#char
	| pexp				#pExpr																																													
	;																																																
pexp: lvalue 																	#lValues																						
	| '(' exp ')' 												#expr
	| 'new' type ('[' exp ']')? 										#new
	| (ID|IDTYPE) '(' (exps)? ')' '[' exp ']' 		#callExpr
	;
exps: exp ( ',' exp )*;

/* Acessos */
lvalue: (ID|IDTYPE) 							#lValueIDs
	| lvalue '[' exp ']' 			#array
	| lvalue '.' (ID|IDTYPE) 					#accessData
	;
																									
/* Macros */
ID: 			[a-z] [a-zA-Z0-9_]*;
IDTYPE: 		[A-Z] [a-zA-Z0-9_]*;
INT: 			('0'..'9')+;
FLOAT: 			('0'..'9')* '.'('0'..'9')+;
CHAR: 			'\'' ( ~[\\'] | '\\n' | '\\t' | '\\b' | '\\r' | '\\\\' | '\\\'' ) '\'';
ENDLINE: 	        '\r'? '\n' -> skip;
EMPTY: 		        [ \t]+ -> skip;
LINECOMMENT:     	'--' ~[\r\n]* -> channel(HIDDEN);
MULTLINESCOMMENT:   '{-' .*? '-}' -> channel(HIDDEN);