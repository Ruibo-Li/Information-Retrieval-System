package test;

import services.APIService;
import org.json.*;
/**
 * Created by szeyiu on 3/18/15.
 */
public class APITest {
    public static void main(String[] args) throws Exception{
        APIService apiService = APIService.getInstance();
        //JSONObject jsonObject = apiService.searchEntity("bill gates");
        //String s = jsonObject.getJSONObject("/architecture/architectural_structure_owner/structures_owned").getString("valuetype");
        JSONObject jsonObject = apiService.MQLread("[{\"id\":null,\"name\":null,\"type\":\"/astronomy/planet\"}]");
        String s = jsonObject.toString();
        System.out.println(s);
    }
}
