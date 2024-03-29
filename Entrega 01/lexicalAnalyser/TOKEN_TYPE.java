 /*
  *  DCC045 - Teoria dos Compiladores
  *  André Luiz dos Reis - 201965004C
  *  Lucca Oliveira Schröder - 201765205C
  */

package lexicalAnalyser;

public enum TOKEN_TYPE {
    ID,
    NUM,
    EQ,
    PLUS,
    MINUS,
    TIMES,
    DIV,
    PERC,
    CHAR,
    PRINT, 
    WHILE,
    TYPE,
    DEC,
    TRUE_KEYWORD,
    FALSE_KEYWORD,
    NULL_KEYWORD,
    BOOLEAN_KEYWORD,
    IF_KEYWORD,
    ELSE_KEYWORD,
    FLOAT_KEYWORD,
    INT_KEYWORD,
    DATA_KEYWORD,
    CHAR_KEYWORD,
    PRINT_KEYWORD,
    RETURN_KEYWORD,
    READ_KEYWORD,
    ITERATE_KEYWORD,
    EQEQ,
    NE,
    LT,
    ANDAND,
    LPARENTH,
    RPARENTH,
    LBRACE,
    RBRACE,
    LBRACKET,
    RBRACKET,
    DOT,
    COLONCOLON,
    COLON,
    SEMICOLON,
    COMMA,
    EXCL
    
}
