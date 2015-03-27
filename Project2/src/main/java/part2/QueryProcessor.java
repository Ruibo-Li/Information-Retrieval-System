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

    public List<String> getBusineesLeader(String question) throws Exception{
        List<String> result = new LinkedList<String>();
        String production = question.substring(12, question.length());

        String query = "[{\"/organization/organization_founder/organizations_founded\":[{\"a:name\":null,\"name~=\":\""
        + production
        +"\"}],\"id\":null,\"name\":null,\"type\":\"/organization/organization_founder\"}]";
        JSONObject queryResult = apiService.MQLread(query);
        JSONArray results = queryResult.getJSONArray("result");
        for(int j=0;j<results.length();j++) {
            JSONObject item = results.getJSONObject(j);
            StringBuilder resultString = new StringBuilder();
            resultString.append(item.getString("name"));
            resultString.append(" (as BusinessPerson) created");
            JSONArray organizationsArray = item.getJSONArray("/organization/organization_founder/organizations_founded");
            for(int k=0;k<organizationsArray.length();k++) {
                if(k>0) {
                    resultString.append(",");
                    if(k == organizationsArray.length()-1)
                        resultString.append(" and");
                }
                resultString.append(" <");
                resultString.append(organizationsArray.getJSONObject(k).getString("a:name"));
                resultString.append(">");
            }
            resultString.append(".");
            result.add(resultString.toString());
        }

        return result;
    }
    public List<String> getBookAuthor(String question) throws Exception{
        List<String> result = new LinkedList<String>();
        String production = question.substring(12, question.length());

        String query = "[{\"/book/author/works_written\":[{\"a:name\":null,\"name~=\":\""
                + production
                +"\"}],\"id\":null,\"name\":null,\"type\":\"/book/author\"}]";
        JSONObject queryResult = apiService.MQLread(query);
        JSONArray results = queryResult.getJSONArray("result");
        for(int j=0;j<results.length();j++) {
            JSONObject item = results.getJSONObject(j);
            StringBuilder resultString = new StringBuilder();
            resultString.append(item.getString("name"));
            resultString.append(" (as Author) created");
            JSONArray booksArray = item.getJSONArray("/book/author/works_written");
            for(int k=0;k<booksArray.length();k++) {
                if(k>0) {
                    resultString.append(",");
                    if(k == booksArray.length()-1)
                        resultString.append(" and");
                }
                resultString.append(" <");
                resultString.append(booksArray.getJSONObject(k).getString("a:name"));
                resultString.append(">");
            }
            resultString.append(".");
            result.add(resultString.toString());
        }

        return result;
    }
}
