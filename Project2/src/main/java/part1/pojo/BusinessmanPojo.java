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
    @Override
    public String toString(){
        return From + "\t"+To+"\t"+Organization+"\t"+Role+"\t"+Title;
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