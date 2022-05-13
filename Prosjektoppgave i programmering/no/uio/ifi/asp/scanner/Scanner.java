package no.uio.ifi.asp.scanner;

import java.io.*;
import java.util.*;

import no.uio.ifi.asp.main.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class Scanner {
    private LineNumberReader sourceFile = null;
    private String curFileName;
    private ArrayList<Token> curLineTokens = new ArrayList<>();
    private int indents[] = new int[100];
    private int numIndents = 0;
    private final int tabDist = 4;


    public Scanner(String fileName) {
	curFileName = fileName;
	indents[0] = 0;  numIndents = 1;

	try {
	    sourceFile = new LineNumberReader(
			    new InputStreamReader(
				new FileInputStream(fileName),
				"UTF-8"));
	} catch (IOException e) {
	    scannerError("Cannot read " + fileName + "!");
	}
    }


    private void scannerError(String message) {
	String m = "Asp scanner error";
	if (curLineNum() > 0)
	    m += " on line " + curLineNum();
	m += ": " + message;

	Main.error(m);
    }


    public Token curToken() {
	while (curLineTokens.isEmpty()) {
	    readNextLine();
	}
	return curLineTokens.get(0);
    }


    public void readNextToken() {
	if (! curLineTokens.isEmpty())
	    curLineTokens.remove(0);
    }


    public boolean anyEqualToken() {
	for (Token t: curLineTokens) {
	    if (t.kind == equalToken) return true;
	}
	return false;
    }


    private void readNextLine() {
	curLineTokens.clear();

	// Read the next line:
	String line = null;
	try {
	    line = sourceFile.readLine();
	    if (line == null) {
		sourceFile.close();
		sourceFile = null;
		while(indents[numIndents-1] != indents[0]) {
		    indents[numIndents-1] = 0;
		    curLineTokens.add(new Token(dedentToken, curLineNum()));
		}
		curLineTokens.add(new Token(eofToken));	
	    } else {
		Main.log.noteSourceLine(curLineNum(), line);
	    }
	} catch (IOException e) {
	    sourceFile = null;
	    scannerError("Unspecified I/O error!");
	}
	
	//-- Must be changed in part 1:
	tokenMaker(line);



	
	// end changes
	for (Token t: curLineTokens) 
	    Main.log.noteToken(t);
    
    }
    /**
     * Method checks if line is null or contains only whitespaces, then checks if line starts with # all the above options it ignores.
     * it then expands leading tabs of the line if any are present before it decides the indentation level of the line compared to 
     * the last line read. TO DO: Parse line and generate appropriate tokens and add them to curLineTokens.
     * 
     * @param line, String to be parsed and tokenized.
     */
    private void tokenMaker(String line) {
	if(line != null) {
	    if(line.trim().length() !=  0) { // find if line is empty or not
		if(line.charAt(line.indexOf(line.trim())) != '#') { // find if first none whitespace char is a # or not
		    expandLeadingTabs(line); // changes leading tabs to whitespaces
	
		    indentDedentTokenMaker(line); 	// calculating indentation
		    //parse line for other tokens
		    int j = 0;
		    char c, nc;

		    while(j < line.length()) {
			c = line.charAt(j);
		
			if(c == '\"' ) { //is string
			    String str = "";
			    int count = 1;
			    for(int i = j+1; i < line.length(); i++) {
				c = line.charAt(i);
				if(c !='\"') {
				    str+=c;
				} else {
				    count++;
				    Token strLit = new Token(stringToken, curLineNum());
				    strLit.stringLit = str;
				    curLineTokens.add(strLit);
				    j=i;
				    break;
				}
			    }
			    if(count < 2) {
			    	Main.error("Asp scanner error on line " + curLineNum() + ": string literal not terminated!");
			    }
			}		    
			else if(c == '\'' ) { // is string
			    String str = "";
			    int count = 1;			    
			    for(int i = j+1; i < line.length(); i++) {
				c = line.charAt(i);
				if(c !='\'') {
				    str+=c;
				} else {
				    count++;
				    Token strLit = new Token(stringToken, curLineNum());
				    strLit.stringLit = str;
				    curLineTokens.add(strLit);
				    j=i;
				    break;
				}
			    }
			    if(count < 2) {
			    	Main.error("Asp scanner error on line " + curLineNum() + ": string literal not terminated!");
			    } 
			
			    //checking legal single char tokens in asp    
			} else if( c == '+') {
			    curLineTokens.add(new Token(plusToken, curLineNum())); 
			}  else if( c == '-') {
			    curLineTokens.add(new Token(minusToken, curLineNum()));
			} else if( c == '*') {
			     curLineTokens.add(new Token(astToken, curLineNum()));
			} else if( c == '%') {
			    curLineTokens.add(new Token(percentToken, curLineNum()));
			} else if( c == ':') {
			    curLineTokens.add(new Token(colonToken, curLineNum()));
			} else if( c == ',') {
			    curLineTokens.add(new Token(commaToken, curLineNum()));
			} else if( c == '{') {
			    curLineTokens.add(new Token(leftBraceToken, curLineNum()));
			} else if( c == '[') {
			    curLineTokens.add(new Token(leftBracketToken, curLineNum()));
			} else if( c == '(') {
			    curLineTokens.add(new Token(leftParToken, curLineNum()));
			} else if( c == '}') {
			    curLineTokens.add(new Token(rightBraceToken, curLineNum()));
			} else if( c == ']') {
			    curLineTokens.add(new Token(rightBracketToken, curLineNum()));
			} else if( c == ')') {
			    curLineTokens.add(new Token(rightParToken, curLineNum()));
			} else if ( c == '=') { // have to check for 2 char tokens in asp such as == // <= >= != and also their single char version
			    nc = line.charAt(j+1);
			    if(nc == '=') {
				curLineTokens.add(new Token(doubleEqualToken, curLineNum()));
				j++; 
			    } else {
				curLineTokens.add(new Token(equalToken, curLineNum()));
			    }
			} else if ( c == '/') {
			    nc = line.charAt(j+1);
			    if(nc == '/') {
				curLineTokens.add(new Token(doubleSlashToken, curLineNum()));
				j++;
			    } else {
				curLineTokens.add(new Token(slashToken, curLineNum()));
			    }
			} else if( c == '<') {
			    nc = line.charAt(j+1);
			    if(nc == '=') {
				curLineTokens.add(new Token(lessEqualToken, curLineNum()));
				j++;
			    } else {
				curLineTokens.add(new Token(lessToken, curLineNum()));
			    }
			} else if( c == '>') {
			      nc = line.charAt(j+1);
			      if(nc == '=') {
				curLineTokens.add(new Token(greaterEqualToken, curLineNum()));
				j++;
			    } else {
				curLineTokens.add(new Token(greaterToken, curLineNum()));
			    }
			} else if( c == '!') {
			    nc = line.charAt(j+1);
			    if(nc == '=') {
			    	curLineTokens.add(new Token(notEqualToken, curLineNum()));
				j++;
			    } else {
				Main.error("Asp scanner error on line " + curLineNum() + ": illegal character: '" + c+ "'!");
			    }
			} else if(isLetterAZ(c)){ // names can only start with letters
			    String name = "" + c;
			    int i = j;
			    if(i < line.length()-1) {
				i+=1;
				nc = line.charAt(i);
				if(isLetterAZ(nc) || isDigit(nc)) { // but can contain numbers
				    name+=nc;
				} else {
				    i--; //need to jump one back so we don't skip it when parsing through the rest of the line.
				}
				while(isLetterAZ(nc)|| isDigit(nc)) { // rpeat the above part and parsing the rest of the line.
				    if(i < line.length()-1){
					i+=1;
					nc = line.charAt(i);
				    } else {
					//i--;
					j = i;
					break;
				    }				   
				    if(isLetterAZ(nc) || isDigit(nc)) { 
					name+=nc;
				    } else {
					i--;
				    }
				}
				
				j = i;
			    }
			   			    
			    if(name.equals("and")) {
				curLineTokens.add(new Token(andToken, curLineNum()));
			    } else if(name.equals("def")) {
				curLineTokens.add(new Token(defToken, curLineNum()));
			    } else if(name.equals("elif")) {
				curLineTokens.add(new Token(elifToken, curLineNum()));
			    } else if(name.equals("else")) {
				curLineTokens.add(new Token(elseToken, curLineNum()));
			    } else if(name.equals("False")) {
				curLineTokens.add(new Token(falseToken, curLineNum()));
			    } else if(name.equals("for")) {
				curLineTokens.add(new Token(forToken, curLineNum()));
			    } else if(name.equals("if")) {
				curLineTokens.add(new Token(ifToken, curLineNum()));
			    } else if(name.equals("in")) {
				curLineTokens.add(new Token(inToken, curLineNum()));
			    } else if(name.equals("None")) {
				curLineTokens.add(new Token(noneToken, curLineNum()));
			    } else if(name.equals("not")) {
				curLineTokens.add(new Token(notToken, curLineNum()));
			    } else if(name.equals("or")) {
				curLineTokens.add(new Token(orToken, curLineNum()));
			    } else if(name.equals("pass")) {
				curLineTokens.add(new Token(passToken, curLineNum()));
			    } else if(name.equals("return")) {
				curLineTokens.add(new Token(returnToken, curLineNum()));
			    } else if(name.equals("True")) {
				curLineTokens.add(new Token(trueToken, curLineNum()));
			    } else if(name.equals("while")) {
				curLineTokens.add(new Token(whileToken, curLineNum()));
			    } else {
				Token tName = new Token(nameToken,curLineNum());
				tName.name = name;
				curLineTokens.add(tName);
			    } // add reserved names from python before this else so they can't be used in asp as names
			   
			} else if(isDigit(c)) { // checking for ints or floats
			    String intOrFloat = "" + c;
			    int i = j+1;
			    while(i < line.length()) {
				c = line.charAt(i);
				if(isDigit(c) || c == '.') { 
				    intOrFloat+=c;
				} else {
				    break;
				}
				i++;
			    }
			    j = i-1;
			    if(intOrFloat.charAt(0) == '.' || intOrFloat.charAt(intOrFloat.length()-1) == '.') {
				Main.error("Asp scanner error on line " + curLineNum() + ": illegal float literal: '" + intOrFloat+ "'!");	
			    } else if(isInteger(intOrFloat)) {
				Token ifToken = new Token(integerToken, curLineNum());
				ifToken.integerLit = Integer.parseInt(intOrFloat);
				curLineTokens.add(ifToken);
			    } else if(isFloat(intOrFloat)) {
				Token ifToken = new Token(floatToken, curLineNum());
				ifToken.floatLit = Double.parseDouble(intOrFloat); // skjer noen rare rundings feil ved skriving til fil feks på 1071.1 i expressions.asp???
				//System.out.println("parsed number: " + intOrFloat + " got: " + Float.parseFloat(intOrFloat));
				curLineTokens.add(ifToken);
			    }
			   
			}
			else if(c == '#') { // checking for inline comments
			    j = line.length();
			} else {
			    if(c != ' ') { // should be only whitespaces on the line that haven't been checked so far that are legal characters, rest are illegal.		       
				Main.error("Asp scanner error on line " + curLineNum() + ": illegal character: '" + c+ "'!");		      
			    }
			}
			
		      
			j++;
		    } 
		    curLineTokens.add(new Token(newLineToken,curLineNum()));
		}	
	    }
	}
    }

    /**
     * Implements the algorithm described in the INF2100 compendium for keeping  track of indentation in asp
     * 
     * @param line, current line to check indentation of
     */
    private void indentDedentTokenMaker(String line){
	// for hver linje
        int leadingWS = findIndent(line); //tell innledende blanke n
	if(leadingWS > indents[numIndents-1]){
	    indents[numIndents] = leadingWS; // push n på indents
	    numIndents++;
	    curLineTokens.add(new Token(indentToken, curLineNum())); // legg til INDENT symbol i curLineTokens
	}// hvis n > indents.top
	
	if(leadingWS != indents[numIndents-1]) {
	    int i = numIndents-1;
	    while(indents[i] > leadingWS ) {
		indents[i] = 0; // pop indents
		curLineTokens.add(new Token(dedentToken, curLineNum())); // legg et DEDENT symbol i curLineTokens
		i--;
		numIndents--;
	    }
	    indents[numIndents] = leadingWS;  
	    	
	}// hvis n < indents.top
	if(leadingWS != indents[numIndents-1]) {
	    Main.error("Asp scanner error on line " + curLineNum() + ": indentation error!");
	    //error message.
	}

	// etter at siste linje er lest for alle verdier > 0 legg DEDENT symbol i curLineTokens
    }
    
    private boolean isInteger(String i) {
	try {
	    Integer.parseInt(i);
	    return true;
	} catch(NumberFormatException e) {
	    return false;
	}
    }

        private boolean isFloat(String i) {
	try {
	    Float.parseFloat(i);
	    return true;
	} catch(NumberFormatException e) {
	    return false;
	}
    }
        
    public int curLineNum() {
	return sourceFile!=null ? sourceFile.getLineNumber() : 0;
    }

    private int findIndent(String s) {
	int indent = 0;

	while (indent<s.length() && s.charAt(indent)==' ') indent++;
	return indent;
    }

    private String expandLeadingTabs(String s) {
	String newS = "";
	for (int i = 0;  i < s.length();  i++) {
	    char c = s.charAt(i);
	    if (c == '\t') {
		do {
		    newS += " ";
		} while (newS.length()%tabDist != 0);
	    } else if (c == ' ') {
		newS += " ";
	    } else {
		newS += s.substring(i);
		break;
	    }
	}
	return newS;
    }


    private boolean isLetterAZ(char c) {
	return ('A'<=c && c<='Z') || ('a'<=c && c<='z') || (c=='_');
    }


    private boolean isDigit(char c) {
	return '0'<=c && c<='9';
    }


    public boolean isCompOpr() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return (k == lessToken || k == lessEqualToken ||
	      k == greaterToken || k == greaterEqualToken || 
		k == notEqualToken ||k == doubleEqualToken);
    }


    public boolean isFactorPrefix() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return (k == plusToken || k == minusToken);
    }


    public boolean isFactorOpr() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return (k == astToken || k == slashToken || 
		k == percentToken || k == doubleSlashToken);
    }
	

    public boolean isTermOpr() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return this.isFactorPrefix();
    }
}
