package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspForStmt extends AspStmt {
    AspName index;
    AspExpr test;
    AspSuite body;

    AspForStmt(int n) {
	super(n);
    }

    public static AspForStmt parse(Scanner s) {
	enterParser("for stmt");
	AspForStmt afs = new AspForStmt(s.curLineNum());
	skip(s, forToken); afs.index = AspName.parse(s);
	skip(s, inToken); afs.test = AspExpr.parse(s);
	skip(s, colonToken); afs.body = AspSuite.parse(s);
	leaveParser("for stmt");
	return afs;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("for ");
	this.index.prettyPrint();
	Main.log.prettyWrite(" in ");
	this.test.prettyPrint();
	Main.log.prettyWrite(":");
	this.body.prettyPrint();
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	
	RuntimeValue rv = test.eval(curScope);
	if(rv instanceof RuntimeListValue) {
	    RuntimeListValue rlv = (RuntimeListValue) rv;
	    ArrayList<RuntimeValue> rl = rlv.getList();
	    for(int i = 0; i < rl.size(); i++) {
		String str = "For #" + (i+1) + ": " + index.name + " = " + i;
		trace(str);
		curScope.assign(index.name, rl.get(i));
		body.eval(curScope);
	    }
	} else {
	    trace("no list in for exiting program");
	}
	
	return null;
    }
}
