package postProcess;

import java.io.*;

/**
 * Created by szeyiu on 4/24/15.
 */
public class Filter {
    public static void main(String[] args) throws IOException {
        File fin = new File("out.txt");
        File fout = new File("out_filtered.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fin)));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fout)));
        String line = reader.readLine();
        while(line!=null){
            if(line.matches(".*=>.*\\[[a-zA-z]+.*\\].*") &&
                    (line.contains("Auto")||line.contains("Taxis")||line.contains("Commercial")||
                            line.contains("Bus")||line.contains("Truck")||line.contains("Other"))){
                writer.write(line+"\n");
            }
            line = reader.readLine();
        }
        writer.flush();
        reader.close();
        writer.close();
    }
}
