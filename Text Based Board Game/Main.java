import java.io.*;
import java.util.*;


public class Main {
    public static String filenamee ;


    public static String[] readFile(String fileName) {  //METHOD FOR READING THE INITIALS AND COMMANDS
        String[] temp = new String[100];
        int i = 0;
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                temp[i++] = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            AppendToFile("An error occurred.");
            e.printStackTrace();
        }
        String[] data = new String[i];
        if (i >= 0) System.arraycopy(temp, 0, data, 0, i);
        return data;
    }

    public static void draw(String[][] board, int x, int y) {   //METHOD FOR DRAWING BOARD WITH * USING FOR LOOPS
        for (int i = 0; i < x + 1; i++) {
            AppendToFile("**");
        }
        AppendToFile("\n");

        for (int i = 0; i < x; i++) {
            AppendToFile("*");
            for (int j = 0; j < y; j++) {
                AppendToFile(board[i][j]);
            }
            AppendToFile("*\n");
        }
        for (int i = 0; i < x + 1; i++) {
            AppendToFile("**");
        }
        AppendToFile("\n\n");
    }

    public static void isDead(String[][] board, int x, int y, Character[] characters) { //CHECKING AND CHANGING THE DEATH CHARACTER
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (Character character : characters) {
                    if (character.getName().equals(board[i][j])) {
                        if (character.getHp() <= 0) {
                            board[i][j] = "  ";
                        }
                    }
                }
            }
        }
    }

    public static int winner(String[][] board, int x, int y) { // METHOD FOR SELECTING WINNER
        int zorde = 0, calliance = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j].charAt(0) == 'H' || board[i][j].charAt(0) == 'E' || board[i][j].charAt(0) == 'D')
                    calliance++;
                else if (board[i][j].charAt(0) == 'O' || board[i][j].charAt(0) == 'T' || board[i][j].charAt(0) == 'G')
                    zorde++;
            }
        }
        if (zorde == 0 && calliance == 0)
            return 3;
        else if (zorde != 0 && calliance == 0)
            return 2;
        else if (zorde == 0 && calliance != 0)
            return 1;
        else
            return 0;
    }

    public static void charactersInfo(Character[] characters) { //METHOD FOR WRITING THE CHARACTERS AND INFOS TO THE FILE


            for (Character character : characters) {

                if (character.getHp() > 0) {
                    AppendToFile(character + "\n");
                }
            }
            AppendToFile("\n");
        }

    public static void AppendToFile (String data) { //METHOD FOR WRITING OUTPUTS TO THE OUTPUT FILE
        try {

            File f1 = new File(filenamee);


            FileWriter fileWritter = new FileWriter(f1.getName(),true);

            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.append(data);
            bw.close();
            fileWritter.close();


        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main( String[] args) throws FileNotFoundException {
        filenamee = args[2];           //OUTPUT FILE
        final boolean append = false, autoflush = true;
        PrintStream printStream = new PrintStream(new FileOutputStream(filenamee, append),  autoflush);

        System.setOut(printStream);



        String[] initials;
        initials = readFile(args[0]);

        String[] tokens = initials[1].split("x");
        int x = Integer.parseInt(tokens[0]);
        int y = Integer.parseInt(tokens[1]);
        String[][] board = new String[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = "  ";
            }
        }
        int characterCount = 0; //FROM THE INITIALS FILE GETTING THE CHARACTERS
        for (String initial : initials) {
            tokens = initial.split(" ");
            if (tokens[0].equals("ELF") || tokens[0].equals("DWARF") || tokens[0].equals("HUMAN")
                    || tokens[0].equals("GOBLIN") || tokens[0].equals("ORK") ||tokens[0].equals("TROLL") ) {
                board[Integer.parseInt(tokens[3])][Integer.parseInt(tokens[2])] = tokens[1];
                characterCount++;
            }
        }
        Character[] characters = new Character[characterCount--];
        int sort = characterCount + 1;
        for (String initial : initials) {
            tokens = initial.split(" ");
            if (tokens[0].equals("ELF")) {
                characters[characterCount--] = new Elf(tokens[1]);
            } else if (tokens[0].equals("DWARF")) {
                characters[characterCount--] = new Dwarf(tokens[1]);
            } else if (tokens[0].equals("HUMAN")) {
                characters[characterCount--] = new Human(tokens[1]);
            } else if (tokens[0].equals("TROLL")) {
                characters[characterCount--] = new Troll(tokens[1]);
            } else if (tokens[0].equals("ORK")) {
                characters[characterCount--] = new Ork(tokens[1]);
            } else if (tokens[0].equals("GOBLIN")) {
                characters[characterCount--] = new Goblin(tokens[1]);
            }
        }

        Character temp;
        for (int i = 0; i < sort; i++)
        {
            for (int j = i + 1; j < sort; j++) {
                if (characters[i].getName().compareTo(characters[j].getName())>0)
                {
                    temp = characters[i];
                    characters[i] = characters[j];
                    characters[j] = temp;
                }
            }
        }


        draw(board, y, x);
        charactersInfo(characters);

        String[] commands ; //SOLVING THE COMMANDS AND MAKING MOVES
        commands = readFile(args[1]);

        for (String command : commands) {
            tokens = command.split(" ");
            boolean flag = true;
            String name = tokens[0];

            int posX = 0, posY = 0;
            for (int j = 0; j < x; j++) {
                for (int k = 0; k < y; k++) {
                    if (board[j][k].equals(tokens[0])) {
                        posX = j;
                        posY = k;
                        break;
                    }
                }
            }
            tokens = tokens[1].split(";");

            for (int i = 0; i < characters.length; i++) { // MOVE COUNTER CHECK
                if (characters[i].getName().equals(name)) {
                    characterCount = i;
                    flag = characters[i].checkMaxMove((tokens.length + 1) / 2);
                }
            }

            if (flag) {
                int tempX = posX, tempY = posY;
                for (int j = 0; j < tokens.length; j++) { // BOUNDARY CHECK
                    if (j % 2 != 0) {
                        tempX = tempX + (Integer.parseInt(tokens[j]));
                    } else {
                        tempY  = tempY + (Integer.parseInt(tokens[j]));
                    }
                    if (tempX >= x || tempX < 0 || tempY >= y || tempY < 0)
                        flag = false;
                }
                if (!flag) {
                    AppendToFile("\n");
                    AppendToFile("Error : Game board boundaries are exceeded. Input line ignored."+"\n");


                } else {
                    if (name.charAt(0) == 'O') { //ORK HEALING PART
                        for (Character character : characters) {
                            if (character.getName().equals(name)){
                                character.Heal();
                                break;
                            }
                        }
                        if (posX + 1 < x) {
                            if (board[posX + 1][posY].charAt(0) == 'T' || board[posX + 1][posY].charAt(0) == 'G' || board[posX + 1][posY].charAt(0) == 'O' ) {
                                for (Character character : characters) {
                                    if (character.getName().equals(board[posX + 1][posY])){
                                        character.Heal();
                                        break;
                                    }
                                }
                            }
                        }

                        if (posX + 1 < x && posY + 1 < y) {
                            if (board[posX + 1][posY + 1].charAt(0) == 'T' || board[posX + 1][posY + 1].charAt(0) == 'G' || board[posX + 1][posY + 1].charAt(0) == 'O' ) {
                                for (Character character : characters) {
                                    if (character.getName().equals(board[posX + 1][posY + 1])){
                                        character.Heal();
                                        break;
                                    }
                                }
                            }
                        }

                        if (posX + 1 < x && posY - 1 >= 0) {
                            if (board[posX + 1][posY - 1].charAt(0) == 'T' || board[posX + 1][posY - 1].charAt(0) == 'G' || board[posX + 1][posY - 1].charAt(0) == 'O' ) {
                                for (Character character : characters) {
                                    if (character.getName().equals(board[posX + 1][posY - 1])){
                                        character.Heal();
                                        break;
                                    }
                                }
                            }
                        }

                        if (posX - 1 >= 0) {
                            if (board[posX - 1][posY].charAt(0) == 'T' || board[posX - 1][posY].charAt(0) == 'G' || board[posX - 1][posY].charAt(0) == 'O' ){
                                for (Character character : characters) {
                                    if (character.getName().equals(board[posX - 1][posY])) {
                                        character.Heal();
                                        break;
                                    }
                                }
                            }
                        }

                        if (posX - 1 >= 0 && posY + 1 < y) {
                            if (board[posX - 1][posY + 1].charAt(0) == 'T' || board[posX - 1][posY + 1].charAt(0) == 'G' || board[posX - 1][posY + 1].charAt(0) == 'O' ) {
                                for (Character character : characters) {
                                    if (character.getName().equals(board[posX - 1][posY + 1])){
                                        character.Heal();
                                        break;
                                    }
                                }
                            }
                        }

                        if (posX - 1 >= 0 && posY - 1 >= 0) {
                            if (board[posX - 1][posY - 1].charAt(0) == 'T' || board[posX - 1][posY - 1].charAt(0) == 'G' || board[posX - 1][posY - 1].charAt(0) == 'O' ) {
                                for (Character character : characters) {
                                    if (character.getName().equals(board[posX - 1][posY - 1])){
                                        character.Heal();
                                        break;
                                    }
                                }
                            }
                        }

                        if (posY + 1 < y) {
                            if (board[posX][posY + 1].charAt(0) == 'T' || board[posX][posY + 1].charAt(0) == 'G' || board[posX][posY + 1].charAt(0) == 'O' ){
                                for (Character character : characters) {
                                    if (character.getName().equals(board[posX][posY + 1])) {
                                        character.Heal();
                                        break;
                                    }
                                }
                            }
                        }
                        if (posY - 1 >= 0) {
                            if (board[posX][posY - 1].charAt(0) == 'T' || board[posX][posY - 1].charAt(0) == 'G'
                                    || board[posX][posY - 1].charAt(0) == 'O' ) {
                                for (Character character : characters) {
                                    if (character.getName().equals(board[posX][posY - 1])){
                                        character.Heal();
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    loops:
                    for (int j = 0; j < tokens.length; j+=2) {
                        boolean zordeFriend = name.charAt(0) == '0' || name.charAt(0) == 'G' || name.charAt(0) == 'T'; //FRIEND CHECK


                        if (zordeFriend) {
                            if (board[posX + (Integer.parseInt(tokens[j + 1]))][posY + (Integer.parseInt(tokens[j ]))].charAt(0) == 'T' ||
                                    board[posX + (Integer.parseInt(tokens[j + 1]))][posY + (Integer.parseInt(tokens[j]))].charAt(0) == 'G' ||
                                    board[posX + (Integer.parseInt(tokens[j + 1]))][posY + (Integer.parseInt(tokens[j]))].charAt(0) == 'O') {
                                break;
                            }
                        } else {
                            if (board[posX + (Integer.parseInt(tokens[j+ 1]))][posY + (Integer.parseInt(tokens[j]))].charAt(0) == 'H' ||
                                    board[posX + (Integer.parseInt(tokens[j + 1]))][posY + (Integer.parseInt(tokens[j]))].charAt(0) == 'E' ||
                                    board[posX + (Integer.parseInt(tokens[j + 1]))][posY + (Integer.parseInt(tokens[j]))].charAt(0) == 'D') {
                                break;
                            }
                        }

                        if (board[posX + (Integer.parseInt(tokens[j + 1]))][posY + (Integer.parseInt(tokens[j]))].equals("  ")) {
                            board[posX][posY] = "  ";
                            posX = posX + (Integer.parseInt(tokens[j + 1]));
                            posY = posY + (Integer.parseInt(tokens[j]));
                            board[posX][posY] = name;
                            if (name.charAt(0) == 'E' || name.charAt(0) == 'D') { //FOR THE ATTACKS EVERY STEP

                                if (name.charAt(0) == 'E' && (j + 1) == (tokens.length - 1)) {
                                    if (posX + 2 < x){
                                        if (board[posX + 2][posY].charAt(0) == 'O' || board[posX + 2][posY].charAt(0) == 'T' || board[posX + 2][posY].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX + 2][posY])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX + 2 < x && posY + 2 < y){
                                        if (board[posX + 2][posY + 2].charAt(0) == 'O' || board[posX + 2][posY + 2].charAt(0) == 'T' || board[posX + 2][posY + 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX + 2][posY + 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX + 1 < x && posY + 2 < y){
                                        if (board[posX + 1][posY + 2].charAt(0) == 'O' || board[posX + 1][posY + 2].charAt(0) == 'T' || board[posX + 1][posY + 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX + 1][posY + 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX + 2 < x && posY + 1 < y){
                                        if (board[posX + 2][posY + 1].charAt(0) == 'O' || board[posX + 2][posY + 1].charAt(0) == 'T' || board[posX + 2][posY + 1].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX + 2][posY + 1])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX + 2 < x && posY - 2 >= 0){
                                        if (board[posX + 2][posY - 2].charAt(0) == 'O' || board[posX + 2][posY - 2].charAt(0) == 'T' || board[posX + 2][posY - 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX + 2][posY - 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX + 1 < x && posY - 2 >= 0){
                                        if (board[posX + 1][posY - 2].charAt(0) == 'O' || board[posX + 1][posY - 2].charAt(0) == 'T' || board[posX + 1][posY - 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX + 1][posY - 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX + 2 < x && posY - 1 >= 0){
                                        if (board[posX + 2][posY - 1].charAt(0) == 'O' || board[posX + 2][posY - 1].charAt(0) == 'T' || board[posX + 2][posY - 1].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX + 2][posY - 1])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX - 2 >= 0) {
                                        if (board[posX - 2][posY].charAt(0) == 'O' || board[posX - 2][posY].charAt(0) == 'T' || board[posX - 2][posY].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX - 2][posY])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX - 2 >= 0 && posY - 2 >= 0){
                                        if (board[posX - 2][posY - 2].charAt(0) == 'O' || board[posX - 2][posY - 2].charAt(0) == 'T' || board[posX - 2][posY - 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX - 2][posY - 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX - 1 >= 0 && posY - 2 >= 0){
                                        if (board[posX - 1][posY - 2].charAt(0) == 'O' || board[posX - 1][posY - 2].charAt(0) == 'T' || board[posX - 1][posY - 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX - 1][posY - 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX - 2 >= 0 && posY - 1 >= 0){
                                        if (board[posX - 2][posY - 1].charAt(0) == 'O' || board[posX - 2][posY - 1].charAt(0) == 'T' || board[posX - 2][posY - 1].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX - 2][posY - 1])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX - 2 >= 0 && posY + 2 < y){
                                        if (board[posX - 2][posY + 2].charAt(0) == 'O' || board[posX - 2][posY + 2].charAt(0) == 'T' || board[posX - 2][posY + 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX - 2][posY + 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX - 1 >= 0 && posY + 2 < y){
                                        if (board[posX - 1][posY + 2].charAt(0) == 'O' || board[posX - 1][posY + 2].charAt(0) == 'T' || board[posX - 1][posY + 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX - 1][posY + 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posX - 2 >= 0 && posY + 1 < y){
                                        if (board[posX - 2][posY + 1].charAt(0) == 'O' || board[posX - 2][posY + 1].charAt(0) == 'T' || board[posX - 2][posY + 1].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX - 2][posY + 1])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posY + 2 < y) {
                                        if (board[posX][posY + 2].charAt(0) == 'O' || board[posX][posY + 2].charAt(0) == 'T' || board[posX][posY + 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX][posY + 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }

                                    if (posY - 2 >= 0) {
                                        if (board[posX][posY - 2].charAt(0) == 'O' || board[posX][posY - 2].charAt(0) == 'T' || board[posX][posY - 2].charAt(0) == 'G') {
                                            for (Character character : characters) {
                                                if (character.getName().equals(board[posX][posY - 2])) {
                                                    characters[characterCount].Attack(character);
                                                }
                                            }
                                        }
                                    }
                                }

                                callianceAttack(x, y, board, characterCount, characters, posX, posY);

                            } else if (name.charAt(0) == 'G') { //FOR THE ATTACKS EVERY STEP

                                zordeAttack(x, y, board, characterCount, characters, posX, posY);

                            } else if (name.charAt(0) == 'O' || name.charAt(0) == 'T') {

                                if ((j + 1) == (tokens.length - 1)){
                                    zordeAttack(x, y, board, characterCount, characters, posX, posY);
                                }

                            } else if (name.charAt(0) == 'H') {
                                if ((j + 1) == (tokens.length - 1)){
                                    callianceAttack(x, y, board, characterCount, characters, posX, posY);
                                }
                            }
                        } else {
                            for (Character character : characters) {
                                if (character.getName().equals(board[posX + (Integer.parseInt(tokens[j + 1]))][posY + (Integer.parseInt(tokens[j]))])) {
                                    characters[characterCount].Attack(character);
                                    if (characters[characterCount].getHp() > character.getHp()) {
                                        characters[characterCount].setHp(characters[characterCount].getHp() - character.getHp());
                                        character.setHp(0);
                                        board[posX + (Integer.parseInt(tokens[j + 1]))][posY + (Integer.parseInt(tokens[j]))] = board[posX][posY];
                                        board[posX][posY] = "  ";
                                        break loops;
                                    } else if (characters[characterCount].getHp() < character.getHp()) {
                                        character.setHp(character.getHp() - characters[characterCount].getHp());
                                        characters[characterCount].setHp(0);
                                        board[posX][posY] = "  ";
                                        break loops;
                                    } else {
                                        character.setHp(0);
                                        characters[characterCount].setHp(0);
                                        board[posX][posY] = "  ";
                                        board[posX + (Integer.parseInt(tokens[j + 1]))][posY + (Integer.parseInt(tokens[j]))] = "  ";
                                        break loops;
                                    }
                                }
                            }
                        }
                    }

                    isDead(board, x, y, characters);
                    draw(board, x, y);
                    charactersInfo(characters);
                    if (winner(board, x, y) == 3) {
                        AppendToFile("Game Finished\nDraw\n");

                        return;
                    } else if (winner(board, x, y) == 2) {
                        AppendToFile("Game Finished\nZorde Wins\n");


                        return;
                    }
                    else if (winner(board, x, y) == 1) {
                        AppendToFile("Game Finished\nCalliance Wins\n");
                        return;
                    }
                }
            } else {
                AppendToFile("\n");
                AppendToFile("Error : Move sequence contains wrong number of move steps. Input line ignored."+"\n");

            }

        }
    }

    public static void callianceAttack(int x, int y, String[][] board, int characterCount, Character[] characters, int posX, int posY) {
        if (posX + 1 < x){
            if (board[posX + 1][posY].charAt(0) == 'O' || board[posX + 1][posY].charAt(0) == 'T' || board[posX + 1][posY].charAt(0) == 'G') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX + 1][posY])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX + 1 < x && posY + 1 < y){
            if (board[posX + 1][posY + 1].charAt(0) == 'O' || board[posX + 1][posY + 1].charAt(0) == 'T' || board[posX + 1][posY + 1].charAt(0) == 'G') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX + 1][posY + 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX + 1 < x && posY - 1 >= 0){
            if (board[posX + 1][posY - 1].charAt(0) == 'O' || board[posX + 1][posY - 1].charAt(0) == 'T' || board[posX + 1][posY - 1].charAt(0) == 'G') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX + 1][posY - 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX - 1 >= 0) {
            if (board[posX - 1][posY].charAt(0) == 'O' || board[posX - 1][posY].charAt(0) == 'T' || board[posX - 1][posY].charAt(0) == 'G') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX - 1][posY])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX - 1 >= 0 && posY - 1 >= 0){
            if (board[posX - 1][posY - 1].charAt(0) == 'O' || board[posX - 1][posY - 1].charAt(0) == 'T' || board[posX - 1][posY - 1].charAt(0) == 'G') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX - 1][posY - 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX - 1 >= 0 && posY + 1 < y){
            if (board[posX - 1][posY + 1].charAt(0) == 'O' || board[posX - 1][posY + 1].charAt(0) == 'T' || board[posX - 1][posY + 1].charAt(0) == 'G') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX - 1][posY + 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posY + 1 < y) {
            if (board[posX][posY + 1].charAt(0) == 'O' || board[posX][posY + 1].charAt(0) == 'T' || board[posX][posY + 1].charAt(0) == 'G') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX][posY + 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posY - 1 >= 0) {
            if (board[posX][posY - 1].charAt(0) == 'O' || board[posX][posY - 1].charAt(0) == 'T' || board[posX][posY - 1].charAt(0) == 'G') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX][posY - 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }
    }

    public static void zordeAttack(int x, int y, String[][] board, int characterCount, Character[] characters, int posX, int posY) {
        if (posX + 1 < x){
            if (board[posX + 1][posY].charAt(0) == 'H' || board[posX + 1][posY].charAt(0) == 'E' || board[posX + 1][posY].charAt(0) == 'D') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX + 1][posY])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX + 1 < x && posY + 1 < y){
            if (board[posX + 1][posY + 1].charAt(0) == 'H' || board[posX + 1][posY + 1].charAt(0) == 'E' || board[posX + 1][posY + 1].charAt(0) == 'D') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX + 1][posY + 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX + 1 < x && posY - 1 >= 0){
            if (board[posX + 1][posY - 1].charAt(0) == 'H' || board[posX + 1][posY - 1].charAt(0) == 'E' || board[posX + 1][posY - 1].charAt(0) == 'D') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX + 1][posY - 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX - 1 >= 0) {
            if (board[posX - 1][posY].charAt(0) == 'H' || board[posX - 1][posY].charAt(0) == 'E' || board[posX - 1][posY].charAt(0) == 'D') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX - 1][posY])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX - 1 >= 0 && posY - 1 >= 0){
            if (board[posX - 1][posY - 1].charAt(0) == 'H' || board[posX - 1][posY - 1].charAt(0) == 'E' || board[posX - 1][posY - 1].charAt(0) == 'D') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX - 1][posY - 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posX - 1 >= 0 && posY + 1 < y){
            if (board[posX - 1][posY + 1].charAt(0) == 'H' || board[posX - 1][posY + 1].charAt(0) == 'E' || board[posX - 1][posY + 1].charAt(0) == 'D') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX - 1][posY + 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posY + 1 < y) {
            if (board[posX][posY + 1].charAt(0) == 'H' || board[posX][posY + 1].charAt(0) == 'E' || board[posX][posY + 1].charAt(0) == 'D') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX][posY + 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }

        if (posY - 1 >= 0) {
            if (board[posX][posY - 1].charAt(0) == 'H' || board[posX][posY - 1].charAt(0) == 'E' || board[posX][posY - 1].charAt(0) == 'D') {
                for (Character character : characters) {
                    if (character.getName().equals(board[posX][posY - 1])) {
                        characters[characterCount].Attack(character);
                    }
                }
            }
        }
    }
}
