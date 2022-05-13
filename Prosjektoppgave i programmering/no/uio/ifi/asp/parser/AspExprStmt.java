package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspExprStmt extends AspStmt {
    AspExpr ae;

    AspExprStmt(int n) {
	super(n);
    }
    
    public static AspExprStmt parse(Scanner s) {
	enterParser("expr stmt");
	
	AspExprStmt aes = new AspExprStmt(s.curLineNum());
	aes.ae = AspExpr.parse(s); skip(s, newLineToken);

	leaveParser("expr stmt");
	return aes;
    }
    
    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	this.ae.prettyPrint();
	Main.log.prettyWriteLn("");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	RuntimeValue v = ae.eval(curScope);
	trace(v.showInfo());
	return v;
    }
}
