package pojo;

/**
 * The pojo class for the search result.
 * Created by szeyiu on 2/17/15.
 */
public class SearchResult {
    public String title;
    public String description;
    public String url;
    public boolean relevant;

    public int getTopK() {
        return topK;
    }

    public void setTopK(int topK) {
        this.topK = topK;
    }

    public int topK;
    public SearchResult(){
        relevant = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString(){
        return "[\ntitle: "+title + "\ndescription: "+description+"\nurl: "+url+ "\n]";
    }

    public boolean isRelevant() {
        return relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }
}
