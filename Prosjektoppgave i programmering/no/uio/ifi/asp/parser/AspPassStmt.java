package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspPassStmt extends AspStmt {
   
    AspPassStmt(int n) {
	super(n);
    }
    
    public static AspPassStmt parse(Scanner s) {
	enterParser("Pass stmt");
	AspPassStmt aps = new AspPassStmt(s.curLineNum());
	skip(s, passToken); skip(s, newLineToken);
	leaveParser("Pass stmt");
	return aps;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("pass");
	Main.log.prettyWriteLn("");
	// newline?
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	return null;
    }
}
