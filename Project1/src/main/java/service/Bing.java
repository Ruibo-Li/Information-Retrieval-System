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
            String title = ele.getString("Title");
            String desc = ele.getString("Description");
            String url = ele.getString("Url");
            SearchResult rst = new SearchResult();
            rst.setTitle(title);
            rst.setDescription(desc);
            rst.setUrl(url);
            srlist.add(rst);
        }
        return srlist;
    }


}
