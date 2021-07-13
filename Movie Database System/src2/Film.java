public class Film {
    private String filmType;
    private String filmId;
    private String filmTitle;
    private String language;
    private String directorId;
    private String runtime;
    private String country;
    private String performerId;

    public Film(String filmType,String filmId, String filmTitle, String language, String directorId, String runtime, String country, String performerId) {
        this.filmType = filmType;
        this.filmId = filmId;
        this.filmTitle = filmTitle;
        this.language = language;
        this.directorId = directorId;
        this.runtime= runtime;
        this.country = country;
        this.performerId = performerId;
    }
    public Film(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPerformerId() {
        return performerId;
    }

    public void setPerformerId(String performerId) {
        this.performerId = performerId;
    }
}
