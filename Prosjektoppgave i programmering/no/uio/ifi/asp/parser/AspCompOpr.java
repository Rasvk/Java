package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*; 
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspCompOpr extends AspSyntax {
    TokenKind opr;

    AspCompOpr(int n) {
	super(n);
    }

    public static AspCompOpr parse(Scanner s) {
	enterParser("comp opr");

	AspCompOpr aco = new AspCompOpr(s.curLineNum());
	aco.opr = s.curToken().kind;
	s.readNextToken();

	leaveParser("comp opr");
	return aco;
    }
    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite(" " +this.opr.toString()+ " ");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	//blir aldri kalt?
	return null;
    }
}
