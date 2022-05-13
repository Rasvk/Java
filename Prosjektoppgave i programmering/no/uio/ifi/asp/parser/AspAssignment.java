package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspAssignment extends AspStmt {
    AspName declName;
    ArrayList<AspSubscription> sub = new ArrayList<>();
    AspExpr expr;

    AspAssignment(int n) {
	super(n);
    }

    public static AspAssignment parse(Scanner s) {
	enterParser("assignment");
	
	AspAssignment aa = new AspAssignment(s.curLineNum());
	aa.declName = AspName.parse(s);
	 
        //if subscription
	if(s.curToken().kind == leftBracketToken) {
	    while(true) {
		aa.sub.add(AspSubscription.parse(s));
		if(s.curToken().kind != leftBracketToken) break;
	    }
	}
	//while true subscription sub.add(AspSubscription.parse(s))
	skip(s, equalToken); aa.expr = AspExpr.parse(s);
	skip(s, newLineToken);

	leaveParser("assignment");
	return aa;
    }
  
    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	
	this.declName.prettyPrint();
	for(AspSubscription as : sub) {
	    as.prettyPrint();
	}
	Main.log.prettyWrite(" = ");
	expr.prettyPrint();
	Main.log.prettyWriteLn("");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	RuntimeValue aExpr = expr.eval(curScope);      // expression part of assignment(int float list osv...)
	if(sub.size() > 0) {
	    //System.out.println("Calling name.eval(curScope) in Assignment "+ declName.name);
	    RuntimeValue aName = declName.eval(curScope); // denne bruker find
	    String str = declName.name;                    //brukes til utskrift i trace
	    for(int i = 0; i < sub.size()-1; i++) {
		RuntimeValue v = sub.get(i).eval(curScope); //beregn indeksen
		aName = aName.evalSubscription(v, this);  // kall evalSubscription aName er nå RuntimeStringValue
		str += "["+v.toString()+"]";               //opdater trace string
	    }
	    
	    if(sub.size() == 1) {
		str += "["+sub.get(0).eval(curScope).toString()+"]";
	    }
	    trace("Assigning list variable: " + str + " = " + aExpr.toString());
	    aName.evalAssignElem(aExpr, sub.get(sub.size()-1).eval(curScope), this); // kall evalAssignElem for å gjøre resten.
	} else {
	    // enkel assignment ingen subscription
	    curScope.assign(declName.name, aExpr);
	    trace("Assigning variable: " + declName.name+ " = "+ aExpr.showInfo());
	}
	return aExpr;
    }
}
