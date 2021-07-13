public class FeatureFilm extends Film{
    private String releaseDate;
    private String budget;
    private String writerId;
    private String genre;
    public FeatureFilm(String filmType,String filmId, String filmTitle, String language, String directorId, String runtime, String country, String performerId,String genre,String releaseDate,String writerId,String budget){
        super(filmType,filmId,filmTitle,language,directorId,runtime,country,performerId);
        this.genre = genre;
        this.releaseDate=releaseDate;
        this.writerId = writerId;
        this.budget = budget;

    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

