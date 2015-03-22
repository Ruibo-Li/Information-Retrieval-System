package test;

import org.json.JSONObject;
import part1.Extractor;
import services.APIService;

import java.util.*;

/**
 * Created by szeyiu on 3/21/15.
 */
public class GetSportsTeamTest {
    public static void main(String[] args) throws Exception{
        APIService apiService = APIService.getInstance();
        JSONObject jsonObject = apiService.searchEntity("NY Knicks");
        Extractor e = new Extractor("");
        Map<String, List<Object>> map = e.getSportsTeam(jsonObject);
        for(String k: map.keySet()){
            List<Object> lst = map.get(k);
            System.out.println("**********"+k+"**********");
            for(Object s: lst){
                System.out.println(s.toString());
            }
        }
    }
}
