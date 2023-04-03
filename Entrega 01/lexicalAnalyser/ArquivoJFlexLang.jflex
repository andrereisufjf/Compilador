
 /*
  * DCC045 - Teoria dos Compiladores - 2022.1
  *  André Luiz dos Reis - 201965004AC
  *  Lucca Oliveira Schröder - 201765205C
  */


 /*  Esta seção é copiada antes da declaração da classe do analisador léxico.
  *  É nesta seção que se deve incluir imports e declaração de pacotes.
  */

package lexicalAnalyser;
  
%%

%unicode
%line
%column
%class LexicalAnalyserLang
%function nextToken
%type Token

%{
    
    /* Código arbitrário pode ser inserido diretamente no analisador dessa forma. 
     * Aqui podemos declarar variáveis e métodos adicionais que julgarmos necessários. 
     */
    private int ntk;
    
    public int readedTokens(){
       return ntk;
    }

    private Token symbol(TOKEN_TYPE t) {
        ntk++;
        return new Token(t,yytext(), yyline+1, yycolumn+1);
        
    }

    private Token symbol(TOKEN_TYPE t, Object value) {
        ntk++;
        return new Token(t, value, yyline+1, yycolumn+1);
    }
%}

%init{
    ntk = 0; // Isto é copiado direto no construtor do lexer. 
%init}


  /* Definição de macros */
  ID_GERAL      = [:letter:] | [:digit:] | "_"
  FimDeLinha    = \r|\n|\r\n
  Brancos       = {FimDeLinha} | [ \t\f]
  numeroDecimal = [:digit:]* "." [:digit:]+
  numeroInteiro = [:digit:] [:digit:]*
  identificador = [:lowercase:] {ID_GERAL}*
  tipo          = [:uppercase:] {ID_GERAL}* 
  ASPAS         = [\'] | ["'"] | [']
  chacter       = {ASPAS} (.) {ASPAS} | {ASPAS} "\\" (.) {ASPAS} 
  LineComment   = "--" (.)* {FimDeLinha}
  
%state COMMENT


%%

<YYINITIAL>{
    
    {Brancos}       { /* Não faz nada  */             }
    {LineComment}   {                       }

    /* novos casos  */

/*  PALAVRAS RESERVADAS */
    "true" { return symbol(TOKEN_TYPE.TRUE_KEYWORD); }
    "false" { return symbol(TOKEN_TYPE.FALSE_KEYWORD); }
    "null" { return symbol(TOKEN_TYPE.NULL_KEYWORD); }
    "Bool" { return symbol(TOKEN_TYPE.BOOLEAN_KEYWORD); }
    "if" { return symbol(TOKEN_TYPE.IF_KEYWORD); }
    "else" { return symbol(TOKEN_TYPE.ELSE_KEYWORD); }
    "Float" { return symbol(TOKEN_TYPE.FLOAT_KEYWORD); }
    "Int" { return symbol(TOKEN_TYPE.INT_KEYWORD); }
    "data" { return symbol(TOKEN_TYPE.DATA_KEYWORD); }
    "Char" { return symbol(TOKEN_TYPE.CHAR_KEYWORD); }
    "print" { return symbol(TOKEN_TYPE.PRINT_KEYWORD); }
    "return" { return symbol(TOKEN_TYPE.RETURN_KEYWORD); }
    "read" { return symbol(TOKEN_TYPE.READ_KEYWORD); }
    "iterate" { return symbol(TOKEN_TYPE.ITERATE_KEYWORD); }

/* SIMBOLOS COM MAIS PRIORIDADES*/
    "==" { return symbol(TOKEN_TYPE.EQEQ); }
    "!=" { return symbol(TOKEN_TYPE.NE); }
    "<" { return symbol(TOKEN_TYPE.LT); }
    "&&" { return symbol(TOKEN_TYPE.ANDAND); }
    "("   { return symbol(TOKEN_TYPE.LPARENTH); }
    ")"   { return symbol(TOKEN_TYPE.RPARENTH); }
    "{"   { return symbol(TOKEN_TYPE.LBRACE); }
    "}"   { return symbol(TOKEN_TYPE.RBRACE); }
    "["   { return symbol(TOKEN_TYPE.LBRACKET); }
    "]"   { return symbol(TOKEN_TYPE.RBRACKET); }
    "::" { return symbol(TOKEN_TYPE.COLONCOLON); }
    ":" { return symbol(TOKEN_TYPE.COLON); }
    ";"   { return symbol(TOKEN_TYPE.SEMICOLON); }
    ","   { return symbol(TOKEN_TYPE.COMMA); }
    "."   { return symbol(TOKEN_TYPE.DOT); }
    "=" { return symbol(TOKEN_TYPE.EQ); }
    "!" { return symbol(TOKEN_TYPE.EXCL); }

/* OPERAÇÕES MATEMÁTICAS*/

    "+" { return symbol(TOKEN_TYPE.PLUS); }
    "-" { return symbol(TOKEN_TYPE.MINUS); }
    "*" { return symbol(TOKEN_TYPE.TIMES); }
    "/" { return symbol(TOKEN_TYPE.DIV); }
    "%" { return symbol(TOKEN_TYPE.PERC); }


/* IDENTIFICADOS GERAL */

    {identificador} { return symbol(TOKEN_TYPE.ID, yytext());   }
    {tipo}          { return symbol(TOKEN_TYPE.TYPE, yytext());   }
    {numeroDecimal} { return symbol(TOKEN_TYPE.DEC, Float.parseFloat(yytext()) );  }
    {numeroInteiro} { return symbol(TOKEN_TYPE.NUM, Integer.parseInt(yytext()) );  }
    {chacter}       { return symbol(TOKEN_TYPE.CHAR, yytext());   }
    "{-*"            { yybegin(COMMENT);}
}

<COMMENT>{
   "-}"     { yybegin(YYINITIAL); } 
   [^"-}"]* {                     }
}


[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }



