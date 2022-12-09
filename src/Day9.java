import Objects.Vec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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


    private static ArrayList<Vec> visitets = new ArrayList<>();
    private static Map< Character , Vec > dirs = new HashMap<>();
    private static ArrayList< Vec > snake = new ArrayList();

    private static void initSnake(){
        int [] init = {0,0};
        int leng = 10 ;
        for( int i = 0; i < leng ; i++ )  snake.add( new Vec( init ) );


    }

    private static  boolean isVisited( int [] point ) {
        return visitets.stream().anyMatch(vec -> {
            int[] v = vec.getV();
            return v[0] == point[0] && v[1] == point[1];
        });

    }
    private static void initDirs(){
        dirs.put('R', Vec.nw( new int [] { 0 , 1 } ) );
        dirs.put('L', Vec.nw( new int [] { 0 , -1 } ) );
        dirs.put('D', Vec.nw( new int [] { 1 , 0 } ) );
        dirs.put('U', Vec.nw( new int [] { -1 , 0 } ) );
    }

    private static int [] sum( int [] H, Vec dir ){
        int [] v = dir.getV();
        return new int [] { H[0] + v[0] , H[1] + v[1] };
    }
    private static boolean isNum(char a){
        return '0' <= a && a <= '9';
    }
    private static int calcNum(){
        String num = "";
        int i = rd.length()-1;
        char a = rd.charAt( i );
        while ( isNum( a ) ){
            num = a + num  ;
            a = rd.charAt( --i ) ;
        }

        return Integer.parseInt( num );
    }
    private static int dist( int [] T, int [] H ){
        return (int) Math.sqrt(
                (T[0]-H[0])*(T[0]-H[0]) +( T[1]-H[1])*( T[1]-H[1])
        );
    }
    private static int dist(  Vec Tvect ,Vec Hvect ){
        int [] T = Tvect.getV();
        int [] H = Hvect.getV();
        return (int) Math.sqrt(
                (T[0]-H[0])*(T[0]-H[0]) +( T[1]-H[1])*( T[1]-H[1])
        );
    }
    private static void updateSnake( int[] Hnew ) throws CloneNotSupportedException {
        int leng = snake.size();
        Vec snakeAnt = snake.get( 0 ).clone();
        snake.set( 0 , Vec.nw( Hnew ) ); // a 0 poses [1,0]
        Vec aux;
        Vec tail;

        for( int i = 1 ; i < leng ; i++) {
            if ( dist( snake.get( i ), snake.get( i - 1 ) ) <= 1 ) break;
            aux = snake.get( i ).clone();
            snake.set( i, snakeAnt ); // a la posicio i li poses se post
            snakeAnt = aux;
        }
        tail = snake.get( snake.size() - 1 ).clone();
        if( !isVisited( tail.getV() ) ) visitets.add( tail );
    }
    public static void main(String[] args) throws CloneNotSupportedException {

        open(true,9);
        // init dirs
        int numMov;
        Vec dir;
        initDirs();
        initSnake();
        visitets.add( Vec.nw( new int[] { 0 , 0 } ) );
        // se direccio
        while( rd != null ){
            dir = dirs.get( rd.charAt( 0 ) );
            numMov =  calcNum( );
            for ( int i = 0 ; i < numMov ; i++ ){
                updateSnake( sum( snake.get( 0 ).getV() , dir ) );
            }


            read();

        }
        System.out.println("Total T visitets --> " + visitets.size());
        for( Vec vec:visitets){
            System.out.println(Arrays.toString( vec.getV() ) );
        }
    }


}
