package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNoneLiteral extends AspAtom {
    TokenKind tk;
    String str;

    AspNoneLiteral(int n) {
	super(n);
    }

    public static AspNoneLiteral parse(Scanner s) {
	enterParser("none literal");
	
	AspNoneLiteral anl = new AspNoneLiteral(s.curLineNum());
	anl.tk = s.curToken().kind;
	anl.str = anl.tk.toString();
	skip(s, noneToken);

	leaveParser("none literal");
	return anl;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite(this.str);
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	
	return null;
    }
}
