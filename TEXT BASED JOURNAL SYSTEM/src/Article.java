
public class Article {
    private int paperid, publisherYear;
    private String name, publisherName;

    public Article(int paperid, String name, String publisherName, int publisherYear){
        this.paperid = paperid;
        this.name = name;
        this.publisherName = publisherName;
        this.publisherYear = publisherYear;
    }

    public int getPaperid() {
        return paperid;
    }

    public void setPaperid(int paperid) {
        this.paperid = paperid;
    }

    public int getPublisherYear() {
        return publisherYear;
    }

    public void setPublisherYear(int publisherYear) {
        this.publisherYear = publisherYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    public String getAuthourBook(int num){
        int a = getPaperid();
        String s;
        if (num == a){
            s = "+"+getPaperid()+"\t"+getName()+"\t"+getPublisherName()+"\t"+getPublisherYear();
        }else{
            s = "not";
        }
        return s;
    }
    public int getAuthourBook2(int num){
        int a = getPaperid()/10000;
        int s = 0;
        if (num == a){
            s = getPaperid();
        }
        return s;
    }

}
