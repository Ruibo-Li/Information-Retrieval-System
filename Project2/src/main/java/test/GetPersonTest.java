package test;

import services.APIService;
import org.json.*;
import part1.*;
import java.util.*;
/**
 * Created by szeyiu on 3/18/15.
 */
public class GetPersonTest {
    public static void main(String[] args) throws Exception{
        APIService apiService = APIService.getInstance();
        JSONObject jsonObject = apiService.searchEntity("brad pitt");
        //String s = jsonObject.getJSONObject("/architecture/architectural_structure_owner/structures_owned").getString("valuetype");
        Extractor e = new Extractor();
        Map <String,List<String>> result = e.getActor(jsonObject);
        Iterator it = result.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            //System.out.println((result.get(pair.getKey()).size()));
        }
    }
}
