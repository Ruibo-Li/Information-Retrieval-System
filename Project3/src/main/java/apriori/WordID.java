package apriori;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by szeyiu on 4/14/15.
 */
public class WordID {
    static List<String> id2Word = new ArrayList<String>();
    static HashMap<String, Integer> word2Id = new HashMap<String, Integer>();
    static int size = 0;
    static void load(String input) throws IOException {
        File fin = new File(input);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fin)));
        String line = reader.readLine();
        while(line!=null){
            String[] words = split(line);//line.split(",");
            for(int i=0; i<words.length; ++i){
                String formatWord = words[i].trim();
                if(!word2Id.containsKey(formatWord)){
                    id2Word.add(formatWord);
                    word2Id.put(formatWord, id2Word.size()-1);
                }
            }
            line = reader.readLine();
        }
        reader.close();
        size = id2Word.size();
    }
    static int getId(String word){
        if(!word2Id.containsKey(word)) return -1;
        else return word2Id.get(word);
    }
    static String getWord(int id){
        if(id>size-1) return null;
        return id2Word.get(id);
    }
    static String[] split(String str){
        List<String> strlst = new ArrayList<String>();
        boolean isQuote = false;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); ++i){
            if(str.charAt(i)=='\"'){
                if(i<str.length()-1 && str.charAt(i+1)=='\"'){
                    sb.append("\"");
                    ++i;
                } else {
                    isQuote = !isQuote;
                }
            } else if(str.charAt(i)==','){
                if(isQuote){
                    sb.append(str.charAt(i));
                } else {
                    strlst.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(str.charAt(i));
            }
        }
        String[] rst = new String[strlst.size()];
        for(int i=0; i<strlst.size(); ++i){
            rst[i] = strlst.get(i);
        }
        return rst;
    }
/*
    public static void main(String[] args){
        String str =  "\"BEST BUY STORES, L.P.\",Electronic Store - 001,Non-Delivery of Goods - N01,Advised to Sue - ATS,LISLE,IL";
        String[] rst = split(str);
        for(int i=0; i<rst.length; ++i){
            System.out.println(rst[i]);
        }
    }*/
}
