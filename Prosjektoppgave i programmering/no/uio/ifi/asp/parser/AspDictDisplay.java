package no.uio.ifi.asp.parser;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspDictDisplay extends AspAtom {
    LinkedHashMap<AspStringLiteral, AspExpr> ddList = new LinkedHashMap<>();
    AspDictDisplay(int n) {
	super(n);
    }

    public static AspDictDisplay parse(Scanner s) {
	enterParser("dict display");
	
	AspDictDisplay add = new AspDictDisplay(s.curLineNum());
	skip(s, leftBraceToken);
	if(s.curToken().kind != rightBraceToken) {
	    while(true) {
		AspStringLiteral asl = AspStringLiteral.parse(s);
		skip(s, colonToken);
		AspExpr ae = AspExpr.parse(s);
		add.ddList.put(asl, ae);
		if(s.curToken().kind != commaToken) {
		    break;
		}
		skip(s, commaToken);
	    }
	}
	skip(s, rightBraceToken);

	leaveParser("dict display");
	return add;
    }
    
    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("{");
        Iterator itr = ddList.entrySet().iterator();
	while(itr.hasNext()) {
	    Map.Entry pair =(Map.Entry)itr.next();
	    AspStringLiteral asl = (AspStringLiteral)pair.getKey();
	    asl.prettyPrint();
	    Main.log.prettyWrite(":");
	    AspExpr ae = (AspExpr)pair.getValue();
	    ae.prettyPrint();
	    if(itr.hasNext()) {
		Main.log.prettyWrite(", ");
	    }
	}
	Main.log.prettyWrite("}");
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	LinkedHashMap<String, RuntimeValue> runtimeMap = new LinkedHashMap<>();
	Iterator itr = ddList.entrySet().iterator();
	while(itr.hasNext()) {
	    Map.Entry pair = (Map.Entry)itr.next();
	    AspStringLiteral asl = (AspStringLiteral)pair.getKey();
	    AspExpr ae = (AspExpr)pair.getValue();
	    RuntimeValue rsv = asl.eval(curScope);
	    runtimeMap.put(rsv.toString(),ae.eval(curScope));
	}
	RuntimeDictValue v = new RuntimeDictValue(runtimeMap);
	return v;
    }
}
