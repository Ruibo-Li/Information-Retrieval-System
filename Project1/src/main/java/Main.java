import IRModel.Naive;
import IRModel.NaiveTunned;
import service.*;
import java.util.*;
import pojo.*;
/**
 * Created by szeyiu on 2/7/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<String> qlist = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your query, use space to split if more than 1 word:");
        String line = scanner.nextLine();
        String[] wordlist = line.split(" ");
        for(String w: wordlist) {
            if(w.trim().length()>0)
                qlist.add(w.toLowerCase());
        }
        String key = "OGwFXUbDNNw/U2hhi/vhjWdahU36dTuk5V2ZUis7+VU";

        NaiveTunned naive = new NaiveTunned();
        while(qlist!=null){
            List<SearchResult> searchResults = Bing.Interact(qlist, key);
            qlist = naive.updateQuery(qlist, searchResults);
        }

    }
}
