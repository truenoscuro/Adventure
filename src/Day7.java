import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day7 {
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
    private static boolean isCommand(){
        return rd.charAt(0) == '$';
    }
    private static String comand(){
        String  cm = "";
        int i = 2;
        while( rd.charAt(i) != ' ') cm += rd.charAt(i++);
        return cm;
    }
    private static String cd(){
        String dir = " ";
        int i = rd.length()-1;
        while( rd.charAt(i) != ' ')  dir = rd.charAt(i--)+dir;
        return dir;
    }
    private static void ls(){

    }

    /*
    /
    ls
    dir a
    size
    size
    dir b
    (a,b) + suma(a) + suma(b)

     */




    public static void main(String[] args) {
        open(7);
        System.out.println(comand());
        System.out.println(cd());

    }
}
