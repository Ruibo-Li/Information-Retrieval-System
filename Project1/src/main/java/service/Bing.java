package service;
import java.io.*;
import java.util.*;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.codec.binary.Base64;
import pojo.*;
import org.json.*;
/**
 * Created by ruiboli on 2/7/15.
 */
public class Bing {
    public static String search(List<String> queryList, String key) throws Exception {
        StringBuilder log = new StringBuilder();
        log.append("Search");
        for(String keyword :queryList) {
            log.append(' ');
            log.append("\"");
            log.append(keyword);
            log.append("\"");
        }
        log.append("...");
        System.out.println(log.toString());
        String q = "";
        for(int i=0; i<queryList.size();++i){
            q += queryList.get(i);
            if(i<queryList.size()-1)    q+="%20";
        }
        String bingUrl = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%27" + q + "%27&$top=10&$format=Json";
        String accountKey = key;

        byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
        String accountKeyEnc = new String(accountKeyBytes);

        URL url = new URL(bingUrl);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);

        InputStream inputStream = (InputStream) urlConnection.getContent();
        byte[] contentRaw = new byte[urlConnection.getContentLength()];
        inputStream.read(contentRaw);
        String content = new String(contentRaw);

        //The content string is the xml/json output from Bing.
        return content;
    }

    /**
     * Parse the json and get the search result list.
     * @param jsonResult
     * @return
     */
    public static List<SearchResult> json2Result(String jsonResult){
        JSONObject jsonObject = new JSONObject(jsonResult);
        JSONArray resultList = jsonObject.getJSONObject("d").getJSONArray("results");
        List<SearchResult> srlist = new ArrayList<SearchResult>();
        for(int i=0; i<resultList.length(); ++i){
            JSONObject ele = resultList.getJSONObject(i);
            String title = ele.getString("Title").toLowerCase();
            String desc = ele.getString("Description").toLowerCase();
            String url = ele.getString("Url").toLowerCase();
            SearchResult rst = new SearchResult();
            rst.setTitle(title);
            rst.setDescription(desc);
            rst.setUrl(url);
            rst.setTopK(i);
            srlist.add(rst);
        }
        return srlist;
    }

    public static List<SearchResult> Interact(List<String> queryList, String key) throws Exception{
        String jscontent = search(queryList, key);
        List<SearchResult> searchResults = Bing.json2Result(jscontent);
        Scanner user_input = new Scanner(System.in);

        for(SearchResult searchResult: searchResults){
            System.out.println(searchResult.toString());
            while(true){
                System.out.println("Is this content relevant? (yes/no)");
                String answer = user_input.next().toLowerCase();
                if(answer.equals("yes")||answer.equals("y")) {
                    searchResult.setRelevant(true);
                    break;
                }
                else if(answer.equals("no")||answer.equals("n")) {
                    searchResult.setRelevant(false);
                    break;
                }
                else
                    System.out.println("Invalid input. Please input again.");
            }
        }
        return searchResults;
    }
}
