package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorOpr extends AspSyntax {
    TokenKind opr;

    AspFactorOpr(int n) {
	super(n);
    }

    public static AspFactorOpr parse(Scanner s) {
	enterParser("factor opr");

	AspFactorOpr afo = new AspFactorOpr(s.curLineNum());
	afo.opr = s.curToken().kind;
	s.readNextToken();
	
	leaveParser("factor opr");
	return afo;
    }
    
    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite(" "+opr.toString() +" ");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	return null;
    }
}
