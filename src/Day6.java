import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day6 {
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
    private static boolean haveRepeat( String str){
        int leng = str.length();
        for( int i = 0; i < leng ; i++){
            for( int j = i + 1 ; j < leng ; j++){
                if( str.charAt( i ) == str.charAt( j ) ) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        open(6);

        int leng = rd.length();

        String subString = "";
        // canvia solv a part1 si vols la solucio de la part1  o part2 si vols la solucio
        // de la part 2
        int part1 =  4;
        int part2 = 14;
        int solv = part2;
        int num = part2 ;
        for( int i = 0 ; i + solv < leng ; i++ ){
            subString = rd.substring( i , solv + i );
            if( !haveRepeat( subString ) ) break;
            num++;
        }
        // segona forma de solucionarlo
        int i = 0;
        do{
            subString = rd.substring( i, solv + i);
            i++;
        }while(haveRepeat(subString));
        //segona solucio
        System.out.println("i ---> " +(i+solv-1) );
        // primera solucio
        System.out.println("subString --> " +  subString);
        System.out.println("breakPoint --> " +  num);


    }
}
