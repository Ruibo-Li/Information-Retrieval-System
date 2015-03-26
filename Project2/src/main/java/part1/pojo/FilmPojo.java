package part1.pojo;

/**
 * Created by szeyiu on 3/22/15.
 */
public class FilmPojo {
    String character="";
    String filmName="";

    int cW = 39;
    int fW = 40;
    @Override
    public String toString(){
        String nameString = format(filmName, fW-1);
        String chString = format(character, cW-1);

        return nameString+"|"+chString;
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
    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }
}
