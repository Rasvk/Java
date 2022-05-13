package no.uio.ifi.asp.runtime;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.*;

public class RuntimeDictValue extends RuntimeValue {
    LinkedHashMap<String, RuntimeValue> dictValues;

  
    /**
     * Prints out info in a readable manner
     *
     * @return list string representation of the dictionary
     */
    @Override
    public String showInfo() {
	String list = "{";
	Iterator itr = dictValues.entrySet().iterator();
	while(itr.hasNext()) {
	    Map.Entry pair = (Map.Entry)itr.next();
	    String key = (String)pair.getKey();
	    RuntimeValue v = (RuntimeValue)pair.getValue();
	    list += "'" + key + "' : " + v.showInfo();
	    if(itr.hasNext()) {
		list += ", ";
	    }
	}

	list+="}";
	return list;
    }
    
    /**
     * Constructor
     * 
     * @param LinkedHashmap v - dictionary values
     */
    public RuntimeDictValue(LinkedHashMap<String, RuntimeValue> v) {
	dictValues = v;
    }

   
    /**
     * Returns typename
     * 
     * @return type name
     */
    @Override
    protected String typeName() {
	return "Dictionary";
    }


    @Override 
    public String toString() {
	return showInfo();
    }
    
    
    /**
     * Returns the boolean value of the dicitonary
     *
     * @param what description of what is beeing done
     * @param where reference to where in the syntax tree runtime eval is done 
     * @return true
     * @return false
     */
    @Override
    public boolean getBoolValue(String what, AspSyntax where) {
    	if(!dictValues.isEmpty())
    	    return true;
    	else
    	    return false;
    } 

    /**
     * Evaluates subscriptions of the dictionary and returns the right element
     * if no key is found runtimeError
     *
     * @param v index of subscription/wich element to get from dict
     * @return returns the toString() version of element at given index
     * @return null
     */
    @Override
    public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
	if(v instanceof RuntimeStringValue) {
	    return  dictValues.get(v.toString());
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
    	if(dictValues.isEmpty())
    	    return new RuntimeBoolValue(true);
    	else
    	    return new RuntimeBoolValue(false);
    } 
}
