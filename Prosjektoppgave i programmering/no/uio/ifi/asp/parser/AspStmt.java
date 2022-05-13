package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspStmt extends AspSyntax {
    
    AspStmt(int n) {
	super(n);
    }

    public static AspStmt parse(Scanner s) {
	enterParser("stmt");

	AspStmt as = null;
	switch(s.curToken().kind) {
	case nameToken:
	    if(s.anyEqualToken()){
		// assignment mangler prettyPrint
		as = AspAssignment.parse(s); break;
	    } else {
		// expr
		as = AspExprStmt.parse(s); break;
	    }
	case forToken:
	    //for mangler prettyPrint
	    as = AspForStmt.parse(s); break;
	case ifToken:
	    //if mangler prettyPrint
	    as = AspIfStmt.parse(s); break;
	case whileToken:
	    //while mangler prettyPrint
	    as = AspWhileStmt.parse(s); break;
	case returnToken:
	    //return newline?
	    as = AspReturnStmt.parse(s); break;
	case passToken:
	    //pass newline?
	    as = AspPassStmt.parse(s); break;
	case defToken:
	    //def mangler prettyPrint
	    as = AspFuncDef.parse(s); break;
	case stringToken:
	    as = AspExprStmt.parse(s); break;
	case floatToken:
	    as = AspExprStmt.parse(s); break; 
	case integerToken:
	    as = AspExprStmt.parse(s); break;
	case trueToken:
	case   falseToken:
	    as = AspExprStmt.parse(s); break;
	case noneToken:
	    as = AspExprStmt.parse(s); break;
	case leftBracketToken:
	    as = AspExprStmt.parse(s); break;
	case leftBraceToken:
	    as = AspExprStmt.parse(s); break;
	case plusToken:
	    as = AspExprStmt.parse(s); break;
	case minusToken:
	    as = AspExprStmt.parse(s); break;
	case notToken:
	    as= AspExprStmt.parse(s); break;
	case andToken:
	    as = AspExprStmt.parse(s); break;
	case leftParToken:
	    as = AspExprStmt.parse(s); break;
	default:
	    parserError("Expected a statement found a " +
			s.curToken().kind + "!", s.curLineNum());
	}

	leaveParser("stmt");
	return as;
    }

}
