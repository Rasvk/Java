package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNotTest extends AspSyntax {    
    AspComparison comp;
    TokenKind tk;
    AspNotTest(int n) {
	super(n);
    }

    public static AspNotTest parse(Scanner s) {
	enterParser("not test");

	AspNotTest ant = new AspNotTest(s.curLineNum());
	if(s.curToken().kind == notToken) {
	    skip(s, notToken);
	    ant.tk = notToken;
	} else {
	    ant.tk = null;
	}
	ant.comp = AspComparison.parse(s);
	leaveParser("not test");
	return ant;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	if(this.tk != null) {
	    Main.log.prettyWrite(" not ");
	}
	//System.out.println(comp);
	comp.prettyPrint();
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	RuntimeValue v = comp.eval(curScope);
	if(tk != null) {
	    v = v.evalNot(this);
	}
	return v;
    }
}
