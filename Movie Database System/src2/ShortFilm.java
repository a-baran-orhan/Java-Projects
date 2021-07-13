public class ShortFilm extends Film{
    private String genre;
    private String releaseDate;
    private String writerId;
    public ShortFilm(String filmType,String filmId, String filmTitle, String language, String directorId, String runtime, String country, String performerId,String genre,String releaseDate,String writerId){
        super(filmType,filmId,filmTitle,language,directorId,runtime,country,performerId);
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.writerId = writerId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }
}
