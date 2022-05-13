import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileUtils;
import org.apache.jena.query.*;
import java.io.*;
public class Oblig4 {
    private Model rdfs;
    private Model url;
    private Model res; // maybe change var names to something easier to understand? URL does not make much sense

    /**
     * Constructor
     */
    public Oblig4() {
	rdfs = ModelFactory.createDefaultModel();
	url = ModelFactory.createDefaultModel();
	res = ModelFactory.createDefaultModel();
    }
    
    /**
     * calls the readFile model with appropriate arguments.
     *
     * @param rdfsFile
     * @param urlFile
     */
    public void readModels(String rdfsFile, String urlFile) {
	readFile(rdfs, rdfsFile, FileUtils.guessLang(rdfsFile));
	readFile(url, urlFile, FileUtils.guessLang(urlFile));
    } 

    /**
     * reads the file with appropriate language and stores it in given model
     *
     * @param m
     * @param file
     * @param lang
     */
    public void readFile(Model m, String file, String lang) {
	try {
	    m.read(file, lang);
	} catch (Exception e) {
	    System.out.println("Somethjing went wrong reading file: " 
			       + file + " Exiting program"); // add more sensible error msg
	    System.exit(0);
	}
    }

    /**
     * Executes a construct query and writes the result to file by calling writeFile method
     *
     * @param queryFile
     * @param output
     */
    public void doQuery(String queryFile, String output) {
	Model m = ModelFactory.createRDFSModel(rdfs,url);       
	Query q = QueryFactory.read(queryFile);
	QueryExecution qexec = QueryExecutionFactory.create(q, m);
	//m.write(System.out, "TTL");
	res = qexec.execConstruct();
	res.write(System.out, "TTL");
	writeFile(res, output);
    }
    
    /**
     * Tries to open a new FileWriter and write the rdf to the output file in TTL
     */
    public void writeFile(Model m, String output) {
	try{
	    FileWriter out = new FileWriter(output);
	    m.write(out, "TTL");
	} catch (IOException writeException) {
	    System.out.println("ERROR! Something went wrong while writing to file: " + output);
	    System.exit(0);
	}
    }

    public static void main(String[] args) {
	String schema, query, url, output;
	Oblig4 o4;
	//org.apache.log4j.BasicConfigurator.configure();
        
	if(args.length != 4) { //kuriositet fra min makefile som gjorde at dette ble litt tullete, kan legge filer som dependencies
	    // i stedet for flagg burde løse problemet
	    System.out.println("ERROR! NOT ENOUGH ARGUMENTS! + " + args.length);
	    for(String s: args) {
		System.out.println(s);
	    }
	    System.exit(0);
	}
	
	schema = args[0];
	query = args[1];
	output = args[2];
	url = "http://sws.ifi.uio.no/inf3580/v15/oblig/3/simpsons";
	o4 = new Oblig4();
	o4.readModels(schema, url);
        o4.doQuery(query, output);

    }	
}

