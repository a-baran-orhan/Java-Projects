
public class Rate {
    private String userId;
    private String filmId;
    private String ratingPoint;


    public Rate(String userId,String filmId,String ratingPoint){
        this.userId = userId;
        this.filmId = filmId;
        this.ratingPoint = ratingPoint;
    }

    @Override
    public boolean equals(Object obj) { //METHOD FOR USING CONTAINS
        if(obj instanceof Rate){
            Rate a = (Rate) obj;
            return a != null && this.userId.equals(a.userId) && this.filmId.equals(a.filmId);
        }
        return false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getRatingPoint() {
        return ratingPoint;
    }

    public void setRatingPoint(String ratingPoint) {
        this.ratingPoint = ratingPoint;
    }
}

