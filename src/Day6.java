import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

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
    private static int addRepeat(int [] repeats, char ch ){
        int and = repeats[ ch - 'a' ]++;
        return ( and <= 1 && repeats[ ch - 'a' ] >= 2 ) ? 1 : 0;
    }
    private static int restRepeat(int [] repeats, char ch ){
        int and = repeats[ ch - 'a' ]--;
        return ( and >= 2 &&
                repeats[ ch - 'a' ] <= 1 )? -1 : 0;
    }
    /*
        !(and >  1 && now < 2)
        and <= 1 || now >= 2
     */


    public static void main(String[] args) {

        open(6);

        int leng = rd.length();
        String subString = "";
        // canvia solv a part1 si vols la solucio de la part1  o part2 si vols la solucio
        // de la part 2
        int part1 =  4;
        int part2 = 14;
        int solv = part2;
        int num = solv ;
        for( int i = 0 ; i + solv < leng ; i++ ){
            subString = rd.substring( i , solv + i );
            if( !haveRepeat( subString ) ) break;
            num++;
        }
        // segona forma de solucionarlo
        int p = 0;
        do{
            subString = rd.substring( p, solv + p);
            p++;
        }while(haveRepeat(subString));
        //segona solucio
        System.out.println("p ---> " +(p+solv-1) );
        // primera solucio
        System.out.println("subString --> " +  subString);
        System.out.println("breakPoint --> " +  num);

        // linea solve
        open(6);
        int numRepeats = 0;
        int [] repeats = new int[ 'z' - 'a' + 1 ]; // num char repeat in substring
        for (int r : repeats) r = 0;
        for ( int i = 0; i < solv ;i++) numRepeats += addRepeat( repeats,rd.charAt( i ) );
        int result  = 0;
        for ( int i = 0 ; i + solv  < leng ; i++){
            numRepeats += restRepeat(repeats,rd.charAt( i  ));
            numRepeats += addRepeat( repeats,rd.charAt( i + solv  ) );
            if( numRepeats != 0) continue;
            result = i + solv +1;
            break;
        }
        System.out.println("breakPoint --> "+ result);





    }
}
