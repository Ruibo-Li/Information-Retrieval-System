package IRModel;
import pojo.*;

import java.util.*;
import java.io.*;
/**
 * The very naive method, refering to the book P182
 * Created by szeyiu on 2/17/15.
 */
public class Naive {
    public double beta;
    public double gamma;
    public double precision;
    public double titleFactor;
    public int iter;
    public Set<String> propSet;
    boolean antiProp;
    public Naive() throws Exception{
        iter = 0;
        beta = 0.75;
        gamma = 0.15;
        titleFactor = 3;
        precision = 0.9;
        antiProp = true;
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/StopWords.txt"));
        propSet = new HashSet<String>();
        try {
            String line = br.readLine();
            while (line != null) {
                propSet.add(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        /*
        String[] pArray = {
                "above",
                "after",
                "against",
                "along",
                "amid",
                "among",
                "around",
                "as",
                "at",
                "before",
                "behind",
                "below",
                "beside",
                "besides",
                "between",
                "beyond",
                "but",
                "by",
                "despite",
                "during",
                "for",
                "from",
                "in",
                "into",
                "like",
                "near",
                "of",
                "off",
                "on",
                "onto",
                "over",
                "past",
                "per",
                "plus",
                "round",
                "save",
                "since",
                "than",
                "through",
                "to",
                "toward",
                "towards",
                "under",
                "unlike",
                "until",
                "up",
                "upon",
                "via",
                "with",
                "he", "she", "it", "the", "these", "those", "this"
        };

        propSet = new HashSet<String>();
        for(String prop: pArray)    propSet.add(prop);
        */
    }

    public void setParam(double beta, double gamma){
        this.beta = beta;
        this.gamma = gamma;
    }
    public void setPrecision(double p){
        precision = p;
    }
    /**
     * tokenize the string and eliminate strings that are not English word.
     * @param str
     * @return
     */
    private List<String> tokenize(String str){
        String[] splitStr = str.split(" ");
        List<String> rst = new ArrayList<String>();
        for(String word: splitStr){
            if(word.matches(".*[a-zA-Z]+.*")){
                rst.add(word);
            }
        }
        return rst;
    }

    /**
     * get the doc vector from the str
     * @param str
     * @return
     */
    public Map<String, Double> getDocVector(String str){
        List<String> strlst = tokenize(str);
        Map<String, Double> dicWeight = new HashMap<String, Double>();
        for(String s: strlst){
            if(!dicWeight.containsKey(s)){
                dicWeight.put(s, 0.0);
            }
            dicWeight.put(s, dicWeight.get(s)+1);
        }
        return dicWeight;
    }

    /**
     * return v1+v2, which is a new HashMap
     * @param v1
     * @param v2
     * @return
     */
    public Map<String, Double> addVector(Map<String, Double> v1, Map<String, Double> v2){
        Map<String, Double> v = new HashMap<String, Double>();
        for(String v1word: v1.keySet()){
            v.put(v1word, v1.get(v1word));
        }
        for(String v2word: v2.keySet()){
            if(!v.containsKey(v2word)){
                v.put(v2word, 0.0);
            }
            v.put(v2word, v.get(v2word)+v2.get(v2word));
        }
        return v;
    }

    /**
     * return v+v0, which stores in v
     * @param v
     * @param v0
     * @return
     */
    public Map<String, Double> accumulateVector(Map<String, Double> v, Map<String, Double> v0){
        for(String v0word: v0.keySet()){
            if(!v.containsKey(v0word)){
                v.put(v0word, 0.0);
            }
            v.put(v0word, v.get(v0word)+v0.get(v0word));
        }
        return v;
    }

    /**
     * return v1-v2, which is a new HashMap
     * @param v1
     * @param v2
     * @return
     */
    public Map<String, Double> minorVector(Map<String, Double> v1, Map<String, Double> v2){
        return addVector(v1, scaleVector(v2, -1));
    }

    /**
     * return t*v0, which is a new HashMap
     * @param v0
     * @param t
     * @return
     */
    public Map<String, Double> scaleVector(Map<String, Double> v0, double t){
        Map<String, Double> v = new HashMap<String, Double>();
        for(String v0word: v0.keySet()){
            v.put(v0word, t*v0.get(v0word));
        }
        return v;
    }

    private List<String> top2Query(List<Map<String, Double>> relevantVectors, List<Map<String, Double>> inrelevantVectors, List<String> oldQueryList){
        Map<String, Double> relevantSum = new HashMap<String, Double>();
        double Dr = relevantVectors.size();
        for(Map<String, Double> reVector: relevantVectors){
            accumulateVector(relevantSum, reVector);
        }

        Map<String, Double> inrelevantSum = new HashMap<String, Double>();
        double Dnr = inrelevantVectors.size();
        for(Map<String, Double> inreVector: inrelevantVectors){
            accumulateVector(inrelevantSum, inreVector);
        }

        relevantSum = scaleVector(relevantSum, beta/Dr);
        inrelevantSum = scaleVector(inrelevantSum, gamma/Dnr);
        Map<String, Double> Q = minorVector(relevantSum, inrelevantSum);
        for(String q: oldQueryList){
            if(Q.containsKey(q)){
                Q.remove(q);
            }
        }
        String maxmaxQ = "";
        double maxmaxW = -100000000;
        String maxQ = "";
        double maxW = -100000000;
        for(String q: Q.keySet()){
            if(antiProp && propSet.contains(q)) continue;
            double w = Q.get(q);
            if(w>maxmaxW){
                maxW = maxmaxW;
                maxmaxW = w;
                maxQ = maxmaxQ;
                maxmaxQ = q;
            } else if(w>maxW){
                maxW = w;
                maxQ = q;
            }
        }
        List<String> rst = new ArrayList<String>();
        rst.add(maxmaxQ);
        rst.add(maxQ);
        return rst;
    }

    /**
     * the main function for this class, input query keywords, and search result with relevant info,
     * then the Naive model will update the query keywords for next iteration.
     * @param queryList
     * @param searchResults
     * @return
     */
    public List<String> updateQuery(List<String> queryList, List<SearchResult> searchResults){
        if(iter==0){
            for(String w: queryList)    antiProp = antiProp && !propSet.contains(w);
        }
        System.out.println("Naive Model is updating query, iter: "+iter+ ", ignore prop: "+antiProp);
        List<Map<String, Double>> relevantVectors = new ArrayList<Map<String, Double>>();
        List<Map<String, Double>> inrelevantVectors = new ArrayList<Map<String, Double>>();

        for(SearchResult searchResult: searchResults){
            if(searchResult.isRelevant()) {
                relevantVectors.add(
                        addVector(scaleVector(getDocVector(searchResult.getTitle()),titleFactor),
                                getDocVector(searchResult.getDescription()))
                );
            } else {
                inrelevantVectors.add(
                        addVector(scaleVector(getDocVector(searchResult.getTitle()), titleFactor),
                                getDocVector(searchResult.getDescription()))
                );
            }
        }

        double p = 1.0*relevantVectors.size()/(relevantVectors.size()+inrelevantVectors.size());
        if(p==0.0){
            System.out.println("No relevant result, Naive Model terminate");
            return null;
        }
        System.out.println("The result precision is "+p);
        if(p >= precision){
            System.out.println("Naive Model has reached the precision: "+precision+"\nFinish!");
            return null;
        }
        List<String> top2List =  top2Query(relevantVectors, inrelevantVectors, queryList);
        for(String w: top2List) queryList.add(w);
        iter++;
        return queryList;
    }
}
