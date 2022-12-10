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
        int [] init = { 0 , 0 };
        int leng = 10 ;
        for( int i = 0; i < leng ; i++ )  snake.add( new Vec( init ) );
    }

    private static  boolean isVisited( Vec points ) {
        int[] point = points.getV();
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


    private static Vec  sum( Vec Hvec, Vec dir ){
        int [] v = dir.getV();
        int [] H = Hvec.getV();
        return  Vec.nw( new int [] { H[0] + v[0] , H[1] + v[1] });
    }

    private static boolean isNum(char a){
        return '0' <= a && a <= '9';
    }
    private static int calcNum(){
        StringBuilder num = new StringBuilder();
        int i = rd.length()-1;
        char a = rd.charAt( i );
        while ( isNum( a ) ){
            num.insert(0, a);
            a = rd.charAt( --i ) ;
        }
        return Integer.parseInt(num.toString());
    }






    private static Vec mov( Vec heat , Vec act ){
        int [] v2 = heat.getV();
        int [] v1 = act.getV();
        int dx = v2[0]-v1[0];
        int dy = v2[1]-v1[1];
        int d = ( int ) Math.sqrt( Math.pow( dx ,2) + Math.pow( dy ,2));
        int nx = dx/d;
        int ny = dy/d;
        if(dx == 0 || dy == 0) return  Vec.nw(new int [] {v1[0] + nx ,v1[1] + ny});
        if( nx == 0 ) nx = (int) Math.signum(dx);
        if( ny == 0 ) ny = (int) Math.signum(dy);
        return Vec.nw( new int [] { v1[0] + nx ,v1[1] + ny } );
    }


    private static boolean isClausure( Vec heat , Vec act ){
        int [] v1 = heat.getV();
        int [] v2= act.getV();
        int vX = Math.abs( v2[0] - v1[0] );
        int vY = Math.abs( v2[1] - v1[1] );
        return    vX <= 1 && vY <= 1;

    }
    private static void updateSnake( Vec dir ) throws CloneNotSupportedException {
        int leng = snake.size();
        Vec init = sum( snake.get(0) , dir );
        snake.set( 0 , init  ); // a 0 poses [1,0]
        for( int i = 1 ; i < leng ; i++) {
            if ( isClausure( snake.get( i ) , snake.get( i - 1 ) )  ) break;
            snake.set( i , mov(   snake.get( i - 1 ) , snake.get( i ) ) ); // a la posicio i li poses se post
        }
    }
    private static void problem1() throws CloneNotSupportedException {
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
                updateSnake( dir);
                Vec tail = snake.get( snake.size() - 1 );
                if( !isVisited( tail ) ) {
                    visitets.add( tail );
                }
            }
            read();
        }

        System.out.println("Total T visitets --> " + visitets.size());


    }
    public static void main(String[] args) throws CloneNotSupportedException {

        problem1();


    }





}
