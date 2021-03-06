package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspWhileStmt extends AspStmt {
    AspExpr test;
    AspSuite body;

    AspWhileStmt(int n) {
	super(n);
    }
    public static AspWhileStmt parse(Scanner s) {
	enterParser("while stmt");
	AspWhileStmt aws = new AspWhileStmt(s.curLineNum());
	skip(s, whileToken);  aws.test = AspExpr.parse(s);
	skip(s, colonToken);  aws.body = AspSuite.parse(s);
	leaveParser("while stmt");
	return aws;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("while ");
	this.test.prettyPrint();
	Main.log.prettyWrite(":");
	this.body.prettyPrint();
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	while(true) {
	    RuntimeValue t = test.eval(curScope);
	    if(!t.getBoolValue("while loop test", this)) break;
	    trace("while True: ...");
	    body.eval(curScope);
	}
	trace("while False:");
	return null;
    }
}
