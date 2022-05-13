package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspPrimary extends AspSyntax {   
    AspAtom atom;
    ArrayList<AspPrimarySuffix> apsList = new ArrayList<>();
   
    AspPrimary(int n) {
	super(n);
    }

    public static AspPrimary parse(Scanner s) {
	enterParser("primary");
	
	AspPrimary ap = new AspPrimary(s.curLineNum());
	ap.atom = AspAtom.parse(s);
	if(s.curToken().kind == leftBracketToken || s.curToken().kind == leftParToken){
	    while(true) {
		ap.apsList.add(AspPrimarySuffix.parse(s));
		if(s.curToken().kind != leftBracketToken || s.curToken().kind != leftParToken){
		    break;
		}
	    }
	}
	leaveParser("primary");
	return ap;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	this.atom.prettyPrint();
	for(AspPrimarySuffix aps : apsList) {
	    aps.prettyPrint();
	}
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	RuntimeValue v = atom.eval(curScope);
	if( apsList.size() != 0) {
	    for(int i = 0; i < apsList.size(); i++) {
		RuntimeValue b = apsList.get(i).eval(curScope);
		if(apsList.get(i) instanceof AspSubscription) {
		    v = v.evalSubscription(b, this);
		} else if(apsList.get(i) instanceof AspArguments) { // primary suffix kan bare være subscription eller arguments
		    RuntimeListValue args = (RuntimeListValue) b; //arguments er alltid RuntimeListValue
		    //System.out.println("useing find in asp primary, looking for func: " + v.toString());
		    RuntimeValue func = curScope.find(v.toString(), this);
		    //System.out.println("function found: " + func.typeName());
		    String str = "Call function " + v.toString() + 
			" with params " + args.showInfo();
		    trace(str);
		    v = func.evalFuncCall(args.getList(), this); // 2. la AspPrimary kalle evalFuncCall
		}
	    }
	}
	return v;
    }
}
