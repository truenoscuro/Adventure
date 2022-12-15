import Objects.SandPlay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Day14 {
    private static BufferedReader br = null;
    private static String rd;

    private static void open(boolean isExample, int num) {
        String path = (isExample) ? "examples" : "utils";
        try {
            br = new BufferedReader(new FileReader(path + "/day" + num + ".txt"));
            read();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // <-- initial read
    }

    private static void read() {
        try {
            rd = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isNum(char a) {
        return '0' <= a && a <= '9';
    }

    private static char[][] init() {
        ArrayList<Integer> dirC = new ArrayList<>();
        ArrayList<Integer> dirR = new ArrayList<>();
        ArrayList<Integer> split = new ArrayList<>();
        int sp = 0;
        String numString;
        while (rd != null) {
            numString = "";
            char a;
            int indx = 0;
            for (int i = 0; i < rd.length(); i++) {
                a = rd.charAt(i);
                if (isNum(a)) numString = numString + a;
                else if (numString.length() > 0) {
                    int num = Integer.parseInt(numString);
                    if (indx % 2 == 0) dirC.add(num);
                    else {
                        split.add(sp);
                        dirR.add(num);
                    }
                    numString = "";
                    indx = (indx + 1) % 2;
                }
            }
            dirR.add(Integer.parseInt(numString));
            split.add(sp);
            sp++;
            read();
        }
        dirC.add(500);
        dirR.add(0);

        int minC = Collections.min(dirC);
        int maxC = Collections.max(dirC);
        int minR = Collections.min(dirR);
        int maxR = Collections.max(dirR);
        cInit -= minC;
        int lengC = maxC - minC + 1;
        int lengR = maxR - minR + 1;
        rleng = lengR;
        cleng = lengC;

        char[][] table = new char[lengR][lengC];
        for (int i = 0; i < lengR; i++)
            for (int j = 0; j < lengC; j++)
                table[i][j] = '·';
        for (int i = 0 ; i < dirR.size( ) - 2 ; i++) {
            int sp1 = split.get( i );
            int sp2 = split.get( i + 1 );
            if( sp1 != sp2 ) i++;
            int r1 = dirR.get( i ) - minR;
            int c1 = dirC.get( i ) - minC;
            int r2 = dirR.get( i + 1 ) - minR;
            int c2 = dirC.get( i + 1 ) - minC;
            int diffR = Math.abs( r2 - r1 );
            int diffC = Math.abs( c2 - c1 );
            for( int j = 0 ; j < Math.max(diffR,diffC)+1 ; j++)
                if( diffR == 0 ) table[ r1 ][(int) (c1 +Math.signum(c2 - c1)* j)] = '#';
                else table[(int) (r1 + Math.signum(r2-r1)*j)][ c1 ] = '#';
        }
        table[ rInit ][ cInit ] = 'x';
        return table;


    }

    private static SandPlay initSandPlay() {
        ArrayList<Integer> dirC = new ArrayList<>();
        ArrayList<Integer> dirR = new ArrayList<>();
        ArrayList<Integer> split = new ArrayList<>();
        int sp = 0;
        String numString;
        while (rd != null) {
            numString = "";
            char a;
            int indx = 0;
            for (int i = 0; i < rd.length(); i++) {
                a = rd.charAt(i);
                if (isNum(a)) numString = numString + a;
                else if (numString.length() > 0) {
                    int num = Integer.parseInt(numString);
                    if (indx % 2 == 0) dirC.add(num);
                    else {
                        split.add(sp);
                        dirR.add(num);
                    }
                    numString = "";
                    indx = (indx + 1) % 2;
                }
            }
            dirR.add(Integer.parseInt(numString));
            split.add(sp);
            sp++;
            read();
        }

        SandPlay sandPlay = new SandPlay();
        int maxR = Collections.max( dirR );
        sandPlay.setLimitR(maxR+2);


        for (int i = 0 ; i < dirR.size( ) - 1 ; i++) {
            int sp1 = split.get( i );
            int sp2 = split.get( i + 1 );
            if( sp1 != sp2 ) i++;
            int r1 = dirR.get( i ) ;
            int c1 = dirC.get( i ) ;
            int r2 = dirR.get( i + 1 )  ;
            int c2 = dirC.get( i + 1 ) ;
            int diffR = Math.abs( r2 - r1 );
            int diffC = Math.abs( c2 - c1 );
            for( int j = 0 ; j < Math.max(diffR,diffC)+1 ; j++)
                if( diffR == 0 ) sandPlay.addRock( r1 , ( int ) ( c1 + Math.signum( c2 - c1 ) * j ) );
                else sandPlay.addRock( ( int ) ( r1 + Math.signum( r2 - r1 ) * j ), c1 );
        }
        return sandPlay;


    }


    private static void print(char [][] table){
        System.out.println();
        Arrays.stream(table).toList().forEach(System.out::println);

        System.out.println();
    }









    private static boolean isRange(int r, int c) {
        return  0 <= r && r < rleng &&
                0 <= c && c < cleng;
    }
    private static boolean play( char [][] table ){
        int r = rInit;
        int c = cInit;
        boolean isPut = false;
        while( !isPut ){
            if( !isRange(r + 1 , c ) ) return false;
            if( table[ r + 1 ][ c ] == '·' ){
                r++;
                continue;
            }
            if( !isRange(r + 1 ,c - 1 ) ) return false;
            if( table[ r + 1 ][ c -1 ] == '·' ){
                r++;
                c--;
                continue;
            }
            if( !isRange(r + 1 ,c + 1 ) ) return false;
            if( table[ r + 1 ][ c + 1 ] == '·' ){
                r++;
                c++;
                continue;
            }
            table[ r ][ c ] ='o';
            isPut = true;
        }
        return true;
    }
    private static boolean playClass( SandPlay sandPlay ) {
        if( sandPlay.isEmpty()) return false;
        boolean isPut = false;
        boolean isMoved = false;
        int [] point = sandPlay.getPath();
        while ( !isPut ) {
            point = sandPlay.getPath();

            if( !sandPlay.isRock( point[ 0 ] + 1 , point[ 1 ] ) ){
                sandPlay.add(point[ 0 ] + 1 ,point[ 1 ] );
                isMoved = true;
                continue;
            }
            if( !sandPlay.isRock( point[0] + 1 ,point[ 1 ] - 1 ) ){
                sandPlay.add(point[0] + 1 ,point[ 1 ] - 1);
                isMoved = true;
                continue;
            }
            if( !sandPlay.isRock( point[ 0 ] + 1 ,point[ 1 ] + 1 ) ){
                sandPlay.add(point[0] + 1 ,point[ 1 ] + 1);
                isMoved = true;
                continue;
            }
            isPut = true;
        }
        //System.out.println(Arrays.toString(point));
        sandPlay.addRock( point[0] , point[1] );
        sandPlay.remove();

        return true;
    }





    private static void  problem1(){

        char [][] table = init();
        int solv = 0;
        while (play(table)) solv++;
        print(table);
        System.out.println(solv);

    }
    private static void  problem2(){
        SandPlay play = initSandPlay();
        int solv = 0;
        while(playClass(play)) solv++;
        System.out.println(solv);
    }
    static int rInit = 0;
    static int cInit = 500;
    static int rleng ;
    static int cleng ;

    public static void main(String[] args){
        open(false, 14);
        problem2();

        //initSandPlay();
    }
}
