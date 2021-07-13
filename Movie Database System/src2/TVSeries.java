public class TVSeries extends Film{
    private String genre;
    private String writerId;
    private String startDate;
    private String endDate;
    private String seasons;
    private String episodes;

    public TVSeries(String filmType,String filmId, String filmTitle, String language, String directorId, String runtime, String country, String performerId, String genre, String writerId, String startDate, String endDate, String seasons, String episodes) {
        super(filmType,filmId, filmTitle, language, directorId, runtime, country, performerId);
        this.genre = genre;
        this.writerId = writerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSeasons() {
        return seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }
}
