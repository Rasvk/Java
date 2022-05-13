package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspIfStmt extends AspStmt {
    ArrayList<AspSuite> body = new ArrayList<>();
    ArrayList<AspExpr> test = new ArrayList<>();

    AspIfStmt(int n) {
	super(n);
    }

    public static AspIfStmt parse(Scanner s) {
	enterParser("if stmt");
	
	AspIfStmt ais = new AspIfStmt(s.curLineNum());
	skip(s, ifToken); ais.test.add(AspExpr.parse(s));
	skip(s, colonToken); ais.body.add(AspSuite.parse(s));
	if(s.curToken().kind == elifToken) {
	    skip(s, elifToken);
	    while(true) {
		ais.test.add(AspExpr.parse(s));
		skip(s, colonToken);
		ais.body.add(AspSuite.parse(s));
		if(s.curToken().kind != elifToken) break;
		skip(s, elifToken);
	    }
	}
	if(s.curToken().kind == elseToken) {
	    skip(s, elseToken); skip(s, colonToken);
	    ais.body.add(AspSuite.parse(s));
	}
	leaveParser("if stmt");
	return ais;
    }

    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
	Main.log.prettyWrite("if ");
	for(int i = 0; i < body.size(); i++) {
	    
	    if(i == body.size()-1 && i != 0){
		Main.log.prettyWrite("else:");
		body.get(i).prettyPrint();
		
	    } 
	    if(i > 0 && i < body.size()-1) {
		Main.log.prettyWrite("elif ");
		test.get(i).prettyPrint();
		Main.log.prettyWrite(":");
		body.get(i).prettyPrint();
	
	    } 
	    if(i == 0) {
	    	test.get(i).prettyPrint();
		Main.log.prettyWrite(":");
		body.get(i).prettyPrint();
	    }
	    //test.get(i).prettyPrint();
	    //Main.log.prettyWrite(":");
	    //body.get(i).prettyPrint();
	}
	
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
	//-- Must be changed in part 4:
	int len = body.size() - test.size();
	System.out.println("HELLO");
	for(int i = 0; i < test.size(); i++) {
	    AspExpr ae = test.get(i);
	    RuntimeValue ifTest = ae.eval(curScope);
	    if(ifTest.getBoolValue("if Test", this)) {
		String str = "if True alt #" + i + ": ...";
		trace(str);
		body.get(i).eval(curScope);
	    } else if(len == 1 && i == test.size()-1) { // do else start
		System.out.println("we are doing else");
		trace("else: ... ");
		body.get(i+1).eval(curScope);
	    }// else slutt
	}
	return null;
    }
}
