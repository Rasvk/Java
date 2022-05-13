package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspExpr extends AspSyntax {
    //-- Must be changed in part 2:
    ArrayList<AspAndTest> andTests = new ArrayList<>();
    AspExpr(int n) {
	super(n);
    }


    public static AspExpr parse(Scanner s) {
	enterParser("expr");

	//-- Must be changed in part 2:
	AspExpr ae = new AspExpr(s.curLineNum());
	while(true) {
	    ae.andTests.add(AspAndTest.parse(s));
	    if(s.curToken().kind != orToken) break;
	    skip(s, orToken);
	}
 
	leaveParser("expr");
	return ae;
    }


    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	int nPrinted = 0;
	for(AspAndTest aat: andTests) {
	    if(nPrinted > 0) {
		Main.log.prettyWrite(" or ");
	    }
	    aat.prettyPrint();
	    //System.out.println(nPrinted);
	    ++nPrinted;
	}
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	//System.out.println("ASP EXPR 2");
	RuntimeValue v = andTests.get(0).eval(curScope);
	for (int i = 1;  i < andTests.size();  ++i) {
	    if (v.getBoolValue("or operand",this))
		return v;
	    v = andTests.get(i).eval(curScope);
	}
	return v;
    }

}
