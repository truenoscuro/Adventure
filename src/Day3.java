import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3 {
    private static BufferedReader br = null;
    private static String rd;

    private static void open( int num) throws IOException {
        br = new BufferedReader(new FileReader("utils/day"+ num +".txt"));
        read(); // <-- initial read
    }
    private static void read() throws IOException {
        rd = br.readLine();
    }

    private static int sumPriority( char a ){
        return ( a - 'a' > 0)? a - 'a' + 1 : a - 'A' + 1 + 26 ;
    }

    private static boolean isElement( char a, String backpack ){
        int leng = backpack.length();
        int j = leng/2;
        for ( int i = j ;  i < leng; i++ ){
            if(a == backpack.charAt(i)) return true;
        }
        return  false;
    }


    private  static int searchPriority( String backpack){
        int i = 0;
        char element;
        do{
            element = backpack.charAt( i++ );
        }while ( !isElement(element,backpack) );
        return sumPriority(element);
    }

    private static boolean isElementBackpack( char a , String backpack ){
        int leng = backpack.length();
        for ( int i = 0 ;  i < leng; i++ ){
            if( a == backpack.charAt( i ) ) return true;
        }
        return  false;
    }

    private  static int searchThreePriority( String[] backpack){
        int i = 0;
        char element;
        do{
            element = backpack[0].charAt( i++ );
        } while (   !(isElementBackpack( element,backpack[ 1 ] ) &&
                    isElementBackpack( element , backpack[ 2 ] ))
                );

        return sumPriority(element);
    }


    public static void main(String[] args) throws IOException {
        // Part 1
       open(3);
       int total = 0;
       while ( rd != null ){
           total += searchPriority( rd );
           read();
       }
       System.out.println( " total --> " + total );
        // Part 2

        String [] backpacks = new String[3];
        int total3 = 0;
        open(3);
        String [] example = {
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg"
        };
        System.out.println( "Example --> " + searchThreePriority( example ));
        backpacks[0] = rd;
        read();
        backpacks[1] = rd;
        read();
        backpacks[2] = rd;

        while ( rd != null ){
            total3 += searchThreePriority( backpacks );
            read();
            backpacks[0] = rd;
            read();
            backpacks[1] = rd;
            read();
            backpacks[2] = rd;
        }
        System.out.println( " total --> " + total3 );




    }



}
