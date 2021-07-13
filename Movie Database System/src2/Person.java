public class Person {
    protected String name;
    protected String surname;
    protected String country;
    protected String id;

    public Person(String id,String name,String surname,String country){
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.id = id;
    }

    public Person() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

