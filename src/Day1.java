import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day1 {
    static BufferedReader br = null;
    static String rd;


    private static void open() throws IOException {
        br = new BufferedReader(new FileReader("utils/input.txt"));
        read(); // <-- initial read
    }
    private static void read() throws IOException {
        rd = br.readLine();
    }
    private static boolean isBackpackEnd(){
      return rd == null || rd.equals("");
    };
    private static int countBackpack() throws IOException {
        int total = 0;

        while( !isBackpackEnd() ){
            total += Integer.parseInt( rd );
            read();
        }
        read(); //<-- next backpack line;
        return total;
    }
    public static void main(String[] args) throws IOException {
        //Part 1
        //init folder
        open();
        //init parameter
        int total;
        //initial max
        int max = countBackpack();
        while( !isBackpackEnd() ){
            total = countBackpack();
            if( total > max ){ //change if total is higher max
                max = total;
            }
        }

        System.out.println("Total max --> " + max );
        //Part 2
        open();
        int [] max3 = { countBackpack() , countBackpack() , countBackpack() };
        Arrays.sort( max3 );


        while( !isBackpackEnd() ){
            total = countBackpack();
            for( int i = 0; i < max3.length; i++) {
                if( total > max3[ i ]){
                    max3[ i ] = total;
                    break;
                }
            }
        }
        System.out.println("Total max 3 --> " + Arrays.stream(max3).sum());





    }
}
