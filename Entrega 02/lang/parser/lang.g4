 /*
  * DCC045 - Teoria dos Compiladores - 2022.1
  *  André Luiz dos Reis - 201965004AC
  *  Lucca Oliveira Schröder - 201765205C
  */

grammar lang;

@parser::header
{
    package lang.parser;

    import lang.ast.*;
}

@lexer::header
{
    package lang.parser;
}

/* Regras da gramática */

prog :
  data* func*
;

data :
 'data' ID '{' decl* '}'
;

decl :
 ID '::' type ';'
;

func :
 ID '(' params* ')' ( ':' type (',' type)* )* '{' cmd* '}'
;

params :
 ID '::' type (',' ID '::' type)* 
;

type :
 type '[' ']'
 |
 btype  
;

btype :
 'Int' | 'Char' | 'Bool' | 'Float' | ID
;

cmd :
 '{' (cmd)* '}'
|
 'if' '(' exp ')' cmd
|
 'if' '(' exp ')' cmd 'else' cmd
|
 'iterate' '(' exp ')' cmd
| 
 'read' lvalue ';'
| 
 'print' exp ';'
|
 'return' exp (',' exp)* ';'
|
 lvalue '=' exp ';'
|
 ID '('  (exps)+ ')'  ( '<' lvalue (',' lvalue)* '>'  )* ';'
;

exp :
 exp '&&' exp
|
 rexp
;

rexp :
 aexp '<' aexp
|
 rexp '==' aexp
|
 rexp '!=' rexp
|
 aexp
;

aexp:
 aexp '+' mexp
|
 aexp '-' mexp
|
mexp
;

mexp :
 mexp '*' sexp
|
 mexp '/' sexp
|
 mexp '%' sexp
|
sexp
;

sexp :
 '!' sexp
|
 '-' sexp
| 
 'true'
|
 'false'
|
 'null'
| INT | FLOAT | CHAR
|
 pexp
;

pexp :
 lvalue
|
 '(' exp ')'
|
 'new' type ( '[' exp ']' )*
|
 ID '(' (exps)+ ')' '[' exp ']'
;

lvalue :
 ID
|
 lvalue '[' exp ']'
|
 lvalue '.' ID 
;

exps :
 exp ( ',' exp )*
;


/* Regras léxicas */

CHAR: '\'' . '\'' | '\'' '\\n' '\'' | '\'' '\\t' '\''  | '\'' '\\\\' '\'';
ID : ( [a-z] | [A-Z] | [0-9] | '_' )+;
INT : [0-9]+;
FLOAT: [0-9]*  '.'  [0-9]+;

NEWLINE: '\r'? '\n' -> skip;
LINE_COMMENT : '--' ~('\r' | '\n')* NEWLINE -> skip;
COMMENT: '{-' .*?  '-}' -> skip;
WS : [ \t]+ -> skip;



