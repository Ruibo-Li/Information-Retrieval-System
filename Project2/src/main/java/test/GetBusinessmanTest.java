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
        Extractor e = new Extractor();
        //String s = jsonObject.getJSONObject("/architecture/architectural_structure_owner/structures_owned").getString("valuetype");
        Map <String,List<Extractor.BusinessmanObject>> result = e.getBusinessPerson(jsonObject);
        Iterator it = result.entrySet().iterator();
        List<Extractor.BusinessmanObject> leaderships = result.get("Leadership");
        System.out.println("Leadership: ");
        for(Extractor.BusinessmanObject leadership:leaderships) {
            System.out.println("From" + leadership.getFrom());
            System.out.println("To" + leadership.getTo());
            System.out.println("Organization" + leadership.getOrganization());
            System.out.println("Role" + leadership.getRole());
            System.out.println("Title" + leadership.getTitle());
            System.out.println("");
        }

        List<Extractor.BusinessmanObject> boardMembers = result.get("BoardMember");
        System.out.println("BoardMember: ");
        for(Extractor.BusinessmanObject boardMember:boardMembers) {
            System.out.println("From" + boardMember.getFrom());
            System.out.println("To" + boardMember.getTo());
            System.out.println("Organization" + boardMember.getOrganization());
            System.out.println("Role" + boardMember.getRole());
            System.out.println("Title" + boardMember.getTitle());
            System.out.println("");
        }

        List<Extractor.BusinessmanObject> founded = result.get("Founded");
        System.out.println("Founded: ");
        for(Extractor.BusinessmanObject boardMember:founded) {
            System.out.println(boardMember.getFounded());
            System.out.println("");
        }

         //System.out.println((result.get(pair.getKey()).size()));

    }
}
