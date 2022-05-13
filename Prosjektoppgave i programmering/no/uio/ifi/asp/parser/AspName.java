package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspName extends AspAtom {
    public String name;

    AspName(int n) {
	super(n);
    }
    
    public static AspName parse(Scanner s) {
	enterParser("name");
	
	AspName an = new AspName(s.curLineNum());
	an.name = s.curToken().name;
	skip(s, nameToken);

	leaveParser("name");
	return an;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite(this.name);
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	return curScope.find(name, this);
    }
}
