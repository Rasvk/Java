package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspIntegerLiteral extends AspAtom {
    long number;

    AspIntegerLiteral(int n){
	super(n);
    }

    public static AspIntegerLiteral parse(Scanner s) {
	enterParser("integer literal");

	AspIntegerLiteral ail = new AspIntegerLiteral(s.curLineNum());
	ail.number = s.curToken().integerLit;
	skip(s, integerToken);

	leaveParser("integer literal");
	return ail;
    }
    
    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("" + number);
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	return new RuntimeIntValue(number);
    }
}
