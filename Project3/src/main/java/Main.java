import apriori.FrequentItems;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length!=3){
            throw new Error("Error: need at least 3 args: csvFile minSupp minConf");
        }
        String inputfile = args[0];
        String outputfile = "output.txt";
        double threshold = Double.valueOf(args[1]);
        double conf = Double.valueOf(args[2]);

        FrequentItems fi = new FrequentItems(inputfile);
        fi.setOutputPath(outputfile);
        if(threshold>=0 && threshold<=1) fi.setThreshold(threshold);
        if(conf>=0 && conf<=1) fi.setMinConf(conf);
        fi.findFrequentPairs().findFrequentAll();
        fi.findRules();
        fi.printfile();
        System.out.println("Program safely terminated! Thanks!");
    }
}
