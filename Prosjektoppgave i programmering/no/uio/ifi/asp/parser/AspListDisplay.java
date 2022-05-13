package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspListDisplay extends AspAtom {
    ArrayList<AspExpr> aeList = new ArrayList<>();
    AspListDisplay(int n) {
	super(n);
    }

    public static AspListDisplay parse(Scanner s){
	enterParser("list display");
	
	AspListDisplay ald = new AspListDisplay(s.curLineNum());
	skip(s, leftBracketToken);
	if(s.curToken().kind != rightBracketToken) {
	    while(true){
		ald.aeList.add(AspExpr.parse(s));
		if(s.curToken().kind != commaToken) {
		    break;
		}
		skip(s, commaToken);
	    }
	
	}
	skip(s, rightBracketToken);

	leaveParser("list display");
	return ald;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	int i = 0;
	Main.log.prettyWrite("[");
	for(AspExpr ae: this.aeList) {
	    ae.prettyPrint();
	    if(i < this.aeList.size()-1) {
		Main.log.prettyWrite(", ");
	    }
	    i++;
	}
	Main.log.prettyWrite("]");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	ArrayList<RuntimeValue> rlv = new ArrayList<>();
	for(AspExpr ae: aeList) {
	    RuntimeValue b = ae.eval(curScope);
	    rlv.add(b);
	}
	RuntimeListValue v = new RuntimeListValue(rlv);
	return v;
    }
}
