package part1.pojo;

/**
 * Created by szeyiu on 3/21/15.
 */
public class DeathPojo{
    String place;
    String date;
    String cause;
    private int pW=27;
    private int dW=26;
    private int cW=26;

    @Override
    public String toString(){
        String nameString = format(place, pW-1);
        String positionString = format(date, dW-1);
        String fromToString = format(cause, cW-1);
        return "|"+nameString+"|"+positionString+"|"+fromToString+"|";
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


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}