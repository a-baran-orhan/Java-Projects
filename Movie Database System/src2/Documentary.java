public class Documentary extends Film{
    private String releaseDate;

    public Documentary(String filmType,String filmId, String filmTitle, String language, String directorId, String runtime, String country, String performerId, String releaseDate) {
        super(filmType,filmId, filmTitle, language, directorId, runtime, country, performerId);
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
