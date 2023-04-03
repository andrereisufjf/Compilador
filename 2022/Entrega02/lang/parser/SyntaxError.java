 /*
  * DCC045 - Teoria dos Compiladores - 2022.1
  *  André Luiz dos Reis - 201965004AC
  *  Lucca Oliveira Schröder - 201765205C
  */

package lang.parser;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.*;

import java.util.*;

public class SyntaxError extends BaseErrorListener {
    private boolean error;

    public SyntaxError(){
        super();
        error = false;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e)
    {
        this.error = true;
    }
    public boolean isError() {
        return this.error;
    }
}