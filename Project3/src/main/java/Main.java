import apriori.FrequentItems;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.print("Find Frequent Item Sets. Author: Siyao Li\n\n\nInstruction:\n\n" +
                "Arguments: inputfile, outputfile, [min_supp=0.7], [min_conf=0.7]\n");

        if(args.length<2){
            throw new Error("Error: need at least two args: inputfile, outputfile!");
        }
        String inputfile = args[0];
        String outputfile = args[1];
        double threshold = -1;
        double conf = -1;
        if(args.length>2) {
            threshold = Double.valueOf(args[2]);
            conf = Double.valueOf(args[3]);
        }
        FrequentItems fi = new FrequentItems(inputfile);
        fi.setOutputPath(outputfile);
        if(threshold>=0 && threshold<=1) fi.setThreshold(threshold);
        if(conf>=0 && conf<=1) fi.setMinConf(conf);
        fi.findFrequentPairs().findFrequentAll();
        fi.findRules();
        fi.printfile();
        System.out.print("Result has outputted to file. Do you want to display results on screen?\n" +
                "Y: Display\nN: No and quit for safe.\n");
        Scanner console = new Scanner(System.in);
        String in = console.nextLine();
        if(!in.equals("N")&&!in.equals("n"))  fi.print();
        System.out.println("Program safely terminated! Thanks!");
    }
}
