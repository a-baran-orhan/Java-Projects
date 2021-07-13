import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Artist> artistArray = new ArrayList<>();
    static ArrayList<FeatureFilm> featureArray = new ArrayList<>();
    static ArrayList<ShortFilm> shortArray = new ArrayList<>();
    static ArrayList<Documentary> docArray = new ArrayList<>();
    static ArrayList<TVSeries> seriesArray = new ArrayList<>();
    static ArrayList<User> userArray = new ArrayList<>();
    static ArrayList<Actor> actorArray = new ArrayList<>();
    static ArrayList<ChildActor> childArray = new ArrayList<>();
    static ArrayList<Director> directorArray = new ArrayList<>();
    static ArrayList<Writer> writerArray = new ArrayList<>();
    static ArrayList<StuntPerformer> stuntArray = new ArrayList<>();
    static ArrayList<String> writerArray2 = new ArrayList<>();
    static ArrayList<Performer> performerArray = new ArrayList<>();
    static ArrayList<String> performerArray2 = new ArrayList<>();
    static ArrayList<String> directorArray2 = new ArrayList<>();
    static ArrayList<Film> filmsArray = new ArrayList<>();
    static ArrayList<String> sameCountryArray = new ArrayList<>();
    static ArrayList<String> sameCountryDirectorArray = new ArrayList<>();
    static ArrayList<String> sameCountryWriterArray = new ArrayList<>();
    static ArrayList<String> sameCountryActorArray = new ArrayList<>();
    static ArrayList<String> sameCountryChildActorArray = new ArrayList<>();
    static ArrayList<String> sameCountryStuntArray = new ArrayList<>();
    static ArrayList<Rate> rateArray = new ArrayList<>();
    static ArrayList<Performer> performerArray3 = new ArrayList<>();
    static ArrayList<String> directorIdArray = new ArrayList<>();
    static ArrayList<String> performerIdArray = new ArrayList<>();
    static ArrayList<String> writerIdArray = new ArrayList<>();


    public static void main(String[] args){

        try {

            String fileName = args[3];           //OUTPUT FILE
            final boolean append = false, autoflush = true;
            PrintStream printStream = new PrintStream(new FileOutputStream(fileName, append),  autoflush);

            System.setOut(printStream);         //USED FOR PRINTING SOUTS TO OUTPUT.TXT



            String fileName2 = args[1];     //FILMS FILE
            File filmsFile = new File(fileName2);
            Scanner films = new Scanner(filmsFile);
            while (films.hasNextLine()) {       //GETTING ALL THE LINES AND STORING THEM IN THEIR ARRAY LIKE(featureArray) AND FILM ARRAY
                String i = films.nextLine();
                String[] arri = i.split("\\s+");

                if (arri[0].equalsIgnoreCase("FeatureFilm:")) {
                    FeatureFilm ff = new FeatureFilm(arri[0], arri[1], arri[2], arri[3], arri[4], arri[5], arri[6], arri[7], arri[8], arri[9], arri[10], arri[11]);     //CREATING FEATUREFILM OBJECTS
                    featureArray.add(ff);
                    filmsArray.add(ff);
                }else if (arri[0].equalsIgnoreCase("ShortFilm:")) {
                    ShortFilm sf = new ShortFilm(arri[0], arri[1], arri[2], arri[3], arri[4], arri[5], arri[6], arri[7], arri[8], arri[9], arri[10]);
                    try {
                        if (Integer.parseInt(arri[5]) > 40) {
                            throw new IllegalArgumentException("Short film is greater than 40 min");
                        } else {
                            shortArray.add(sf);
                            filmsArray.add(sf);
                        }
                    }catch (IllegalArgumentException e){
                        System.err.println(e.getLocalizedMessage());
                    }
                }else if (arri[0].equalsIgnoreCase("Documentary:")) {
                    Documentary dc = new Documentary(arri[0], arri[1], arri[2], arri[3], arri[4], arri[5], arri[6], arri[7], arri[8]);
                    docArray.add(dc);
                    filmsArray.add(dc);
                }else if (arri[0].equalsIgnoreCase("TVSeries:")) {
                    TVSeries tv = new TVSeries(arri[0], arri[1], arri[2], arri[3], arri[4], arri[5], arri[6], arri[7], arri[8], arri[9], arri[10], arri[11], arri[12], arri[13]);
                    seriesArray.add(tv);
                    filmsArray.add(tv);
                }
            }

            String fileName3 =args[0];      //PEOPLE FILE
            File peopleFile = new File(fileName3);
            Scanner people = new Scanner(peopleFile);
            while (people.hasNextLine()) {
                String j = people.nextLine();
                String[] arrj = j.split("\\s+");

                if (arrj[0].equalsIgnoreCase("Actor:")) { //CREATED ACTOR AND PERFORMER OBJECT AND ADDED THEM TO ARRAYS FOR USE LATER
                    Actor ac = new Actor(arrj[1], arrj[2], arrj[3], arrj[4], arrj[5]);
                    Performer acc  =new Performer(arrj[1]);
                    actorArray.add(ac);
                    performerArray.add(ac);
                    performerArray3.add(acc);
                    artistArray.add(ac);
                    performerIdArray.add(arrj[1]);
                } else if (arrj[0].equalsIgnoreCase("ChildActor:")) {
                    ChildActor ca = new ChildActor(arrj[1], arrj[2], arrj[3], arrj[4], arrj[5]);
                    Performer caa  =new Performer(arrj[1]);
                    childArray.add(ca);
                    performerArray.add(ca);
                    performerArray3.add(caa);
                    artistArray.add(ca);
                    performerIdArray.add(arrj[1]);
                } else if (arrj[0].equalsIgnoreCase("Director:")) {
                    Director dr = new Director(arrj[1], arrj[2], arrj[3], arrj[4], arrj[5]);
                    directorArray.add(dr);
                    artistArray.add(dr);
                    directorIdArray.add(arrj[1]);
                } else if (arrj[0].equalsIgnoreCase("Writer:")) {
                    Writer wr = new Writer(arrj[1], arrj[2], arrj[3], arrj[4], arrj[5]);
                    writerArray.add(wr);
                    artistArray.add(wr);
                    writerIdArray.add(arrj[1]);
                } else if (arrj[0].equalsIgnoreCase("StuntPerformer:")) {
                    StuntPerformer sp = new StuntPerformer(arrj[1], arrj[2], arrj[3], arrj[4], arrj[5], arrj[6]);
                    Performer pf  =new Performer(arrj[1]);
                    stuntArray.add(sp);
                    performerArray.add(sp);
                    performerArray3.add(pf);
                    artistArray.add(sp);
                    performerIdArray.add(arrj[1]);
                } else if (arrj[0].equalsIgnoreCase("User:")) {
                    User us = new User(arrj[1], arrj[2], arrj[3], arrj[4]);
                    userArray.add(us);
                }
            }


            String filename = args[2];      //COMMAND FILE
            File textFile = new File(filename);
            Scanner sc = new Scanner(textFile);
            while (sc.hasNextLine()) {
                String value = sc.nextLine();
                String[] arr = value.split("\\s+");

                if (arr[0].equalsIgnoreCase("ADD")) {       //STATEMENT FOR ADD FEATURE FILMS COMMAND
                    System.out.println("ADD "+"\t"+"FEATUREFILM"+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]+"\t"+arr[5]+"\t"+arr[6]+"\t"+arr[7]+"\t"+arr[8]+"\t"+arr[9]+"\t"+arr[10]+"\t"+arr[11]+"\t"+arr[12]+"\n");   //PRINTING EACH OBTAINED COMMAND

                    if(filmChecker(arr[2])) { //CHECKING THE GIVEN FILM ID IS IN GIVEN FILMS OR NOT
                        System.out.println("Command Failed");
                        System.out.println("User ID: " + arr[2]);
                        System.out.println("Film ID:" + arr[3] + "\n");
                        System.out.println(("-------------------------------------------------------"));

                    }else if(directorChecker(arr[5]) || performerChecker(arr[8]) || writerChecker(arr[11])){        //CHECKING THE GIVEN IDS ARE CORRECT
                        System.out.println("Command Failed");
                        System.out.println("User ID: " + arr[2]);
                        System.out.println("Film ID:" + arr[3] + "\n");
                        System.out.println(("-------------------------------------------------------"));

                    } else{
                        add(arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11], arr[12]);     //ADD METHOD FOR ADDING THE GIVEN FEATUREFILM
                    }


                } else if (arr[0].equalsIgnoreCase("VIEWFILM")) {       //STATEMENT FOR VIEW FILM COMMAND
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    if(filmChecker2(arr[1])){
                        System.out.println("Command Failed");
                        System.out.println("Film ID: "+arr[1]);
                        System.out.println(("-------------------------------------------------------"));
                    }else{
                        viewFilm(arr[1]);
                    }

                } else if (arr[0].equalsIgnoreCase("RATE")) {  //STATEMENT FOR RATE THE FILMS COMMAND
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");

                    Rate rt = new Rate(arr[1], arr[2], arr[3]);

                    try {
                        if (rateArray.contains(rt)) {//FOR THE SAME USER AND SAME FILM
                            System.out.println("This film was earlier rated" + "\n");
                            System.out.println(("-------------------------------------------------------"));

                        } else if (userChecker(arr[1])) {
                            System.out.println("Command Failed");
                            System.out.println("User ID: " + arr[1]);
                            System.out.println("Film ID: " + arr[2]);
                            System.out.println();
                            System.out.println(("-------------------------------------------------------"));

                        } else if (filmChecker2(arr[2])) {
                            System.out.println("Command Failed");
                            System.out.println("User ID: " + arr[1]);
                            System.out.println("Film ID: " + arr[2]);
                            System.out.println();
                            System.out.println(("-------------------------------------------------------"));

                        } else if (Integer.parseInt(arr[3]) > 10 || Integer.parseInt(arr[3]) < 0) {
                            System.out.println("Command Failed");
                            System.out.println("User ID: " + arr[1]);
                            System.out.println("Film ID: " + arr[2]);
                            System.out.println();
                            System.out.println(("-------------------------------------------------------"));
                            throw new IllegalArgumentException("Rating point is not valid.");

                        } else {
                            rateArray.add(rt);
                            rate(arr[2]);
                        }
                    }catch (IllegalArgumentException e){
                        System.err.println(e.getLocalizedMessage());
                    }

                } else if (arr[1].equalsIgnoreCase("FILM")) {       //STATEMENT FOR LIST FILM SERIES
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    listFilmSeries();

                } else if (arr[0].equalsIgnoreCase("EDIT")) {       //STATEMENT FOR USER EDIT THE RATE HE/SHE DID BEFORE
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    if(userChecker(arr[2])){
                        System.out.println("Command Failed");
                        System.out.println("User ID: "+ arr[2]);
                        System.out.println("Film ID: "+arr[3]+"\n");
                        System.out.println(("-------------------------------------------------------"));

                    }else if(filmChecker2(arr[3])){
                        System.out.println("Command Failed");
                        System.out.println("User ID: "+ arr[2]);
                        System.out.println("Film ID: "+arr[3]+"\n");
                        System.out.println(("-------------------------------------------------------"));

                    }else if(!(rateArray.contains(new Rate(arr[2],arr[3],arr[4])))){ //CHECKING THE GIVEN USER WITH THE USERID AND FILMID ARE STORED BEFORE
                        System.out.println("Command Failed");
                        System.out.println("User ID: "+ arr[2]);
                        System.out.println("Film ID: "+arr[3]+"\n");
                        System.out.println(("-------------------------------------------------------"));

                    }else if(editChecker(arr[2],arr[3])){ //CHECK FOR THE USER AND FILM ID IS VALID
                        System.out.println("Command Failed");
                        System.out.println("User ID: "+ arr[2]);
                        System.out.println("Film ID: "+arr[3]+"\n");
                        System.out.println(("-------------------------------------------------------"));

                    }
                    else{
                        editRates(arr[2], arr[3], arr[4]);

                    }


                } else if (arr[0].equalsIgnoreCase("REMOVE")) {     //STATEMENT FOR USER TO REMOVE THE HER/HIS RATED FILM BEFORE
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    if(userChecker(arr[2])){
                        System.out.println("Command Failed");
                        System.out.println("User ID: "+ arr[2]);
                        System.out.println("Film ID: "+arr[3]+"\n");
                        System.out.println(("-------------------------------------------------------"));
                    }else if(filmChecker2(arr[3])){
                        System.out.println("Command Failed");
                        System.out.println("User ID: "+ arr[2]);
                        System.out.println("Film ID: "+arr[3]+"\n");
                        System.out.println(("-------------------------------------------------------"));
                    }else if (ratingScoreFinder(arr[2],arr[3])){        //CHECKING USER RATING SCORE BEFORE OR NOT
                        System.out.println("Command Failed");
                        System.out.println("User ID: "+ arr[2]);
                        System.out.println("Film ID: "+arr[3]+"\n");
                        System.out.println(("-------------------------------------------------------"));
                    }
                    else{
                        removeRates(arr[2], arr[3]);
                    }

                } else if (arr[3].equalsIgnoreCase("COUNTRY")) {        //STATEMENT FOR LIST FILMS BY COUNTRY
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    listFilmsByCountry(arr[4]); //arr[4] is country name

                } else if (arr[3].equalsIgnoreCase("RATE")) {       //STATEMENT FOR LIST *FILMS* BY RATE DEGREE
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    listByDegree();
                    System.out.println(("-------------------------------------------------------"));

                } else if (arr[2].equalsIgnoreCase("BEFORE")) {     //STATEMENT FOR LIST FILMS BEFORE THE YEAR
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    listBeforeYear(arr[3]);

                } else if (arr[2].equalsIgnoreCase("AFTER")) {      //STATEMENT FOR LIST FILMS AFTER THE YEAR
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    listAfterYear(arr[3]);

                } else if (arr[1].equalsIgnoreCase("ARTISTS")) {        //STATEMENTS FOR LIST ARTIST BY COUNTRY
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    listArtistsByCountry(arr[3]);//arr[4] is country name

                } else if (arr[1].equalsIgnoreCase("USER")) {       //STATEMENT FOR LIST THE RATES BY USER SO FAR
                    System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", "").replace(" ", "\t") + "\n");
                    if(userChecker(arr[2])){
                        System.out.println("Command Failed");
                        System.out.println("User ID: "+ arr[2]);
                        System.out.println();
                        System.out.println(("-------------------------------------------------------"));
                    }else{
                        listRates(arr[2]);
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("Error during reading writing"); //EXCEPTION FOR WRONG GIVEN FILES
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean directorChecker(String directorId) { //CHECKER FOR GIVEN DIRECTOR ID IS IN DIRECTORSID OR NOT
        ArrayList<String> list = new ArrayList<>();
        String[] a = directorId.split(",");
        Collections.addAll(list, a);
        return !directorIdArray.containsAll(list);
    }

    private static boolean performerChecker(String performerId) {//CHECKER FOR GIVEN PERFORMER ID IS IN PERFORMERID OR NOT
        ArrayList<String> list = new ArrayList<>();
        String[] a = performerId.split(",");
        Collections.addAll(list, a);
        return !performerIdArray.containsAll(list);
    }

    private static boolean writerChecker(String writerId) {//CHECKER FOR GIVEN WRITER ID IS IN WRITERID OR NOT
        ArrayList<String> list = new ArrayList<>();
        String[] a = writerId.split(",");
        Collections.addAll(list, a);
        return !writerIdArray.containsAll(list);
    }

    private static boolean editChecker(String userId, String filmId) { //CHECK FOR EDIT RATE COMMAND
        for (Rate rate : rateArray) {
            if (rate.getUserId().equals(userId) && rate.getFilmId().equals(filmId)) {
                return false;
            }
        }
        return true;
    }

    private static boolean filmChecker(String givenFilmId) { //CHECK FOR GIVEN FILM ID INSIDE THE FILMS OR NOT
        for (Film film : filmsArray) {
            if (film.getFilmId().equals(givenFilmId)) {
                return true;
            }
        }
        return false;
    }

    private static boolean filmChecker2(String givenFilmIdd) { //CHECK FOR GIVEN FILM ID INSIDE THE FILMS OR NOT WITH DIFFERENT USAGE IN THE STATEMENTS
        for (Film film : filmsArray) {
            if (film.getFilmId().equals(givenFilmIdd)) {
                return false;
            }
        }
        return true;
    }

    public static boolean ratingScoreFinder(String userId,String filmId){ //CHECK FOR USER HAS RATING FOR FILM BEFORE
        for (Rate rate : rateArray) {
            if ((rate.getUserId().equals(userId)) && (rate.getFilmId().equals(filmId))) {
                return false;
            }
        }
        return true;
    }

    private static boolean userChecker(String givenId) { //CHECK FOR GIVEN USER ID INSIDE THE USERS OR NOT
        for (User user : userArray) {
            if (user.getId().equals(givenId)) {
                return false;
            }
        }
        return true;
    }

    public static void viewFilm(String viewFilmID) {


        for (FeatureFilm featureFilm : featureArray) {//PRINTING THE FEATURE FILM IF THE viewFilmId equals featureID
            if (featureFilm.getFilmId().equals(viewFilmID)) {
                String[] date = featureFilm.getReleaseDate().split("\\."); //to get the year at the end *data[2]*
                System.out.println(featureFilm.getFilmTitle() + " " + "(" + date[2] + ")");
                System.out.println(featureFilm.getGenre());
                //-------------PRINTING WRITER,DIRECTOR AND STARS------------------
                String[] writers = featureFilm.getWriterId().split(",");
                String[] stars = featureFilm.getPerformerId().split(",");
                String[] directors = featureFilm.getDirectorId().split(",");

                for (String j : writers) {
                    for (Writer writer : writerArray) {
                        if (j.equals(writer.getId())) {
                            writerArray2.add(writer.getName() + " " + writer.getSurname());
                        }
                    }
                }
                String writersString = writerArray2.toString();
                System.out.println("Writers: " + writersString.replace("[", "").replace("]", ""));
                writerArray2.removeAll(writerArray2);
                for (String j : directors) {
                    for (Director director : directorArray) {
                        if (j.equals(director.getId())) {
                            directorArray2.add(director.getName() + " " + director.getSurname());
                        }
                    }
                }
                String directorString = directorArray2.toString();
                System.out.println("Directors: " + directorString.replace("[", "").replace("]", ""));
                directorArray2.removeAll(directorArray2);
                for (String j : stars) {
                    for (Performer performer : performerArray) {
                        if (j.equals(performer.getId())) {
                            performerArray2.add(performer.getName() + " " + performer.getSurname());
                        }
                    }
                }
                String starString = performerArray2.toString();
                System.out.println("Stars: " + starString.replace("[", "").replace("]", ""));
                performerArray2.removeAll(performerArray2);
                //-------------PRINTING WRITER,DIRECTOR AND STARS------------------
                rateAverage(viewFilmID);

            }
        }
        for (Documentary documentary : docArray) {
            if (documentary.getFilmId().equals(viewFilmID)) {
                String[] date = documentary.getReleaseDate().split("\\.");//to get the year at the end *data[2]*
                System.out.println(documentary.getFilmTitle() + " " + "(" + date[2] + ")" + "\n");
                //-------------PRINTING DIRECTOR AND STARS------------------
                String[] stars = documentary.getPerformerId().split(",");
                String[] directors = documentary.getDirectorId().split(",");
                for (String j : directors) {
                    for (Director director : directorArray) {
                        if (j.equals(director.getId())) {
                            directorArray2.add(director.getName() + " " + director.getSurname());
                        }
                    }

                }
                String directorString = directorArray2.toString();
                System.out.println("Directors: " + directorString.replace("[", "").replace("]", ""));
                directorArray2.removeAll(directorArray2);
                for (String j : stars) {
                    for (Performer performer : performerArray) {
                        if (j.equals(performer.getId())) {
                            performerArray2.add(performer.getName() + " " + performer.getSurname());
                        }
                    }
                }
                String starString = performerArray2.toString();
                System.out.println("Stars: " + starString.replace("[", "").replace("]", ""));
                performerArray2.removeAll(performerArray2);
                //-------------PRINTING WRITER,DIRECTOR AND STARS------------------
                rateAverage(viewFilmID);
            }

        }
        for (ShortFilm shortFilm : shortArray) {
            if (shortFilm.getFilmId().equals(viewFilmID)) {
                String[] date = shortFilm.getReleaseDate().split("\\.");//to get the year at the end *data[2]*
                System.out.println(shortFilm.getFilmTitle() + " " + "(" + date[2] + ")");
                System.out.println(shortFilm.getGenre());
                //-------------PRINTING WRITER,DIRECTOR AND STARS------------------
                String[] writers = shortFilm.getWriterId().split(",");
                String[] stars = shortFilm.getPerformerId().split(",");
                String[] directors = shortFilm.getDirectorId().split(",");
                for (String j : writers) {
                    for (Writer writer : writerArray) {
                        if (j.equals(writer.getId())) {
                            writerArray2.add(writer.getName() + " " + writer.getSurname());
                        }
                    }
                }
                String writersString = writerArray2.toString();
                System.out.println("Writers: " + writersString.replace("[", "").replace("]", ""));
                writerArray2.removeAll(writerArray2);
                for (String j : directors) {
                    for (Director director : directorArray) {
                        if (j.equals(director.getId())) {
                            directorArray2.add(director.getName() + " " + director.getSurname());
                        }
                    }

                }
                String directorString = directorArray2.toString();
                System.out.println("Directors: " + directorString.replace("[", "").replace("]", ""));
                directorArray2.removeAll(directorArray2);
                for (String j : stars) {
                    for (Performer performer : performerArray) {
                        if (j.equals(performer.getId())) {
                            performerArray2.add(performer.getName() + " " + performer.getSurname());
                        }
                    }
                }
                String starString = performerArray2.toString();
                System.out.println("Stars: " + starString.replace("[", "").replace("]", ""));
                performerArray2.removeAll(performerArray2);
                //-------------PRINTING WRITER,DIRECTOR AND STARS------------------
                rateAverage(viewFilmID);

            }

        }
        for (TVSeries tvSeries : seriesArray) {
            if (tvSeries.getFilmId().equals(viewFilmID)) {
                String[] startDate = tvSeries.getStartDate().split("\\.");
                String[] endDate = tvSeries.getEndDate().split("\\.");
                System.out.println(tvSeries.getFilmTitle() + " " + "(" + startDate[2] + "-" + endDate[2] + ")");
                System.out.println(tvSeries.getSeasons() + " seasons" + ", " + tvSeries.getEpisodes() + " episodes");
                System.out.println(tvSeries.getGenre());
                //-------------PRINTING WRITER,DIRECTOR AND STARS------------------
                String[] writers = tvSeries.getWriterId().split(",");
                String[] stars = tvSeries.getPerformerId().split(",");
                String[] directors = tvSeries.getDirectorId().split(",");
                for (String j : writers) {
                    for (Writer writer : writerArray) {
                        if (j.equals(writer.getId())) {
                            writerArray2.add(writer.getName() + " " + writer.getSurname());
                        }
                    }
                }
                String writersString = writerArray2.toString();
                System.out.println("Writers: " + writersString.replace("[", "").replace("]", ""));
                writerArray2.removeAll(writerArray2);
                for (String j : directors) {
                    for (Director director : directorArray) {
                        if (j.equals(director.getId())) {
                            directorArray2.add(director.getName() + " " + director.getSurname());
                        }
                    }

                }
                String directorString = directorArray2.toString();
                System.out.println("Directors: " + directorString.replace("[", "").replace("]", ""));
                directorArray2.removeAll(directorArray2);
                for (String j : stars) {
                    for (Performer performer : performerArray) {
                        if (j.equals(performer.getId())) {
                            performerArray2.add(performer.getName() + " " + performer.getSurname());
                        }
                    }
                }
                String starString = performerArray2.toString();
                System.out.println("Stars: " + starString.replace("[", "").replace("]", ""));
                performerArray2.removeAll(performerArray2);
                //-------------PRINTING WRITER,DIRECTOR AND STARS------------------
                rateAverage(viewFilmID);
            }

        }
        System.out.println(("-------------------------------------------------------"));
    }

    public static void add(String filmType, String filmId, String filmTitle, String language, String directorId, String runtime, String country, String performerId, String genre, String releaseDate, String writerId, String budget) {

        FeatureFilm ff = new FeatureFilm("FeatureFilm", filmId, filmTitle, language, directorId, runtime, country, performerId, genre, releaseDate, writerId, budget);
        featureArray.add(ff);
        filmsArray.add(ff);
        int a = featureArray.size();
        a -= 1;
        System.out.println("FeatureFilm added successfully");
        System.out.println("Film ID: " + featureArray.get(a).getFilmId());
        System.out.println("Film title: " + featureArray.get(a).getFilmTitle() + "\n");
        //System.out.println(featureArray.get(a).getReleaseDate());

        System.out.println(("-------------------------------------------------------"));

    }

    public static void listFilmSeries() {
        if(Integer.parseInt(String.valueOf(seriesArray.size()))==0){
            System.out.println("No result");
            System.out.println();
        }else{
            for (TVSeries tvSeries : seriesArray) {
                String[] startDate = tvSeries.getStartDate().split("\\.");
                String[] endDate = tvSeries.getEndDate().split("\\.");
                System.out.println(tvSeries.getFilmTitle() + " " + "(" + startDate[2] + "-" + endDate[2] + ")");
                System.out.println(tvSeries.getSeasons() + " seasons" + " and " + tvSeries.getEpisodes() + " episodes" + "\n");
            }
        }
        System.out.println(("-------------------------------------------------------"));
    }

    public static void rate(String filmId) { //FOR PRINTING THE SUCCESFUL RATE COMMAND

        System.out.println("Film rated successfully");
        for (Film film : filmsArray) {
            if (filmId.equalsIgnoreCase(film.getFilmId())) {
                System.out.println("Film type: " + film.getFilmType().replace(":", " "));
                System.out.println("Film title: " + film.getFilmTitle() + "\n");
            }
        }


        System.out.println(("-------------------------------------------------------"));
    }

    public static void rateAverage(String viewFilmID) {//Printing average ratings and how many users
        double sum = 0;
        ArrayList<String> users = new ArrayList<>();
        for (Rate rate : rateArray) {
            if (rate.getFilmId().equalsIgnoreCase(viewFilmID)) {
                double a = Integer.parseInt(rate.getRatingPoint());
                sum = sum + a;
                users.add(rate.getUserId());
            }
        }
        int howMany = Integer.parseInt(String.valueOf(users.size()));
        double average = sum/howMany;
        String averageString = Double.toString(average);
        String[] avv = averageString.split("\\.");


        if (howMany > 0 ) {
            if(avv[1].equals("0")){
                System.out.println("Ratings: " + averageString.replace("0","").replace(".","")+ "/10" + " from " + howMany + " users");
                System.out.println();
            }else{
                System.out.println("Ratings: " + averageString.replace(".",",")+ "/10" + " from " + howMany + " users");
                System.out.println();
            }
        } else {
            System.out.println("Awaiting for votes\n");
        }
    }

    public static String rateAverage2 ( String filmId){//Printing average ratings and how many users dıfferent usage at dıfferent locatıon
        String b;
        double sum = 0;
        ArrayList<String> users = new ArrayList<>();
        for (Rate rate : rateArray) {
            if (rate.getFilmId().equalsIgnoreCase(filmId)) {
                double a = Integer.parseInt(rate.getRatingPoint());
                sum = sum + a;
                users.add(rate.getUserId());
            }
        }
        int howMany = Integer.parseInt(String.valueOf(users.size()));
        double average = sum/howMany;
        String averageString = String.format("%,.1f",average);
        String[] avv = averageString.split("\\.");


        if (howMany > 0 ) {
            if(avv[1].equals("0")){
               return  ("Ratings: " + averageString.replace("0","").replace(".","")+ "/10" + " from " + howMany + " users");

            }else{
                b = "Ratings: " + averageString.replace(".",",")+ "/10" + " from " + howMany + " users";
            }
        }else {
            b = "Ratings: 0/10 from 0 users";
        }
        return b;
    }

    public static void listRates(String userId) {

        for (Rate rate : rateArray) {
            if (userId.equals(rate.getUserId())) {
                System.out.println(filmIdtofilmTitle(rate.getFilmId()) + ": " + rate.getRatingPoint());

            }
        }
        System.out.println();
        System.out.println(("-------------------------------------------------------"));
    }

    public static void listFilmsByCountry(String countryName) {
        //String[] sameCountry = null;


        for (Film film : filmsArray) {
            if (countryName.equalsIgnoreCase(film.getCountry())) {
                sameCountryArray.add(film.getFilmTitle() + "\n" + film.getRuntime() + " min" + "\n" + "Language: " + film.getLanguage() + "\n");
            }
        }
        int z = sameCountryArray.size();
        if(z==0){
            System.out.println("No result");
            System.out.println();
        }else{
            for (String s : sameCountryArray) {
                System.out.println("Film title: " + s.replace("[", "").replace("]", "").replace(",", ""));
            }
            sameCountryArray.removeAll(sameCountryArray);
        }
        System.out.println(("-------------------------------------------------------"));
    }

    public static void listArtistsByCountry(String countryName) {
        System.out.println("Directors:");
        for (Director director : directorArray) {
            if (countryName.equalsIgnoreCase(director.getCountry())) {
                sameCountryDirectorArray.add(director.getName() + " " + director.getSurname() + " " + director.getAgent());
            }
        }
        int b = sameCountryDirectorArray.size();
        if(b==0){
            System.out.println("No result");
        }else{
            for (String s : sameCountryDirectorArray) {
                System.out.println(s.replace("[", "").replace("]", "").replace(",", ""));

            }
            sameCountryDirectorArray.removeAll(sameCountryDirectorArray);//removing ex directors froma array
        }
        System.out.println(" ");
        System.out.println("Writers:");
        for (Writer writer : writerArray) {
            if (countryName.equalsIgnoreCase(writer.getCountry())) {
                sameCountryWriterArray.add(writer.getName() + " " + writer.getSurname() + " " + writer.getStyle());
            }
        }
        int a = sameCountryWriterArray.size();
        if(a==0){
            System.out.println("No result");
        }else{
            for (String s : sameCountryWriterArray) {
                System.out.println(s.replace("[", "").replace("]", "").replace(",", ""));
            }
            sameCountryWriterArray.removeAll(sameCountryWriterArray);
        }
        System.out.println(" ");
        System.out.println("Actors:");
        for (Actor actor : actorArray) {
            if (countryName.equalsIgnoreCase(actor.getCountry())) {
                sameCountryActorArray.add(actor.getName() + " " + actor.getSurname() + " " + actor.getHeight() + " cm");
            }
        }
        int c = sameCountryActorArray.size();
        if(c==0){
            System.out.println("No result");
        }else{
            for (String s : sameCountryActorArray) {
                System.out.println(s.replace("[", "").replace("]", "").replace(",", ""));
            }
            sameCountryActorArray.removeAll(sameCountryActorArray);
        }
        System.out.println(" ");
        System.out.println("ChildActors:");
        for (ChildActor childActor : childArray) {
            if (countryName.equalsIgnoreCase(childActor.getCountry())) {
                sameCountryChildActorArray.add(childActor.getName() + " " + childActor.getSurname() + " " + childActor.getAge());
            }
        }
        int d = sameCountryChildActorArray.size();
        if(d==0){
            System.out.println("No result");
        }else{
            for (String s : sameCountryChildActorArray) {
                System.out.println(s.replace("[", "").replace("]", "").replace(",", ""));
            }
            sameCountryChildActorArray.removeAll(sameCountryChildActorArray);
        }
        System.out.println(" ");
        System.out.println("StuntPerformers:");
        for (StuntPerformer stuntPerformer : stuntArray) {
            if (countryName.equalsIgnoreCase(stuntPerformer.getCountry())) {
                sameCountryStuntArray.add(stuntPerformer.getName() + " " + stuntPerformer.getSurname() + " " + stuntPerformer.getHeight() + " cm");
            }
        }
        int e = sameCountryStuntArray.size();
        if(e==0){
            System.out.println("No result");
        }else{
            for (String s : sameCountryStuntArray) {
                System.out.println(s.replace("[", "").replace("]", "").replace(",", ""));
            }
            sameCountryStuntArray.removeAll(sameCountryStuntArray);
        }
        System.out.println();
        System.out.println(("-------------------------------------------------------"));
    }

    public static void listBeforeYear(String year) {
        int yearInt = Integer.parseInt(year);
        ArrayList<String> featureArraysize = new ArrayList<>();
        for (FeatureFilm film : featureArray) {
            String[] dates = film.getReleaseDate().split("\\.");
            if (Integer.parseInt(dates[2]) < yearInt) {
                featureArraysize.add(film.getFilmTitle());
                //System.out.println("Film title: " + featureArray.get(i).getFilmTitle() + " " + "(" + dates[2] + ")" + "\n" + featureArray.get(i).getRuntime() + " min" + "\n" + "Language: " + featureArray.get(i).getLanguage() + "\n");
            }
        }
        int x = featureArraysize.size();
        if(x==0){
            System.out.println("No result");
            System.out.println();
        }else{
            for (FeatureFilm featureFilm : featureArray) {
                String[] dates = featureFilm.getReleaseDate().split("\\.");
                if (Integer.parseInt(dates[2]) < yearInt) {
                    System.out.println("Film title: " + featureFilm.getFilmTitle() + " " + "(" + dates[2] + ")" + "\n" + featureFilm.getRuntime() + " min" + "\n" + "Language: " + featureFilm.getLanguage() + "\n");
                }
            }
        }

        System.out.println(("-------------------------------------------------------"));
    }

    public static void listAfterYear(String year) {
        int yearInt = Integer.parseInt(year);
        ArrayList<String> featureArraysize2 = new ArrayList<>();
        for (FeatureFilm film : featureArray) {
            String[] dates = film.getReleaseDate().split("\\.");
            if (Integer.parseInt(dates[2]) >= yearInt) {
                featureArraysize2.add(film.getFilmTitle());
            }
        }
        int x = featureArraysize2.size();
        if(x==0){
            System.out.println("No result");
            System.out.println();
        }else{
            for (FeatureFilm featureFilm : featureArray) {
                String[] dates = featureFilm.getReleaseDate().split("\\.");
                if (Integer.parseInt(dates[2]) >= yearInt) {
                    System.out.println("Film title: " + featureFilm.getFilmTitle() + " " + "(" + dates[2] + ")" + "\n" + featureFilm.getRuntime() + " min" + "\n" + "Language: " + featureFilm.getLanguage() + "\n");
                }
            }
        }

        System.out.println(("-------------------------------------------------------"));
    }

    public static String filmIdtofilmTitle(String filmIdd) {//CONVERTING FILM ID TO FILM TITLE
        String filmTitle = "";
        for (Film film : filmsArray) {
            if (filmIdd.equalsIgnoreCase(film.getFilmId())) {
                return film.getFilmTitle();
            }
        }
        return filmTitle;
    }

    public static void editRates(String userId, String filmId, String newRate) { //EDIT RATES OF USER WITH THE NEW RATES
        for (Rate rate : rateArray) {
            if ((rate.getUserId().equals(userId)) && (rate.getFilmId().equals(filmId))) {
                System.out.println("New ratings done successfully");
                System.out.println("Film title: " + filmIdtofilmTitle(rate.getFilmId()));
                rate.setRatingPoint(newRate);

                System.out.println("Your rating: " + rate.getRatingPoint());
                System.out.println();
            }
        }
        System.out.println(("-------------------------------------------------------"));
    }

    public static void removeRates(String userId, String filmId) {

        for (int i = 0; i < rateArray.size(); i++) {
            if ((rateArray.get(i).getUserId().equals(userId)) && (rateArray.get(i).getFilmId().equals(filmId))) {
                System.out.println("Your film rating was removed successfully");
                System.out.println("Film title: " + filmIdtofilmTitle(rateArray.get(i).getFilmId()));
                System.out.println();
                rateArray.remove(new Rate(userId,filmId,rateArray.get(i).getRatingPoint()));
            }
        }


        System.out.println(("-------------------------------------------------------"));

    }

    public static void listByDegree() { //FOR THE LIST BY RATE DEGREE COMMAND LISTING THE FILMS WITH RATINGS
        System.out.println("FeatureFilm:");
        if(featureArray.size()==0){
            System.out.println("No result");
        }else{
            for (FeatureFilm featureFilm : featureArray) {
                System.out.println(featureFilm.getFilmTitle() + " (" + releaseDateToYear(featureFilm.getReleaseDate()) + ")" + " " + rateAverage2(featureFilm.getFilmId()));
            }
        }


        System.out.println();

        System.out.println("ShortFilm:");
        if(shortArray.size()==0){
            System.out.println("No result");
        }else {
            for (ShortFilm shortFilm : shortArray) {
                System.out.println(shortFilm.getFilmTitle() + " (" + releaseDateToYear(shortFilm.getReleaseDate()) + ")" + " " + rateAverage2(shortFilm.getFilmId()));
            }
        }
        System.out.println();

        System.out.println("Documentary:");
        if(docArray.size()==0){
            System.out.println("No result");
        }else{
            for (Documentary documentary : docArray) {
                System.out.println(documentary.getFilmTitle() + " (" + releaseDateToYear(documentary.getReleaseDate()) + ")" + " " + rateAverage2(documentary.getFilmId()));
            }
        }

        System.out.println();

        System.out.println("TVSeries:");
        if(seriesArray.size()==0){
            System.out.println("No result");
        }else{
            for (TVSeries tvSeries : seriesArray) {
                System.out.println(tvSeries.getFilmTitle() + " (" + tvSeriesYear(tvSeries.getStartDate(), tvSeries.getEndDate()) + ")" + " " + rateAverage2(tvSeries.getFilmId()));
            }
        }
        System.out.println();
    }


    public static String releaseDateToYear(String releaseDate){ //FOR GETTING ONLY THE YEAR OF DATE
        String[] date = releaseDate.split("\\.");
        return date[2];
    }
    public static String tvSeriesYear(String sDate,String eDate){ //FOR GETTING THE START-END YEAR OF SERIES
        String[] startYear = sDate.split("\\.");
        String[] endYear = eDate.split("\\.");
        return startYear[2]+"-"+endYear[2];
    }

}

