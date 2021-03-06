package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspProgram extends AspSyntax {
    //-- Must be changed in part 2:
    ArrayList<AspStmt> stmts = new ArrayList<>();

    AspProgram(int n) {
	super(n);
    }


    public static AspProgram parse(Scanner s) {
	enterParser("program");

	AspProgram ap = new AspProgram(s.curLineNum());
	while (s.curToken().kind != eofToken) {
	    //-- Must be changed in part 2:
	    ap.stmts.add(AspStmt.parse(s));
	}

	leaveParser("program");
	return ap;
    }


    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
        for(AspStmt as : stmts) {
	    as.prettyPrint();
	}
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	trace("evaluating statements in program");
	for(AspStmt s: stmts) {
	    try {
		s.eval(curScope);
	    } catch(RuntimeReturnValue rrv) {
		RuntimeValue.runtimeError("Error! Return statement outside of function! ", rrv.lineNum);
	    }
	}
	return null;
    }
}
