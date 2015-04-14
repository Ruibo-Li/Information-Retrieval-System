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
            String[] words = line.split(",");
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
}
