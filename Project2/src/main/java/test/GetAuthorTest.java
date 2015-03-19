package test;

import org.json.JSONObject;
import part1.Extractor;
import part1.Extractor1;
import services.APIService;

import java.util.List;
import java.util.Map;

/**
 * Created by szeyiu on 3/18/15.
 */
public class GetAuthorTest {
    public static void main(String[] args) throws Exception{
        APIService apiService = APIService.getInstance();
        JSONObject jsonObject = apiService.searchEntity("bill gates");
        Extractor1 e = new Extractor1();
        Map<String, List<String>> map = e.getAuthor(jsonObject);
        for(String k: map.keySet()){
            List<String> lst = map.get(k);
            System.out.println("**********"+k+"**********");
            for(String s: lst){
                System.out.println(s);
            }
        }

    }
}
