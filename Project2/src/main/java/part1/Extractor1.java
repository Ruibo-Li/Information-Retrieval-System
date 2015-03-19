package part1;
import java.util.*;
import org.json.*;

/**
 * Created by ruiboli on 3/18/15.
 */
public class Extractor1 {
    public List<String> getEntity(JSONObject jsonObject){
        return null;
    }

    public Map<String,List<String>> getAttribute(JSONObject jsonObject){
        return null;
    }

    public Map<String,List<String>> getAuthor(JSONObject jsonObject){
        Map<String, List<String>>  map = new HashMap<String, List<String>>();
        //Book(Title)
        getAuthorHelper(map, jsonObject, "/book/author/works_written", "Book");
        //Book About(the author)
        getAuthorHelper(map, jsonObject, "/book/book_subject/works", "Book About");
        //Influenced
        getAuthorHelper(map, jsonObject, "/influence/influence_node/influenced", "Influenced");
        //Influenced By
        getAuthorHelper(map, jsonObject, "/influence/influence_node/influenced_by", "Influenced By");
        return map;
    }

    private void getAuthorHelper(Map<String, List<String>>  map, JSONObject jsonObject, String path, String title){
        if(jsonObject.isNull(path) || jsonObject.getJSONObject(path).isNull("values")) return;
        JSONArray jsonArray = jsonObject.getJSONObject(path).getJSONArray("values");
        if(jsonArray.length()==0) return;
        map.put(title, new ArrayList<String>());
        List<String> lst = map.get(title);
        for(int i=0; i<jsonArray.length(); ++i){
            String content = jsonArray.getJSONObject(i).getString("text");
            lst.add(content);
        }
    }


    public Map<String,List<String>> getLeague(JSONObject jsonObject){
        return null;
    }

    public Map<String,List<String>> getSportsTeam(JSONObject jsonObject){
        return null;
    }

}
