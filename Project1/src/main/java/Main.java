import service.*;
/**
 * Created by szeyiu on 2/7/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String content = Bing.getResult("tiger","OGwFXUbDNNw/U2hhi/vhjWdahU36dTuk5V2ZUis7+VU");
        System.out.println(content);
    }
}
