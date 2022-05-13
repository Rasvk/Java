package no.uio.ifi.asp.runtime;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.*;

public class RuntimeListValue extends RuntimeValue {
    ArrayList<RuntimeValue> listValues = new ArrayList<>();

    public String showInfo() {
	String list = "[";
	int i = 0;
	for(RuntimeValue rv: listValues) {
	    list += rv.toString();
	    if(i < listValues.size()-1) {
		list += ", ";
	    }
	    i++;
	}
	list+="]";
	return list;
    }
    
    public RuntimeListValue(ArrayList<RuntimeValue> v) {
	listValues = v;
    }

    @Override
    protected String typeName() {
	return "List";
    }

    public ArrayList<RuntimeValue> getList() {
	return listValues;
    }

    @Override 
    public String toString() {
	return showInfo();
    }
    
    @Override
    public boolean getBoolValue(String what, AspSyntax where) {
	if(listValues.size() > 0)
	    return true;
	else
	    return false;
    } 
    
    @Override
    public RuntimeValue evalMultiply(RuntimeValue v, AspSyntax where) {
	int concat = (int)v.getIntValue("* operand", where);
	
	for(int i = 1; i < concat; i++) {
	    listValues.addAll(listValues);
	}
	return new RuntimeListValue(listValues);
    }
    
    @Override
    public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
	if(v instanceof RuntimeIntValue) {
	    int i = (int)v.getIntValue("subscription operation", where);
	    return listValues.get(i);
	} 
	
	runtimeError("Type error for +.", where);
	return null;  // Required by the compiler.
    }
    
    @Override
    public RuntimeValue evalNot(AspSyntax where) {
	if(listValues.size() > 0)
	    return new RuntimeBoolValue(false);
	else
	    return new RuntimeBoolValue(true);
    }

     public void evalAssignElem(RuntimeValue inx, RuntimeValue val, AspSyntax where) {
	 int v = (int)inx.getIntValue("[...] operand", where);
	 listValues.remove(v);
	 listValues.add(v, val);
     }
}
