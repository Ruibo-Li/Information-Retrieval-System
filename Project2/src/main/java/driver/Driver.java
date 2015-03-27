package driver;

import org.json.JSONObject;
import part1.Extractor;
import part2.QueryProcessor;
import services.APIService;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by szeyiu on 3/26/15.
 */
public class Driver {
    public static void part1(String key, String q) throws Exception{
        if(q==null || q.trim().equals("")) return;
        APIService apiService = APIService.getInstanceWithKey(key);
        int idx = 0;
        boolean hasInfo = false;
        while(!hasInfo) {
            List<String> nameMid = apiService.searchMid(q, idx);
            if (nameMid == null) {
                System.out.println("No Result!");
                return;
            }
            String name = nameMid.get(0);
            String mid = nameMid.get(1);
            JSONObject jsonObject = apiService.topic(mid);
            Extractor extractor = new Extractor(name);
            hasInfo = extractor.printInfo(jsonObject);
            idx++;
            if(idx%5==0){
                System.out.println("Searched "+idx+" results and no supported info...");
            }
        }
    }
    public static void printTableQuestion(String q, String line) {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        int spaceLeft = (line.length()-2-q.length())/2;
        for(int i=0;i<spaceLeft;i++)
            sb.append(" ");
        sb.append(q);
        int spaceRight = line.length()-sb.length()-1;
        for(int i=0;i<spaceRight;i++)
            sb.append(" ");
        sb.append("|");
        System.out.println(sb.toString());
        System.out.println(line);
    }
    public static void adjust(StringBuilder sb, String line) {
        if(sb.length()>line.length()-7) {
            sb.delete(line.length() - 7, sb.length());
            sb.append("...");
        }
    }
    public static void formatline(StringBuilder sb, int length) {
        int spaceRight = length - sb.length() - 1;
        for(int i=0;i<spaceRight;i++)
            sb.append(" ");
        sb.append("|");
    }
    public static void pritnTableItem(List <String> strl, String line) {
        StringBuilder firstline = new StringBuilder();
        firstline.append("| ");
        firstline.append(strl.get(0));
        firstline.append(":");
        formatline(firstline, 22);
        firstline.append(" As                 ");
        firstline.append("| Creation");
        formatline(firstline, line.length());
        System.out.println(firstline);
        String leftSpace = "|                    | ";
        System.out.println("|                    ------------------------------------------------------------------------------");
        StringBuilder secondline = new StringBuilder();
        secondline.append(leftSpace);
        secondline.append(strl.get(1));
        formatline(secondline, 43);
        secondline.append(" ");
        secondline.append(strl.get(2));
        adjust(secondline, line);
        formatline(secondline, line.length());
        System.out.println(secondline.toString());
        for(int i=3;i<strl.size();i++) {
            StringBuilder newline = new StringBuilder();
            newline.append("|                    |                    | ");
            newline.append(strl.get(i));
            adjust(newline, line);
            formatline(newline, line.length());
            System.out.println(newline.toString());
        }
        System.out.println(line);
    }
    public static class Comp implements Comparator<List<String>> {
        public int compare(List<String> L1, List<String> L2) {
            String s1 = L1.get(0);
            String s2 = L2.get(0);
            return s1.compareTo(s2);
        }
    }
    public static void part2(String key, String q, Boolean tableprint) throws Exception{
        q = q.trim();
        String line = " -------------------------------------------------------------------------------------------------- ";
        if(!q.toLowerCase().matches("who created .+")){
            System.out.println("Wrong Question!");
            return;
        }
        QueryProcessor queryProcessor = new QueryProcessor(key);
        List<List<String>> result = queryProcessor.getBusinessLeader(q);
        List<List<String>> authorResult = queryProcessor.getBookAuthor(q);
        result.addAll(authorResult);
        Collections.sort(result,new Comp());

        //List<String> result = queryProcessor.getBookAuthor("Who Created Lord of the Rings?");
        if(tableprint){
            System.out.println(line);
            printTableQuestion(q, line);
            for(List<String> strl:result)
                pritnTableItem(strl, line);
        }
        else {
            for(int i=0;i<result.size();i++){
                List<String> cur = result.get(i);
                StringBuffer sb = new StringBuffer();
                sb.append(i+1);
                sb.append(". ");
                sb.append(cur.get(0));
                sb.append(" (as ");
                sb.append(cur.get(1));
                sb.append(") created");
                for(int j=2;j<cur.size();j++) {
                    if(j>2) {
                        sb.append(",");
                        if(j == cur.size()-1)
                            sb.append(" and");
                    }
                    sb.append(" <");
                    sb.append(cur.get(j));
                    sb.append(">");
                }
                sb.append(".");
                System.out.println(sb.toString());
            }
        }
    }
}
