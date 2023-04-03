grammar lang;

@parser::header
{
    package parser;

    import ast.*;
}

@lexer::header
{
    package parser;
}

/* Regras da gramática */

prog :
  s1=stmt ';' 
  (s2=stmt ';' )*
;

stmt :
 ID '=' expr 
|
 expr op='?' '[' s1=stmt ']' ':' '[' s2=stmt ']' 
|
 expr op='?' '[' s1=stmt ']' 
|
 expr 
;

expr :
 term op='+' e=expr 
|
 term 
;

term :
 factor op='*' e=term 
|
 factor 
;

factor :
 ID 
|
 INT 
;

/* Regras léxicas */

ID : [a-z]+;
INT : [0-9]+;

NEWLINE: '\r'? '\n' -> skip;
WS : [ \t]+ -> skip;
LINE_COMMENT : '//' ~('\r' | '\n')* NEWLINE -> skip;
COMMENT: '/*' .*?  '*/' -> skip;

