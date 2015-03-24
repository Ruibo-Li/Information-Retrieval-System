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
        JSONObject jsonObject = apiService.MQLread("[{\"/visual_art/visual_artist/artworks\":[{\"a:name\":null,\"name~=\":\"Mona Lisa\"}],\"id\":null,\"name\":null,\"type\":\"/visual_art/visual_artist\"}]");
        String s = jsonObject.toString();
        System.out.println(s);
    }
}
