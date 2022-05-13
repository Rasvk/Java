package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspComparison extends AspSyntax {
    ArrayList<AspTerm> termList = new ArrayList<>();
    ArrayList<AspCompOpr> acoList = new ArrayList<>();

    AspComparison(int n) {
	super(n);
    }

    public static AspComparison parse(Scanner s) {
	enterParser("comparison");
	
	AspComparison ac = new AspComparison(s.curLineNum());
	while(true) {
	    ac.termList.add(AspTerm.parse(s));
	    if(!s.isCompOpr()) {
		break;
	    }
	    ac.acoList.add(AspCompOpr.parse(s));
	}
	leaveParser("comparison");
	return ac;
	}    
    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	//System.out.println(termList.size()-1);
	for(int i = 0; i < termList.size(); i++) {
	    termList.get(i).prettyPrint();
	    if(i < acoList.size()) {
		acoList.get(i).prettyPrint();
	    }
	    
	    //System.out.println(termList.get(i));
	    //System.out.println(acoList.get(i));
	}
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	RuntimeValue v = termList.get(0).eval(curScope);
	
	for(int i = 1; i < termList.size(); ++i) {
	    v = termList.get(i-1).eval(curScope);
	    TokenKind k = acoList.get(i-1).opr;
	    switch (k) {
	    case lessToken:
	    	v = v.evalLess(termList.get(i).eval(curScope), this); break;
	    case greaterToken:
	    	v = v.evalGreater(termList.get(i).eval(curScope), this); break;
	    case doubleEqualToken:
	    	v = v.evalEqual(termList.get(i).eval(curScope), this); break;
	    case greaterEqualToken:
	    	v = v.evalGreaterEqual(termList.get(i).eval(curScope), this); break;
	    case lessEqualToken:
	    	v = v.evalLessEqual(termList.get(i).eval(curScope), this); break;
	     case notEqualToken:
		 v = v.evalNotEqual(termList.get(i).eval(curScope), this); break;

	    default:
	    	Main.panic("Illegal comparison operator: " + k + "!");
	    }
	    if(v.getBoolValue("if false return", this) != true) {
		return v;
	    }
	}
	return v;
    }
}
