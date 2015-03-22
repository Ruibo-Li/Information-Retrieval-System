package test;

import services.APIService;
import org.json.*;
import part1.*;
import java.util.*;

/**
 * Created by ruiboli on 3/19/15.
 */
public class GetBusinessmanTest {
    public static void main(String[] args) throws Exception{
        APIService apiService = APIService.getInstance();
        JSONObject jsonObject = apiService.searchEntity("bill gates");
        Extractor e = new Extractor("");
        //String s = jsonObject.getJSONObject("/architecture/architectural_structure_owner/structures_owned").getString("valuetype");
        Map <String,List<Object>> result = e.getBusinessPerson(jsonObject);
        Iterator it = result.entrySet().iterator();
        List<Object> leaderships = result.get("Leadership");
        System.out.println("Leadership: ");
        for(Object leadership:leaderships) {
            System.out.println(leadership.toString());
        }

        List<Object> boardMembers = result.get("BoardMember");
        System.out.println("BoardMember: ");
        for(Object boardMember:boardMembers) {
            System.out.println(boardMember.toString());
        }


        List<Object> founded = result.get("Founded");
        System.out.println("Founded: ");
        for(Object boardMember:founded) {
            System.out.println(boardMember.toString());
            System.out.println("");
        }


         //System.out.println((result.get(pair.getKey()).size()));

    }
}
