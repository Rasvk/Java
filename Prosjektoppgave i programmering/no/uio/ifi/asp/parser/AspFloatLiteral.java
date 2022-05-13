package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFloatLiteral extends AspAtom {
    float number;
    AspFloatLiteral(int n) {
	super(n);
    }
    
    public static AspFloatLiteral parse(Scanner s) {
	enterParser("float literal");
	
	AspFloatLiteral afl = new AspFloatLiteral(s.curLineNum());
	afl.number = (float)s.curToken().floatLit;
	skip(s, floatToken);

	leaveParser("float literal");
	return afl;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("" + number);
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	return new RuntimeFloatValue(number);
    }
}
