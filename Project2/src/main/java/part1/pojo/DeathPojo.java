package part1.pojo;

/**
 * Created by szeyiu on 3/21/15.
 */
public class DeathPojo{
    String place;
    String date;
    String cause;
    @Override
    public String toString(){
        return place+"\t"+date+"\t"+cause;
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