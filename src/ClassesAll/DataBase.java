package ClassesAll;


import java.io.Serializable;
import java.util.ArrayList;


public class DataBase implements Serializable {
    private static final long serialVersionUID = -3599706612236618200L;

    public static GameVariables table;
    public static ArrayList<GameVariables> savedGamesList;

    public DataBase(){

        savedGamesList= new ArrayList<>();
        GameVariables game= table;
        savedGamesList.add(game);
    }
}
