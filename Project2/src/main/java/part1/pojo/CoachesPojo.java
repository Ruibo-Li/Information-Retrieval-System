package part1.pojo;

/**
 * Created by szeyiu on 3/21/15.
 */
public class CoachesPojo {
    String name="";
    String position="";
    String from="";
    String to="";

    //total width is 79 + 1
    int nameWidth = 27;
    int positionWidth = 26;
    int fromToWidth = 26;

    @Override
    public String toString(){
        String nameString = format(name, nameWidth-1);
        String positionString = format(position, positionWidth-1);
        String fromToString = format(from+"/"+to, fromToWidth-1);
        return nameString+"|"+positionString+"|"+fromToString;
    }

    private String format(String s, int width){
        StringBuilder builder = new StringBuilder(s);
        if(s.length()<=width){
            int dif = width - s.length();
            for(int i=0; i<dif; ++i){
                builder.append(" ");
            }
        } else{
            builder.delete(width-3, builder.length());
            builder.append("...");
        }
        return builder.toString();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
