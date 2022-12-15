package Objects;

import java.util.ArrayList;

public class SandPlay {




    // path
    ArrayList< Integer > pathR  = new ArrayList<>() ;
    ArrayList< Integer > pathC = new ArrayList<>() ;

    ArrayList< Integer > rockR = new ArrayList<>() ;
    ArrayList< Integer > rockC = new ArrayList<>() ;

    //initPoint
    int initR = 0;
    int initC = 500;
    int limitR;

    public SandPlay(){
        pathR.add(initR);
        pathC.add(initC);
    }

    public void setLimitR( int r ){
        System.out.println( r );
        limitR = r;
    }
    public void addRock( int r , int c ){
        rockR.add( r );
        rockC.add( c );
    }
    public void add( int r , int c ){
        pathR.add( r );
        pathC.add( c );
    }
    public int[] getPath(){
        int indx = pathR.size() - 1 ;
        return  new int[] { pathR.get( indx ) , pathC.get( indx )  };
    }
    public void remove(){
        int indx = pathR.size() - 1 ;
        pathR.remove( indx );
        pathC.remove( indx );
    }
    public boolean isEmpty() {
        return pathR.isEmpty();
    }

    public boolean isRock( int r, int c ){
        if( limitR <= r) return  true;
        for( int i = 0 ; i < rockC.size() ; i++ ){
            if( rockR.get( i ) == r && rockC.get( i ) == c ) return true;
        }
        return false;
    }


}
