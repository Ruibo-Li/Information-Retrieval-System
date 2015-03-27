package driver;

import org.json.JSONObject;
import part1.Extractor;
import part2.QueryProcessor;
import services.APIService;

import java.util.Collections;
import java.util.List;

/**
 * Created by szeyiu on 3/26/15.
 */
public class Driver {
    public static void part1(String key, String q) throws Exception{
        if(q==null || q.trim().equals("")) return;
        APIService apiService = APIService.getInstanceWithKey(key);
        int idx = 0;
        boolean hasInfo = false;
        while(!hasInfo) {
            List<String> nameMid = apiService.searchMid(q, idx);
            if (nameMid == null) {
                System.out.println("No Result!");
                return;
            }
            String name = nameMid.get(0);
            String mid = nameMid.get(1);
            JSONObject jsonObject = apiService.topic(mid);
            Extractor extractor = new Extractor(name);
            hasInfo = extractor.printInfo(jsonObject);
            idx++;
            if(idx%5==0){
                System.out.println("Searched "+idx+" results and no supported info...");
            }
        }
    }
    public static void part2(String key, String q) throws Exception{
        q = q.trim();
        if(!q.toLowerCase().matches("who created .+")){
            System.out.println("Wrong Question!");
            return;
        }
        QueryProcessor queryProcessor = new QueryProcessor(key);
        List<String> result = queryProcessor.getBusineesLeader(q);
        List<String> authorResult = queryProcessor.getBookAuthor(q);
        result.addAll(authorResult);
        Collections.sort(result);
        //List<String> result = queryProcessor.getBookAuthor("Who Created Lord of the Rings?");
        for(int i=0;i<result.size();i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(i+1);
            sb.append(". ");
            sb.append(result.get(i));
            System.out.println(sb.toString());
        }
    }
}
