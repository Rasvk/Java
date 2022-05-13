package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


public class AspSuite extends AspSyntax {   
    ArrayList<AspStmt> stmt = new ArrayList<>();

    AspSuite(int n) {
	super(n);
    }
    
    public static AspSuite parse(Scanner s) {
	enterParser("suite");
	
	AspSuite as = new AspSuite(s.curLineNum());
	skip(s, newLineToken);
	skip(s, indentToken);
	while(true) {
	    as.stmt.add(AspStmt.parse(s));
	    if(s.curToken().kind == dedentToken) {
		break;
	    }
	    
	}
	skip(s, dedentToken);

	leaveParser("suite");
	return as;
	    
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWriteLn("");
	Main.log.prettyIndent();
	for(AspStmt as : stmt) {
	    as.prettyPrint();
	}
	Main.log.prettyDedent();
	//Main.log.prettyWriteLn();
	
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	return null;
    }
}
