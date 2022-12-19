import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Plantilla {
    private static BufferedReader br = null;
    private static String rd;

    private static void open( )  {
        String path = (isExample)? "examples":"utils";
        try {
            br = new BufferedReader(new FileReader(path+"/day"+ numTxt +".txt"));
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
















    private static void  problem1(){

    }
    private static void  problem2(){

    }


    static boolean isExample = true;
    static int numTxt = 0;
    public static void main(String[] args) {



    }
}
