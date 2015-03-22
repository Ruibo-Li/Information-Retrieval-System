package test;

import org.json.JSONObject;
import part1.Extractor;
import services.APIService;

import java.util.List;

/**
 * Created by szeyiu on 3/22/15.
 */
public class ExtractorTest {
    public static void main(String[] args) throws Exception {
        APIService apiService = APIService.getInstance();
        List<String> nameMid = apiService.searchMid("Robert Downey Jr");
        String name = nameMid.get(0);
        String mid = nameMid.get(1);
        JSONObject jsonObject = apiService.topic(mid);
        Extractor extractor = new Extractor(name);
        extractor.printInfo(jsonObject);
    }
}
