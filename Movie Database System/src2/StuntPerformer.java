public class StuntPerformer extends Performer {
    private String realActorId;
    private String height;
    public StuntPerformer(String id,String name,String surname,String country,String height,String realActorId){
        super(id,name,surname,country);
        this.realActorId = realActorId;
        this.height= height;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRealActorId() {
        return realActorId;
    }

    public void setRealActorId(String realActorId) {
        this.realActorId = realActorId;
    }
}
