public class Performer extends Artist{

    public Performer(String id,String name,String surname,String country){
        super(id,name,surname,country);
    }
    public Performer(String id){
        super(id);

    }
    @Override
    public boolean equals(Object obj) { //METHOD FOR USING CONTAINS
        if(obj instanceof Performer){
            Performer a = (Performer) obj;
            return a != null && this.id.equals(a.id);
        }
        return false;
    }
}
