import service.*;
import java.util.*;
import pojo.*;
/**
 * Created by szeyiu on 2/7/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<String> qlist = Arrays.asList("bill", "gates");
        String key = "OGwFXUbDNNw/U2hhi/vhjWdahU36dTuk5V2ZUis7+VU";
        Bing.Interact(qlist, key);
/*
        String jscontent = Bing.search(qlist, key);
        List<SearchResult> searchResults = Bing.json2Result(jscontent);
        for(SearchResult searchResult: searchResults){
            System.out.println(searchResult.toString());
        }
        */
    }
}
