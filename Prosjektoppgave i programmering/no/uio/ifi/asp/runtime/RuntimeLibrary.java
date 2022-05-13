package no.uio.ifi.asp.runtime;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.NumberFormatException;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeLibrary extends RuntimeScope {
    private Scanner keyboard = new Scanner(System.in);

    public RuntimeLibrary() {
	//-- Must be changed in part 4:
	// len
	assign("len", new RuntimeFunc("len") {
		@Override
		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
		    checkNumParams(actualParams, 1, "len", where);
		    return actualParams.get(0).evalLen(where);
		}});

	//input
	assign("input", new RuntimeFunc("input"){
		@Override
		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams,  AspSyntax where){
		    checkNumParams(actualParams, 1, "input", where); // strengt tatt unødvendig 
		    Scanner input = new Scanner(System.in);
		    String output = actualParams.get(0).toString();
		    System.out.print(output);
		    String str = input.nextLine();
		    return new RuntimeStringValue(str);
		}});

	//int
        assign("int", new RuntimeFunc("int")
	    {

		@Override
		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams,
						 AspSyntax where) {
		    checkNumParams(actualParams, 1, "int", where);
		    try{
			if(actualParams.get(0) instanceof RuntimeStringValue) {
			    return new RuntimeIntValue(Integer.parseInt(actualParams.get(0).toString()));
			}
		    }
		    catch(NumberFormatException e){
			runtimeError("can not convert string: " + actualParams.get(0).toString() + " to int", where);
		    }
		    return new RuntimeIntValue(actualParams.get(0).getIntValue("int func", where));
		}});

	//float
	assign("float", new RuntimeFunc("float")
	    {

		@Override
		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams,
						 AspSyntax where) {
		    checkNumParams(actualParams, 1, "int", where);
		    try{
			if(actualParams.get(0) instanceof RuntimeStringValue)
			    return new RuntimeFloatValue(Double.parseDouble(actualParams.get(0).toString()));
		    }
		    catch(NumberFormatException e){
			runtimeError("can not convert string: " + actualParams.get(0).toString() + " to float", where);
		    }
		    return new RuntimeFloatValue(actualParams.get(0).getFloatValue("int func", where));
		}});

	//str
        assign("str", new RuntimeFunc("str")
	    {

		@Override
		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
		    checkNumParams(actualParams, 1, "str", where);
		    return new RuntimeStringValue(actualParams.get(0).toString());
		}});

	// print
	assign("print", new RuntimeFunc("print") {
		@Override
		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
		    
		    for (int i = 0;  i < actualParams.size();  ++i) {
			if (i > 0) System.out.print(" ");
			
			System.out.print(actualParams.get(i).toString());
		    }
		    System.out.println();
		    return new RuntimeNoneValue();
		}});

	//range TODO
	assign("range", new RuntimeFunc("range") {
	    @Override
	    public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
		checkNumParams(actualParams, 2, "range", where);
		// lag listen range(v1, v2) = [v1, ....,v2-1]
	        if(actualParams.get(0) instanceof RuntimeIntValue && actualParams.get(1) instanceof RuntimeIntValue) {
		    ArrayList<RuntimeValue> al = new ArrayList<>();
		    int rangeStart = (int)actualParams.get(0).getIntValue("range", where);
		    int rangeStop = (int)actualParams.get(1).getIntValue("range", where);
		    for(int i =  rangeStart; i < rangeStop; i++) {
			al.add(new RuntimeIntValue((long)i));
		    }
		    
		    return new RuntimeListValue(al);
			    
		} else {
		    runtimeError("Range arguments need to be of type int, not type: " + actualParams.get(0).typeName(), where);
		    return null;
		}
        
	    }});
    }


    private void checkNumParams(ArrayList<RuntimeValue> actArgs, 
				int nCorrect, String id, AspSyntax where) {
	if (actArgs.size() != nCorrect)
	    RuntimeValue.runtimeError("Wrong number of parameters to "+id+"!",where);
    }
}
