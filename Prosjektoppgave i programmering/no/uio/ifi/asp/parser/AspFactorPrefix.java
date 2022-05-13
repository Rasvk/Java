package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorPrefix extends AspSyntax {
    TokenKind prefix;
    AspFactorPrefix(int n) {
	super(n);
    }

    public static AspFactorPrefix parse(Scanner s) {
	enterParser("factor prefix");
	
	AspFactorPrefix afp = new AspFactorPrefix(s.curLineNum());
	afp.prefix = s.curToken().kind;
	test(s, plusToken, minusToken);
	s.readNextToken();
	//test(s, plusToken, minusToken);
	//kan bruke test tk1 tk2 her

	leaveParser("factor prefix");
	return afp;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite(this.prefix.toString()+ " ");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:

	return null;
    }
}
