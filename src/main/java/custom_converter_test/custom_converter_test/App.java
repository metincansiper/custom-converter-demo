package custom_converter_test.custom_converter_test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import factoid.converter.FactoidToBiopax;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
    		Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(App.class
          .getResource("/data.json").getFile()));
        JsonObject template = gson.fromJson(reader, JsonObject.class);
        FactoidToBiopax converter = new FactoidToBiopax();
        converter.addToModel(template);
        
        String validationRes = converter.validate();
        System.out.println(validationRes);
        
        String res = converter.convertToBiopax();
        System.out.println(res);
    }
}
