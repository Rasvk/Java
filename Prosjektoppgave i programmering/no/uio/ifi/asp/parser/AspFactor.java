package no.uio.ifi.asp.parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactor extends AspSyntax {
    LinkedHashMap<AspPrimary, AspFactorPrefix> factPreMap = new LinkedHashMap<>(); //map?
    ArrayList<AspPrimary> primList = new ArrayList<>();
    ArrayList<AspFactorOpr> afoList = new ArrayList<>();

    AspFactor(int n) {
	super(n);
    }
    
    public static AspFactor parse(Scanner s) {
	enterParser("factor");
	
	AspFactor af = new AspFactor(s.curLineNum());
	while(true) {
	    AspPrimary ap = null;
	    if(s.isFactorPrefix()) {
		AspFactorPrefix afp = AspFactorPrefix.parse(s);
	        ap = AspPrimary.parse(s);
		af.factPreMap.put(ap, afp);
		af.primList.add(ap);
	    } else {
		ap = AspPrimary.parse(s);
		af.factPreMap.put(ap, null);
		af.primList.add(ap);
	    }
	    if(!s.isFactorOpr()) {
		break;
	    }
	    af.afoList.add(AspFactorOpr.parse(s));
	}
	leaveParser("factor");
	return af;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
        Iterator itr = factPreMap.entrySet().iterator();
	int i = 0;
	while(itr.hasNext()) {
	    Map.Entry pair =(Map.Entry)itr.next();
	    AspPrimary ap = (AspPrimary)pair.getKey();
	    AspFactorPrefix afp = (AspFactorPrefix)pair.getValue();
	    if(afp != null) {
		afp.prettyPrint();
	    }
	    ap.prettyPrint();
	    if(i < afoList.size()) {
		afoList.get(i).prettyPrint();
		i++;
	    }
	}

    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 3:
	RuntimeValue v = primList.get(0).eval(curScope);
	AspFactorPrefix p = factPreMap.get(primList.get(0));
	if(p != null) {
	    if(p.prefix == TokenKind.minusToken) {
		v = v.evalNegate(this);
	    } else if(p.prefix == TokenKind.plusToken) {
		v = v.evalPositive(this);
	    }
	}
	for(int i = 1; i < primList.size(); ++i) {
	    RuntimeValue b = primList.get(i).eval(curScope);
	    p = factPreMap.get(primList.get(i));
	    if(p != null) {
		if(p.prefix == TokenKind.minusToken) {
		    b = b.evalNegate(this);
		} else if(p.prefix== TokenKind.plusToken) {
		    b = b.evalPositive(this);
		}
	    }
	    TokenKind k = afoList.get(i-1).opr;
	   
	    switch(k) {
	    case percentToken:
		v = v.evalModulo(b, this); break;
	    case doubleSlashToken:
		v = v.evalIntDivide(b, this); break;
	    case slashToken:
		v = v.evalDivide(b, this); break;
	    case astToken:
		v = v.evalMultiply(b, this); break;
	    default:
		Main.panic("Illegal factor operator: " + k + "!");
	    }
	}
	
	return v;
    }
}
