package custom_converter_demo.custom_converter_demo;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import factoid.converter.FactoidToBiopax;

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
	// Google json library instance
    	Gson gson = new Gson();
	// create a reader for factoid input json data
        JsonReader reader = new JsonReader(new FileReader(App.class
          .getResource("/data.json").getFile()));
	// create the json object from the file reader
	// it will be the input for factoid json to biopax conversion
        JsonObject template = gson.fromJson(reader, JsonObject.class);
	// we need to attach the absolute path of the groovy script to the data that we read from
	// data.json file for each of the custom interactions
	// each groovy script tells what must be done for the conversion
    // in this example we want each of the custom interactions to behave in the same way
    // otherwise we would use different groovy scripts for different custom interactions
        String scriptRelPath = "src/main/resources/GroovyIntn.groovy";
	// absolute path of the project
        String projectPath = System.getProperty("user.dir");
	// finilize the script path by adding the relative path of script to the absolute path of the project
	String scriptPath = projectPath + "/" + scriptRelPath;
	// iterate through all of the custom interactions and add the scriptPath property to them
        for ( JsonElement el : template.get("interactions").getAsJsonArray() ) {
        		JsonObject obj = el.getAsJsonObject();
        		if ( obj.get("type").getAsString().equals("Custom Interaction") ) {
        			obj.addProperty("scriptPath", scriptPath);
        		}
        }
	// create a factoid to biopax converter instance
        FactoidToBiopax converter = new FactoidToBiopax();
	// add the conversion template (the data in JsonObject format) to the factoid model
	// so that it will be used during the conversion
        converter.addToModel(template);
        
	// make the validation on the biopax modal in memory
        String validationRes = converter.validate();
        System.out.println("validation result:");
        System.out.println(validationRes);
        
	// export biopax model to a string
        String res = converter.convertToBiopax();
        System.out.println("conversion result:");
        System.out.println(res);
    }
}
