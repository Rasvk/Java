package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspSubscription extends AspPrimarySuffix {
    AspExpr ae;

    AspSubscription(int n) {
	super(n);
    }

    public static AspSubscription parse(Scanner s) {
	enterParser("subscription");
	
	AspSubscription as = new AspSubscription(s.curLineNum());
	skip(s, leftBracketToken);
	as.ae = AspExpr.parse(s);
	skip(s, rightBracketToken);

	leaveParser("subscription");
	return as;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("[");
	this.ae.prettyPrint();
	Main.log.prettyWrite("]");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	return ae.eval(curScope);
    }
}
