package part2;

import org.json.*;
import services.APIService;
import java.util.*;

/**
 * Created by ruiboli on 3/23/15.
 */
public class QueryProcessor {
    APIService apiService;

    public QueryProcessor(String key){
        if(key==null || key.equals("")) apiService = APIService.getInstance();
        else apiService = APIService.getInstanceWithKey(key);
    }

    public List<List<String>> getBusinessLeader(String question) throws Exception{
        List<List<String>> result = new LinkedList<List<String>>();
        String production = question.substring(12, question.length());

        String query = "[{\"/organization/organization_founder/organizations_founded\":[{\"a:name\":null,\"name~=\":\""
        + production
        +"\"}],\"id\":null,\"name\":null,\"type\":\"/organization/organization_founder\"}]";
        JSONObject queryResult = apiService.MQLread(query);
        JSONArray results = queryResult.getJSONArray("result");
        for(int j=0;j<results.length();j++) {
            JSONObject item = results.getJSONObject(j);
            List <String> resultlist = new LinkedList<String>();
            resultlist.add(item.getString("name"));
            resultlist.add("BusinessPerson");
            JSONArray organizationsArray = item.getJSONArray("/organization/organization_founder/organizations_founded");
            for(int k=0;k<organizationsArray.length();k++)
                resultlist.add(organizationsArray.getJSONObject(k).getString("a:name"));
            result.add(resultlist);
        }

        return result;
    }
    public List<List<String>> getBookAuthor(String question) throws Exception{
        List<List<String>> result = new LinkedList<List<String>>();
        String production = question.substring(12, question.length());

        String query = "[{\"/book/author/works_written\":[{\"a:name\":null,\"name~=\":\""
                + production
                +"\"}],\"id\":null,\"name\":null,\"type\":\"/book/author\"}]";
        JSONObject queryResult = apiService.MQLread(query);
        JSONArray results = queryResult.getJSONArray("result");
        for(int j=0;j<results.length();j++) {
            JSONObject item = results.getJSONObject(j);
            List <String> resultlist = new LinkedList<String>();
            resultlist.add(item.getString("name"));
            resultlist.add("Author");
            JSONArray booksArray = item.getJSONArray("/book/author/works_written");
            for(int k=0;k<booksArray.length();k++)
                resultlist.add(booksArray.getJSONObject(k).getString("a:name"));
            result.add(resultlist);
        }

        return result;
    }
}
