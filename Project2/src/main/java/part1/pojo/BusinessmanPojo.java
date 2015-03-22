package part1.pojo;

/**
 * Created by szeyiu on 3/21/15.
 */
public class BusinessmanPojo{
    public String From;
    public String To;
    public String Organization;
    public String Role;
    public String Title;

    private int orgW = 20;
    private int rolW = 20;
    private int titW = 20;
    private int fromToW = 19;

    @Override
    public String toString(){
        String nameString = format(Organization, orgW-1);
        String positionString = format(Role, rolW-1);
        String numberString = format(Title, titW-1);
        String fromToString = format(From+"/"+To, fromToW-1);
        return "|"+nameString+"|"+positionString+"|"+numberString+"|"+fromToString+"|";
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

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        Organization = organization;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}