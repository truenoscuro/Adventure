import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Plantilla {
    private static BufferedReader br = null;
    private static String rd;

    private static void open( int num)  {

        try {
            br = new BufferedReader(new FileReader("utils/day"+ num +".txt"));
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




    public static void main(String[] args) {

    }
}
