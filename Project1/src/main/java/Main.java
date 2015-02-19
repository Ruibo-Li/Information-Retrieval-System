import IRModel.Naive;
import service.*;
import java.util.*;
import pojo.*;
/**
 * Created by szeyiu on 2/7/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<String> qlist = new ArrayList<String>();
        qlist.add("gates".toLowerCase());
        //qlist.add("american");
        //qlist.add("(born");
        //qlist.add("bill");
        //qlist.add("computer");
        String key = "OGwFXUbDNNw/U2hhi/vhjWdahU36dTuk5V2ZUis7+VU";

        Naive naive = new Naive();
        while(qlist!=null){
            List<SearchResult> searchResults = Bing.Interact(qlist, key);
            qlist = naive.updateQuery(qlist, searchResults);
        }

    }
}
