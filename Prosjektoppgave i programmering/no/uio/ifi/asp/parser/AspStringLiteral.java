package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspStringLiteral extends AspAtom {
    String str;

    AspStringLiteral(int n) {
	super(n);
    }

    public static AspStringLiteral parse(Scanner s) {
	enterParser("string literal");

	AspStringLiteral asl = new AspStringLiteral(s.curLineNum());
	asl.str = s.curToken().stringLit;
	//System.out.println(asl.str);
	skip(s, stringToken);

	leaveParser("string literal");
	return asl;
    }
   

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("\"");
	Main.log.prettyWrite(str);
	Main.log.prettyWrite("\"");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part :
	return new RuntimeStringValue(str);
    }
}
