
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static ArrayList<Article> arts = new ArrayList<>();
    static ArrayList<Author> aut = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {



        try
        {



            String fileName = "output.txt";
            final boolean append = false, autoflush = true;
            PrintStream printStream = new PrintStream(new FileOutputStream(fileName, append),  autoflush);

            System.setOut(printStream);
            String authortxt = args[0];
            File textfileauthor = new File(authortxt);
            Scanner sa = new Scanner(textfileauthor);
            while(sa.hasNextLine()){
                String vala = sa.nextLine();
                String[] vials = vala.split(" ");
                if(Arrays.stream(vials).count()==2){
                    Author ar = new Author(Integer.parseInt(vials[1]));
                    aut.add(ar);
                }else{
                    Author ar = new Author(Integer.parseInt(vials[1]), vials[2], vials[3], vials[4], vials[5]);
                    if(Arrays.stream(vials).count()>6){
                        for(int i = 0; i < Arrays.stream(vials).count()-6;i++){
                            ar.addNumber(Integer.parseInt(vials[6+i]));
                        }
                    }

                    aut.add(ar);
                }


            }

            String filename = args[1];
            File textFile = new File(filename);

            Scanner sc = new Scanner(textFile);
            while(sc.hasNextLine()){
                String value = sc.nextLine();
                String[] arr = value.split(" ");
                if(arr[0].equalsIgnoreCase("read")){
                    read(arr[1]);
                }else if(arr[0].equalsIgnoreCase("list")){
                    list();
                }else if(arr[0].equalsIgnoreCase("sortedall")){
                    sortedAll();
                }else if(arr[0].equalsIgnoreCase("del")){
                    del(Integer.parseInt(arr[1]));
                }else if(arr[0].equalsIgnoreCase("completeAll")){
                    completeAll();
                }
            }
            System.setErr(printStream);


        }
        catch(IOException e)
        {
            System.out.println("Error during reading/writing");
        }




}

    public static ArrayList read(String txt) throws FileNotFoundException {
        String art = txt;
        File textfile = new File(art);
        Scanner sc = new Scanner(textfile);
        boolean isArt = false;
        while (sc.hasNextLine()) {
            String value = sc.nextLine();
            String[] val = value.split(" ");
            if (val[0].equalsIgnoreCase("article")) {
                isArt = true;
                Article ar = new Article(Integer.parseInt(val[1]), val[2], val[3], Integer.parseInt(val[4]));
                arts.add(ar);
            } else if (val[0].equalsIgnoreCase("author")) {
                Author ar = new Author(Integer.parseInt(val[1]), val[2], val[3], val[4], val[5]);
                aut.add(ar);
            }
        }
        if (isArt) {
            return arts;
        } else {
            return aut;
        }
    }

    public static void list(){
        System.out.println("--------------------------------------List-------------------------------------");
        for(int i = 0; i < aut.size(); i++){
            int num = aut.get(i).getId();
            System.out.println(aut.get(i).list());
            for(int k = 0; k < aut.get(i).getBookNum().size();k++ ){
                int number = aut.get(i).getBookNum().get(k);
                for(int t = 0; t < arts.size();t++){
                    if(arts.get(t).getAuthourBook(number).equalsIgnoreCase("not")){
                        continue;
                    }
                    System.out.println(arts.get(t).getAuthourBook(number));
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("--------------------------------------End--------------------------------------");
    }

    public static void completeAll(){
        for(int i = 0; i < aut.size(); i++){
            int num = aut.get(i).getId();
            int books = aut.get(i).getBookNum().size();
            if(books == 0){
                for(int k = 0; k < arts.size(); k++){
                    if(aut.get(i).getBookNum().size()==5){
                        break;
                    }
                    int a = arts.get(k).getAuthourBook2(num);
                    if(a!=0){
                        aut.get(i).addNumber(a);
                    }

                }
            }else{
                for(int t = 5 - books; t < 5; t++){
                    for(int k = 0; k < arts.size(); k++){
                        if(aut.get(i).getBookNum().size()==5){
                            break;
                        }
                        int a = arts.get(k).getAuthourBook2(num);
                        if(a!=0 && !aut.get(i).getBookNum().contains(a)){
                            aut.get(i).addNumber(a);
                        }

                    }


                }
            }

        }
        System.out.println("*****************************completeAll Successful****************************");
    }

    public static void sortedAll() {

        for(int j = 0; j < aut.size();j++){
            Collections.sort(aut.get(j).getBookNum());
        }

        int i = 0;
        while(i < arts.size()){
            boolean match = false;
            int a = arts.get(i).getPaperid();
            for(int j = i+1; j < arts.size();j++){
                if(a < arts.get(j).getPaperid()){
                    continue;
                }else{
                    match = true;
                    Article temp = arts.get(i);
                    arts.remove(i);
                    arts.add(temp);
                    i = 0;
                    break;
                }
            }
            if(match==true){
                i = 0;
            }else{
                i++;
            }
        }
        System.out.println("******************************SortedAll Successful*****************************");

    }

    public static void del(int num){
        for(int i = 0; i < aut.size(); i++){
            if(aut.get(i).getId()==num){
                aut.remove(i);
            }
        }
        System.out.println("*********************************del Successful********************************");
    }

}