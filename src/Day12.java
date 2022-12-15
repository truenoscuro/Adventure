import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Day12 {
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
    static void init(){
        open(false,12);
        int i = 0;
        int x = 0;
        while( rd != null ){
            for( int j = 0; j < rd.length(); j++ , i++ ){
                indxV.add( i );
                graf.add( i );
                vX.add( x );
                vY.add( j );
                int d = Integer.MAX_VALUE;
                char v = rd.charAt( j );
                if( v == charInit ){
                    d = 0;
                    v = ( charInit == 'S' )?'a':'z';
                    initPostion = i;
                }
                if ( v == charFinal ){
                    v = ( charFinal == 'E' )?'z':'a';
                    finalPostion = i;
                }
                dist.add( d );
                distClone.add( Integer.MAX_VALUE );
                value.add( v );
            }
            x++;
            read();
        }

    }

    static int min( ){
        int iMin = 0;
        int min = dist.get( indxV.get( 0 ) );
        for( int i = 0; i < indxV.size(); i++){
            if ( dist.get( indxV.get( i ) ) < min ) {
                min = dist.get(indxV.get(i));
                iMin = i;
            }
        }
        int solv = indxV.get( iMin );
        indxV.remove( iMin );
        return  solv;
    }

    static int dijkstrat (){

        while ( !indxV.isEmpty() ){
            int indx = min(  );
            //System.out.println(indxV.size() + " -- " +dist.get( indx )) ;
            if( value.get(indx) == 'a' ){
                return  dist.get( indx ) ;
            }
            for( int[] d : dir){

                int x = d[0] + vX.get(indx);
                int y = d[1] + vY.get(indx);

                boolean isRange = false;
                int indxNext = 0;
                for( int idx : graf ){
                    if( vX.get(idx) == x &&
                        vY.get( idx) == y ) {
                        indxNext =  idx;
                        isRange = true;
                        break;
                    }
                }
                if( !isRange ) continue;
                char v = value.get( indx );
                char vNext = value.get( indxNext);
                boolean isDown = v-vNext == 1;
                boolean isUp = v <= vNext;
                if( ( isDown || isUp ) &&
                        dist.get(indx) != Integer.MAX_VALUE && dist.get( indx ) + 1 < dist.get( indxNext ) ){
                    dist.set( indxNext , dist.get( indx ) + 1 );
                }
            }
        }
        System.out.println("NO ha entrat");
        return Integer.MAX_VALUE;
    }



    static int[][] dir = { { 1 , 0 } , { -1 , 0 } , { 0 , 1 } , { 0 , -1 } };

    static char charInit = 'E';
    static char charFinal = 'S';
    static int  finalPostion ;
    static int  initPostion ;
    static ArrayList < Character > value = new ArrayList<>();
    static ArrayList < Integer > vX = new ArrayList<>();
    static ArrayList < Integer > vY = new ArrayList<>();
    static ArrayList < Integer > indxV = new ArrayList<>();
    static ArrayList < Integer > graf = new ArrayList<>();
    static ArrayList < Integer > dist = new ArrayList<>();
    static ArrayList < Integer > distClone = new ArrayList<>();
    public static void main(String[] args) {
        init();
        System.out.println(dijkstrat());
    }

}
