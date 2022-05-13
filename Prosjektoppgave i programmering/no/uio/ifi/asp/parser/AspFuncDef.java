package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFuncDef extends AspStmt {
    AspName funcName;
    AspSuite body;
    ArrayList<AspName> param = new ArrayList<>();

    AspFuncDef(int n) {
	super(n);
    }
    
    public static AspFuncDef parse(Scanner s) {
	enterParser("func def");
	
	AspFuncDef afd = new AspFuncDef(s.curLineNum());
	skip(s, defToken); afd.funcName = AspName.parse(s);
	skip(s, leftParToken);
	if(s.curToken().kind == rightParToken) {
	    //no parameters 
	    skip(s, rightParToken);
	    
	} else {
	    while(true) {
		afd.param.add(AspName.parse(s));
		if(s.curToken().kind != commaToken) break;
		skip(s, commaToken);
	    }
	    skip(s, rightParToken);
	    //arguments separated by comma
	}

	skip(s, colonToken);
	afd.body = AspSuite.parse(s);
	leaveParser("func def");
	return afd;
    }
    
    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	int i = 0;
	Main.log.prettyWrite("def ");
	this.funcName.prettyPrint();
	Main.log.prettyWrite(" (");
	for(AspName an : param) {
	    an.prettyPrint();
	    if(i < param.size()-1) {
		Main.log.prettyWrite(", ");
	    }
	    i++;
	}
	Main.log.prettyWrite(")");
	Main.log.prettyWrite(":");
	this.body.prettyPrint();
	Main.log.prettyWriteLn();
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	//System.out.println("Calling on name.eval(curScope) in funcDef this uses find " + funcName.name);
	RuntimeValue v = null; //funcName.eval(curScope); // denne kaller find i name sin eval
	ArrayList<RuntimeValue> pl = new ArrayList<>(); // store evaluated params
	//System.out.println("func name: " + funcName.name);
	RuntimeFunc rf = new RuntimeFunc(funcName.name, param, curScope, this);
	//rf.params = param;
	//rf.def = this;
	//System.out.println("Assigning variable funcDef: " + funcName.name + " = " + rf);
	curScope.assign(funcName.name, rf);
	String str = "def " +  funcName.name;
	trace(str);
	return v;
    }

    public RuntimeValue callFunctionSuite(RuntimeScope curScope) throws RuntimeReturnValue {
	RuntimeValue suite = body.eval(curScope);
	return suite;
    }
    
}
