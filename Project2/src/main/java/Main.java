import driver.Driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by szeyiu on 3/26/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        boolean cmdMode = true;
        boolean fileMode = false;
        boolean isPart1 = false;
        boolean isPart2 = false;
        String key = "", q = "", fileName = "";
        for(int i=0; i<args.length-1; i=i+2){
            if(args[i].equals("-key")){
                key = args[i+1];
            } else if(args[i].equals("-q")){
                q = args[i+1];
                cmdMode = false;
            } else if(args[i].equals("-f")){
                fileMode = true;
                fileName = args[i+1];
                cmdMode = false;
            } else if(args[i].equals("-t")){
                if(!args[i+1].equals("infobox") && !args[i+1].equals("question")){
                    System.out.println(args[i]+" "+args[i+1]+": invalid args, ignore!");
                    continue;
                }
                isPart1 = args[i+1].equals("infobox");
                isPart2 = args[i+1].equals("question");
                cmdMode = false;
            } else {
                System.out.println(args[i]+" "+args[i+1]+": invalid args, ignore!");
            }
        }

        if(key==null || key.equals("")){
            System.out.println("Please indicate the key!");
            return;
        }

        if(cmdMode){
            Date date = new Date();
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.print("[");
                System.out.print(date);
                System.out.print("] ");
                System.out.print(System.getProperty("user.name") + "@infoSYS> ");
                q = scanner.nextLine();
                q = q.trim();
                if(q.equals("")) continue;
                isPart1 = !q.toLowerCase().matches("who created .+");
                if(isPart1) Driver.part1(key, q);
                else Driver.part2(key, q);
            }
        }

        if(!fileMode){
            if(isPart1){
                Driver.part1(key,q);
            } else if(isPart2){
                Driver.part2(key,q);
            }
        } else {
            if(fileName==null||fileName.equals("")){
                System.out.println("Please indicate the file!");
                return;
            }
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            q = reader.readLine();
            while(q!=null) {
                if(q.equals("")){
                    q = reader.readLine();
                    continue;
                }
                System.out.println("\n\nQuery Question: "+q+"\n\n");
                if (isPart1) Driver.part1(key, q);
                else if(isPart2) Driver.part2(key, q);
                q = reader.readLine();
            }
        }

    }
}
