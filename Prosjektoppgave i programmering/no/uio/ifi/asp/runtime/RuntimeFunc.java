package no.uio.ifi.asp.runtime;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;
import no.uio.ifi.asp.parser.AspFuncDef;
import no.uio.ifi.asp.parser.AspName;

public class RuntimeFunc extends RuntimeValue {
    AspFuncDef def;
    RuntimeScope defScope;
    String type;
    ArrayList <AspName> params = new ArrayList<>();

    public RuntimeFunc(String t) {
	type = t;
    }

    public RuntimeFunc(String t, ArrayList<AspName> list, RuntimeScope defS, AspFuncDef d) {
	def = d;
	defScope = defS;
	type = t;
	params = list;
    }

    @Override
    public String typeName() {
	return type;
    }

    public String toString() {
	return type;
    }

    public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> p, AspSyntax where) {

	// 3. sjekk antall parametere
	if(p.size() == params.size()) {
	    RuntimeScope newScope = new RuntimeScope(defScope);    // 4. oprett et nytt skop
	
	    // 4. Tilordne aktuelle til formelle parametre
	    for(int i = 0; i < params.size(); i++) {
		//System.out.println("Assigning in runtimefunc " + params.get(i).name + " = " + p.get(i));
		//System.out.println(params.get(i).name + " index: " +i + "size: " + params.size() + " p.get(i): " + p.get(i));
		newScope.assign(params.get(i).name, p.get(i));
		//System.out.println("Assign done Runtime func");
	    }

	    try {
		def.callFunctionSuite(newScope); // 5. utføre funksjonen ved  kall på AspSuite.eval()
	    } catch(RuntimeReturnValue rrv) {
		return rrv.value;
	    }
	   
	} else {
	    Main.error("Error, parameter mismatch, actual and formal parameters don't match");
	}
	return new RuntimeNoneValue(); 
    }
}
