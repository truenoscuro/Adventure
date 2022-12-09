import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day9 {
    private static BufferedReader br = null;
    private static String rd;

    private static void open( boolean isExample , int num )  {
        String path = (isExample)? "examples":"utils";
        try {
            br = new BufferedReader(new FileReader(path+"/day"+ num +".txt"));
            read();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // <-- initial read
    }
    private static void read()  {
        try {
            rd = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static ArrayList<> visitets = new ArrayList<>();

    private static  boolean isVisited( int [] point ){
        for( Object v: visitets){
            if(v[0] == point[0]);

        }

    }
    public static void main(String[] args) {


    }
}
