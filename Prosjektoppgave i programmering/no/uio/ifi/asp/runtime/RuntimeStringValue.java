package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeStringValue extends RuntimeValue {
    String strValue;
    
    @Override
    public String showInfo() {
	if(strValue.indexOf('\'') >= 0)
	    return '"' + strValue + '"';
	else
	    return "'" + strValue + "'";
    }

    public RuntimeStringValue(String v){
	strValue = v;
    }

    @Override
    protected String typeName() {
	return "String";
    }


    @Override 
    public String toString() {
	return strValue;
    }
    
    @Override
    public String getStringValue(String what, AspSyntax where) {
	return strValue;  // Required by the compiler!
    }

    @Override
    public boolean getBoolValue(String what, AspSyntax where) {
	if(strValue == "")
	    return false;
	else
	    return true;
    }
    
    /**
     * Concatenates v1(strVlue) with v2(RuntimeValue v) and returns it
     *
     * @param v String to concatenate with strValue
     * @param where reference to where in the syntax tree runtime eval is done 
     * @return returns the new string
     */
    @Override
    public RuntimeValue evalAdd(RuntimeValue v, AspSyntax where) {
	return new RuntimeStringValue(strValue.concat(v.getStringValue("String concat", where)));
    }
    
    /**
     * Concatenates v1(strVlue), v2 times(RuntimeValue v) and returns it
     *
     * @param v nr of times to concatenate
     * @param where reference to where in the syntax tree runtime eval is done 
     * @return returns the new string
     */
    @Override
    public RuntimeValue evalMultiply(RuntimeValue v, AspSyntax where) {
	int concat = (int)v.getIntValue("* operand", where);
	//System.out.println("CONKAT NR TIMES: " + concat);
	String s = "";
	//System.out.println(s);
	for(int i = 0; i < concat; i++) {
	    s = s.concat(strValue);
	    //System.out.println(s);
	}
	return new RuntimeStringValue(s);
    }
    
    /**
     * Evaluates equality of two strings and returns the corresponding boolean value
     * if no key is found runtimeError
     *
     * @param v 2nd part of the expression ie.  "hei" == "he"
     * @return returns the coresponding boolean to the exression
     * @return runtimeError
     */
    @Override
    public RuntimeValue evalEqual(RuntimeValue v, AspSyntax where) {
	if(strValue.equals(v.getStringValue("== operator", where))) {
	    return new RuntimeBoolValue(true);
	}
	return new RuntimeBoolValue(v instanceof RuntimeNoneValue); // Required by the compiler.
    }
    
    /**
     * Evaluates less of two strings and returns the corresponding boolean value
     * if no key is found runtimeError
     *
     * @param v 2nd part of the expression ie.  "hei" != "he"
     * @return returns the coresponding boolean to the exression
     * @return runtimeError
     */
    @Override
    public RuntimeValue evalNotEqual(RuntimeValue v, AspSyntax where) {
	if(!strValue.equals(v.getStringValue("!= perator", where))) {
	    return new RuntimeBoolValue(true);
	}
	return new RuntimeBoolValue(v instanceof RuntimeNoneValue);
    }
    
     /**
     * Evaluates less of two strings  and returns the corresponding boolean value
     * if no key is found runtimeError
     *
     * @param v 2nd part of the expression ie. "hallo" < "2"
     * @return returns the coresponding boolean to the exression
     * @return runtimeError
     */
    @Override
    public RuntimeValue evalLess(RuntimeValue v, AspSyntax where) {
	if(strValue.compareTo(v.getStringValue("< operand", where)) == -1) {
	    return new RuntimeBoolValue(true);
	}
	return new RuntimeBoolValue(v instanceof RuntimeNoneValue); // Required by the compiler.
    }

     /**
     * Evaluates greater of two strings  and returns the corresponding boolean value
     * if no key is found runtimeError
     *
     * @param v 2nd part of the expression ie. "hei" > "h"
     * @return returns the coresponding boolean to the exression
     * @return runtimeError
     */
    @Override
    public RuntimeValue evalGreater(RuntimeValue v, AspSyntax where) {
	if(strValue.compareTo(v.getStringValue("< operand", where)) == 1) {
	    return new RuntimeBoolValue(true);
	}
	return new RuntimeBoolValue(v instanceof RuntimeNoneValue); // Required by the compiler.
    }

    /**
     * Evaluates greater equal of two strings  and returns the corresponding boolean value
     * if no key is found runtimeError
     *
     * @param v 2nd part of the expression ie. "go" >= "hello"
     * @return returns the coresponding boolean to the exression
     * @return runtimeError
     */
    @Override
    public RuntimeValue evalGreaterEqual(RuntimeValue v, AspSyntax where) {
	if(strValue.compareTo(v.getStringValue(">= operand", where)) == 0 || strValue.compareTo(v.getStringValue(">=", where)) == 1) {
	    return new RuntimeBoolValue(true);
	}
	return new RuntimeBoolValue(v instanceof RuntimeNoneValue); // Required by the compiler.
    }
    
    /**
     * Evaluates less equal of two strings  and returns the corresponding boolean value
     * if no key is found runtimeError
     *
     * @param v 2nd part of the expression ie. 1 <= 2
     * @return returns the coresponding boolean to the exression
     * @return runtimeError
     */
    @Override
    public RuntimeValue evalLessEqual(RuntimeValue v, AspSyntax where) {
	if(strValue.compareTo(v.getStringValue("<= operand", where)) == 0 || strValue.compareTo(v.getStringValue("<=", where)) == -1) {
	    return new RuntimeBoolValue(true);
	}

	runtimeError("Type error for <=.", where);
	return new RuntimeBoolValue(v instanceof RuntimeNoneValue);  // Required by the compiler.
    }

    /**
     * Evaluates subscriptions of the string and returns the right element
     * if no key is found runtimeError
     *
     * @param v index of subscription/wich element to get from dict
     * @return returns the toString() version of element at given index
     */
    @Override
    public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
	if(v instanceof RuntimeIntValue) {
	    String s = "" + strValue.charAt((int)v.getIntValue("subscription[...]", where));
	    return new RuntimeStringValue(s);
	}    

    	runtimeError("Type error for [...].", where);
    	return null;  // Required by the compiler.
    }

     /**
     * Evaluates the not expression and returns the coresponding boolean
     *
     * @param where reference to where in the syntax tree runtime eval is done 
     * @return RuntimeBoolValue() - true or false.
     */
    @Override
    public RuntimeValue evalNot(AspSyntax where) {
	if(strValue != "") {
	    return new RuntimeBoolValue(false);
	} else {
	    return new RuntimeBoolValue(true);
	}
    }
}
