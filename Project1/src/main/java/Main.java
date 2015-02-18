import service.*;
import java.util.*;
import pojo.*;
/**
 * Created by szeyiu on 2/7/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<String> qlist = Arrays.asList("bill", "gates");

        String jscontent = Bing.search(qlist, "OGwFXUbDNNw/U2hhi/vhjWdahU36dTuk5V2ZUis7+VU");
        List<SearchResult> searchResults = Bing.json2Result(jscontent);
        for(SearchResult searchResult: searchResults){
            System.out.println(searchResult.toString());
        }
    }
}
