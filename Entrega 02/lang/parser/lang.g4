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
 'data' IDTYPE '{' decl+ '}'
;

decl :
 (ID|IDTYPE) '::' type ';'
;

func :
 (ID|IDTYPE) '(' params* ')' ( ':' type (',' type)* )* '{' cmd* '}'
;

params :
 (ID|IDTYPE) '::' type (',' (ID|IDTYPE) '::' type)*
;

type :
 btype ( '[' ']' )*
;

btype :
 'Int' | 'Char' | 'Bool' | 'Float' | IDTYPE
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

CHAR: '\'' ( ~[\\'] | '\\n' | '\\t' | '\\b' | '\\r' | '\\\\' | '\\\'' ) '\'';
ID : [a-z] [a-zA-Z0-9_]*;
IDTYPE : [A-Z] [a-zA-Z0-9_]*;
INT : [0-9]+;
FLOAT: [0-9]*  '.'  [0-9]+;

NEWLINE: '\r'? '\n' -> skip;
LINE_COMMENT : '--' ~('\r' | '\n')* NEWLINE -> skip;
COMMENT: '{-' .*?  '-}' -> skip;
WS : [ \t]+ -> skip;



