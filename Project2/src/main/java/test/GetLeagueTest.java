package test;

import org.json.JSONObject;
import part1.Extractor;
import services.APIService;

import java.util.List;
import java.util.Map;

/**
 * Created by szeyiu on 3/21/15.
 */
public class GetLeagueTest {
    public static void main(String[] args) throws Exception{
        APIService apiService = APIService.getInstance();
        JSONObject jsonObject = apiService.searchEntity("NBA");
        Extractor e = new Extractor();
        Map<String, List<Object>> map = e.getLeague(jsonObject);
        for(String k: map.keySet()){
            List<Object> lst = map.get(k);
            System.out.println("**********"+k+"**********");
            for(Object s: lst){
                System.out.println(s.toString());
            }
        }
    }
}
