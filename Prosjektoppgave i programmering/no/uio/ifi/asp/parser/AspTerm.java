package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


public class AspTerm extends AspSyntax {
    ArrayList<AspFactor> factList = new ArrayList<>();
    ArrayList<AspTermOpr> atoList = new ArrayList<>();

    AspTerm(int n){
	super(n);
    }

    public static AspTerm parse(Scanner s) {
	enterParser("term");
	
	AspTerm at = new AspTerm(s.curLineNum());
	while(true) {
	    at.factList.add(AspFactor.parse(s));
	    if(!s.isTermOpr()) {
		break;
	    }
	    at.atoList.add(AspTermOpr.parse(s));
	}
	leaveParser("term");
	return at;
    }



    

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	for(int i = 0, j = 0; i < factList.size(); i++) {
	    if(i > 0 && j < atoList.size()) {
		atoList.get(j).prettyPrint();
		j++;
	    }
	    factList.get(i).prettyPrint();
	 
	    
	}
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	RuntimeValue v = factList.get(0).eval(curScope);

	for (int i = 1;  i < factList.size();  ++i) {
	    TokenKind k = atoList.get(i-1).opr;
	    switch (k) {
	    case minusToken:
		v = v.evalSubtract(factList.get(i).eval(curScope), this);  break;
	    case plusToken:
		v = v.evalAdd(factList.get(i).eval(curScope), this);  break;
	    default:
		Main.panic("Illegal term operator: " + k + "!");
	    }
	}
	return v;
    }
}
