import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    /*
    Rock --> A, X(Lost) -1
    Paper --> B,Y(draw) 0
    Scissors --> C,Z (Win) 1
     */
    private  static boolean isRock(char e){
        return e == 'A' || e == 'X' ;
    }


    private static boolean isPaper(char e){
        return e =='B' || e == 'Y';
    }
    private static boolean isScissors(char e){
        return e =='C' || e == 'Z';
    }
    private static int pointForElecction( char e){
        int point = 1; // <-- Point Rock
        if(isPaper(e)) point = 2; // Point Paper
        else if(isScissors(e)) point = 3; //Point Scissors
        return point;
    }
    private static boolean winLeft(char o,char y){
        return  isRock( o ) && isScissors( y ) || // win Rock
                isScissors( o ) && isPaper(y ) || // win Scissors
                isPaper( o ) && isRock( y ) ;     // win Paper
    }
    private static boolean drawBattle(char o ,char y){
        return  isRock( o ) && isRock( y ) ||
                isScissors( o ) && isScissors( y ) ||
                isPaper( o ) && isPaper( y );
    }

    private static int pointsForBattle(char o, char y){
        int point = 0;                                 // lost
        if( drawBattle( o , y ) ) point = 3; //draw
        else if (winLeft( y , o )) point = 6;              //win
        return point + pointForElecction( y );
    }

    private  static  int strategic (char y){
        int strategic = -1;
        if ( y == 'Y') strategic = 0;
        else if ( y == 'Z') strategic = 1;
        return strategic;
    }


    private static int pointForStrategic( char e,char y ){
        int strategic = strategic( y );
        int point = 0;
        if( strategic > 0){
            
        }else if (strategic == 0){
            point = pointForElecction( e );
        }else{

        }
        return point;
    }
    private static int pointsForBattleStrategic(char o, char y){
        int strategic = strategic( y );
        int point = 0;                                 // lost
        if( strategic == 0) point = 3; //draw
        else if (strategic > 0) point = 6;              //win
        return point + pointForElecction( y );
    }


    // Writtin
    private static BufferedReader br = null;
    private static String rd;

    private static void open() throws IOException {
        br = new BufferedReader(new FileReader("utils/day2.txt"));
        read(); // <-- initial read
    }
    private static void read() throws IOException {
        rd = br.readLine();
    }


    public static void main(String[] args) throws IOException {

        int totalP = 0;
        totalP = pointsForBattle('A','Y');
        System.out.println(totalP);
        totalP += pointsForBattle('B','X');
        System.out.println(totalP);
        totalP += pointsForBattle('C','Z');
        System.out.println(totalP);

        //init
        char e;
        char y;
        open(); // open always read 1
        //solve;
        int totalR = 0;
        int totalL = 0;
        while( rd != null){
            e = rd.charAt( 0 );
            y = rd.charAt( 2 );
            totalR += pointsForBattle(e,y);
            totalL += pointsForBattle(y,e);
            read();
        }
        System.out.println(rd);
        System.out.println(" totalR --> " + totalR);
        System.out.println(" totalL --> " + totalL);


    }
}
