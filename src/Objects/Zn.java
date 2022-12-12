package Objects;

import java.util.ArrayList;

public class Zn {
    ArrayList<Num> tuples;
    ArrayList< Integer > primers;

    int zn;
    public Zn (ArrayList< Integer > primers){
        this.primers = primers;
        this.tuples = new ArrayList<>();
        zn = 1;
        for( int p : primers ) zn *= p;

        for( int i = 0; i < zn ; i++ ){
            ArrayList<Integer> tupla = new ArrayList<>();
            for(int p : primers) tupla.add(i % p);
            tuples.add( new Num( tupla ) );
        }
    }
    public int get(int indxZn, int primer) {
        int key = 0;
        for ( int p : primers ) {
            if ( p == primer ) break;
            key++;
        }
        return tuples.get( indxZn ).get( key );
    }

    public int getZn() { return zn; }
}
