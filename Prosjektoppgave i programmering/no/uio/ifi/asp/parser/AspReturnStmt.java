package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspReturnStmt extends AspStmt {
    AspExpr retExpr;
    int lNum;
    AspReturnStmt(int n) {
	super(n);
    }
    
    public static AspReturnStmt parse(Scanner s){
	enterParser("return stmt");
	
	AspReturnStmt ars = new AspReturnStmt(s.curLineNum());
	ars.lNum = s.curLineNum();
	skip(s, returnToken); ars.retExpr = AspExpr.parse(s);
	skip(s, newLineToken);
	leaveParser("return stmt");
	return ars;
    }
    
    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("return "); //prettyWriteLN?
	this.retExpr.prettyPrint();
	Main.log.prettyWriteLn();
	//Main.log.prettyWriteLn("");
	//husk newline
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	RuntimeValue v = retExpr.eval(curScope);
	
	trace("return" + v.showInfo());
	throw new RuntimeReturnValue(v, lNum);
    }
}
