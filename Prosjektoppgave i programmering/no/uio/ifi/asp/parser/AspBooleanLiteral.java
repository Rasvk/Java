package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspBooleanLiteral extends AspAtom {
    TokenKind tk;
    boolean boolValue;

    AspBooleanLiteral(int n) {
	super(n);
    }

    public static AspBooleanLiteral parse(Scanner s) {
	enterParser("boolean literal");
	
	AspBooleanLiteral abl = new AspBooleanLiteral(s.curLineNum());
       
	switch (s.curToken().kind) {
	case trueToken:
	    abl.tk = trueToken;
	    abl.boolValue = true;
	    skip(s, trueToken);
	    break;
	case falseToken:
	    abl.tk = falseToken;
	    abl.boolValue = false;
	    skip(s, falseToken);
	    break;
	default:
	    parserError("Expected a boolean but found " + 
			s.curToken().kind + "!", s.curLineNum());
	}
	
	leaveParser("boolean literal");
	return abl;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite(tk.toString());
    }

    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	return new RuntimeBoolValue(boolValue);
  
    }
}
