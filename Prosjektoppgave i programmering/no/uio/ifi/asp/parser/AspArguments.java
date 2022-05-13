package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspArguments extends AspPrimarySuffix {
    ArrayList<AspExpr> exprList = new ArrayList<>();

    AspArguments(int n) {
	super(n);
    }
    
    public static AspArguments parse(Scanner s) {
	enterParser("arguments");
	
	AspArguments aa = new AspArguments(s.curLineNum());
	skip(s, leftParToken);
	if(s.curToken().kind != rightParToken) {
	    while(true){
		aa.exprList.add(AspExpr.parse(s));
		if(s.curToken().kind != commaToken){
		    break;
		}
		skip(s, commaToken);
	    }
	}
	skip(s,rightParToken);
	leaveParser("arguments");
	return aa;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	int i = 0;
	Main.log.prettyWrite("(");
	for(AspExpr ae: exprList) {
	    ae.prettyPrint();
	    if(i < exprList.size()-1) {
		Main.log.prettyWrite(", ");
	    }
	    i++;
	}
	Main.log.prettyWrite(")");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3 & 4:
	RuntimeListValue rlv = null;
	RuntimeValue v = exprList.get(0).eval(curScope);
	ArrayList<RuntimeValue> rv = new ArrayList<>();
	rv.add(v);
	for(int i = 1; i < exprList.size(); i++) {
	    v = exprList.get(i).eval(curScope);
	    rv.add(v);
	}
	rlv = new RuntimeListValue(rv); // 1. Beregn aktuelle parametere
	                                // 2. la AspPrimary kalle rv.evalFuncCall
	return rlv;
    }
}
