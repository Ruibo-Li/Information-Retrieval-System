package service;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.codec.binary.Base64;
/**
 * Created by ruiboli on 2/7/15.
 */
public class Bing {
    public static String getResult(String query,String key) throws Exception{
        String bingUrl = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%27"+query+"%27&$top=10&$format=Json";
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

    public static void main(String[] args) throws Exception {
        String content = getResult("tiger","OGwFXUbDNNw/U2hhi/vhjWdahU36dTuk5V2ZUis7+VU");
        System.out.println(content);
    }
}
