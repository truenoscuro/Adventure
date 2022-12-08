import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day8 {
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

    private static  boolean isView( int [] pos ,char tree) {
        char now  = gPos( pos );
        return  tree < now;
    }

    private static char gPos(int [] pos){
        return grid.get(pos[0]).charAt( pos[ 1 ] );
    }

    private static  int [] sum( int [] pos, int [] dir){
        return new int[] { pos[0] + dir[0] , pos[1] + dir[1] };
    }

    private static boolean isInterior( int [] pos ){
        int leng = grid.size() - 1 ;
        return  0 < pos[0]  && pos[0] < leng &&
                0 < pos[1]  && pos[1] < leng ;
    }
    private static boolean isRange( int [] pos ){
        int leng = grid.size() - 1 ;
        return  0 <= pos[0]  && pos[0] <= leng &&
                0 <= pos[1]  && pos[1] <= leng ;
    }

    private static void updateView( int [] pos){
        String row = view.get(pos[0]);
        row = row.substring(0,pos[1]) +'1'+ row.substring(pos[1]+1);
        view.set( pos[0] , row );
    }
    private static void calculateLine( int[] pos , int [] dir ){
        int leng = grid.size();
        char treeMax = gPos(pos);
        int [] mov = pos;
        for ( int i = 0; i < leng ; i++ ){
            if( isView( mov , treeMax ) ){
                treeMax = gPos(mov);
                updateView( mov );
            }
            mov = sum(mov,dir);
        }
    }
    private static void calculateView(){
        int leng = grid.size();
        for ( int i = 0; i < leng ; i++ ){
            calculateLine(new int[]{ i ,0 },dirs[0]);
            calculateLine(new int[]{ i ,leng - 1 },dirs[1]);
            calculateLine(new int[]{ 0 ,i },dirs[2]);
            calculateLine(new int[]{ leng -1 ,i },dirs[3]);
        }
    }
    private static int sumView(){
        int total = 0;
        int leng = view.size();
        for ( String row : view ) {
            for ( int j = 0; j < leng; j++ ) {
                if (row.charAt(j) > '0') total++;
            }
        }
        return total;
    }


    private static int scoreDir(int [] pos, int [] dir){
        char tree = gPos( pos );
        int total = 0;
        int [] mov = sum( pos , dir );
        while( isRange( mov ) ){
            total++;
            if( tree <= gPos( mov ) ) break;
            mov = sum( mov , dir );
        }

      return total;
    };

    private static int scenicScore( int [] pos ){
        return  scoreDir( pos , dirs[0] ) *
                scoreDir( pos , dirs[1] ) *
                scoreDir( pos , dirs[2] ) *
                scoreDir( pos , dirs[3] );
    }
    private static  int maxScore(){
        int max = scenicScore(new int [] {1,1});
        int score;
        int leng = grid.size();
        int [] pos;
        for( int i = 1; i < leng-1 ; i++ ){
            for (int j = 1; j < leng-1 ; j++){
                pos = new int [] { i , j };
                if( gPos(pos) < '1' ) continue;
                score = scenicScore( pos );
                if( score > max ) max = score;
            }
        }
        return max;
    }

    private static void initGrid(){
        grid = new ArrayList<>();
        open(false,8);
        // init grid
        while (rd != null){
            grid.add(rd);
            read();
        }
    };

    private static void initView(){
        view = new ArrayList<>();
        int leng = grid.size();
        String v ;
        for ( int i = 0; i < leng ; i++ ){
            v = "";
            for ( int j = 0; j < leng ; j++ )
                if( isInterior(new int[]{ i , j } ) ) v = v + "0";
                else v = v +"1" ;
            view.add( v );
        }
    }




    private  static ArrayList<String> grid;

    private static ArrayList<String> view;
    private static int [][]dirs = {
            {0,1}, // -->
            {0,-1},
            {1,0},   // v
            {-1,0}
    };
    public static void main(String[] args) {
        initGrid();
        initView();
        calculateView();
        // Part 1
        System.out.println("Num views--> "+ sumView() );
        // Part 2
        System.out.println("Max score -->" + maxScore());
    }
}
