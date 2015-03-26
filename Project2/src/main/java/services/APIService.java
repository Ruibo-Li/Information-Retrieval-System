package services;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.net.URI;
import org.json.*;
/**
 * Created by ruiboli on 3/18/15.
 */
public class APIService {
    private static CloseableHttpClient httpClient;
    private static APIService apiService;
    private String APIKey = "AIzaSyBvdMWKakXD-QBUN4P7c0obKSfGlQQeoxY";
    private String QUERY_URL = "https://www.googleapis.com/freebase/v1/search";
    private String TOPIC_URL = "https://www.googleapis.com/freebase/v1/topic";
    private String MQL_URL = "https://www.googleapis.com/freebase/v1/mqlread";

    private APIService(String APIKey){
        this.APIKey = APIKey;
        httpClient = HttpClients.custom()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .build();
    }
    public JSONObject MQLread (String query) throws Exception{
        HttpResponse response;
        HttpEntity responseEntity = null;
        HttpGet httpGet = new HttpGet();
        String serverOutput = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("query", query));
        params.add(new BasicNameValuePair("key", APIKey));
        httpGet.setURI(URI.create(MQL_URL + "?" + URLEncodedUtils.format(params, "UTF-8")));
        response = httpClient.execute(httpGet);
        try {
            responseEntity = response.getEntity();
            if (responseEntity != null)
                serverOutput = EntityUtils.toString(responseEntity);
        } finally {
            EntityUtils.consume(responseEntity);
            httpGet.releaseConnection();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(serverOutput);
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static APIService getInstanceWithKey(String APIKey){
        if(apiService == null) apiService = new APIService(APIKey);
        return apiService;
    }
    public static APIService getInstance(){
        if(apiService==null) apiService = new APIService("AIzaSyBvdMWKakXD-QBUN4P7c0obKSfGlQQeoxY");
        return apiService;
    }

    public List<String> searchMid(String keyword, int index) throws Exception{
        HttpResponse response;
        HttpEntity responseEntity = null;
        HttpGet httpGet = new HttpGet();
        String serverOutput = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("key", APIKey));
        params.add(new BasicNameValuePair("query", keyword));
        httpGet.setURI(URI.create(QUERY_URL + "?" + URLEncodedUtils.format(params, "UTF-8")));
        response = httpClient.execute(httpGet);
        try {
            responseEntity = response.getEntity();
            if (responseEntity != null)
                serverOutput = EntityUtils.toString(responseEntity);
        } finally {
            EntityUtils.consume(responseEntity);
            httpGet.releaseConnection();
        }
        String mid = "";
        String name = "";
        try {
            JSONObject jsonObject = new JSONObject(serverOutput);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            if(jsonArray.length()<=index) return null;
            jsonObject = jsonArray.getJSONObject(index);
            mid = jsonObject.getString("mid");
            name = jsonObject.getString("name");
        } catch (Exception e){
            e.printStackTrace();
        }
        List<String> rst = new ArrayList<String>();
        rst.add(name);
        rst.add(mid);
        return rst;
    }

    public JSONObject topic(String mid) throws Exception{
        HttpResponse response;
        HttpEntity responseEntity = null;
        HttpGet httpGet = new HttpGet();
        String serverOutput = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("key", APIKey));
        httpGet.setURI(URI.create(TOPIC_URL + mid + "?" + URLEncodedUtils.format(params, "UTF-8")));
        response = httpClient.execute(httpGet);
        try {
            responseEntity = response.getEntity();
            if (responseEntity != null)
                serverOutput = EntityUtils.toString(responseEntity);
        } finally {
            EntityUtils.consume(responseEntity);
            httpGet.releaseConnection();
        }
        JSONObject jsonObject = null;
        try {
            //System.out.println(serverOutput);
            jsonObject = new JSONObject(serverOutput);
            jsonObject = jsonObject.getJSONObject("property");
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject searchEntity(String keyword, int idx) throws Exception{
        List<String> nameMid = searchMid(keyword, idx);
        if(nameMid==null) return null;
        return topic(nameMid.get(1));
    }


}
