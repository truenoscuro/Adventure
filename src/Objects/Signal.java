package Objects;

import java.util.ArrayList;

public class Signal {
    ArrayList<Integer> signal;
    ArrayList<Signal> signalInterior;
    String rd;
    int pos;
    boolean isCreate = false;

    public Signal( String rd, int pos ){
        signal = new ArrayList<>();
        signalInterior = new ArrayList<>();
        this.rd = rd;
        this.pos = pos;
    }
    public Signal ( int num ){
        signalInterior = new ArrayList<>();
        signal = new ArrayList<>();
        signal.add(num);
        this.isCreate = true;
    }

    public boolean isNum ( char a ){
        return '0'<= a && a <= '9' ;
    }
    public int add( ){
    //
        pos++;
        String numString = "";
        char a = rd.charAt( pos );
        while( a != ']' ){
            if( isNum( a ) )  numString =  numString+ a;
            else if ( a == '[' ) {
                Signal s = new Signal( rd , pos );
                this.pos = s.add();
                signalInterior.add( s );
                signal.add( -1 );
            }else if ( numString.length() > 0 ) {
                signal.add(Integer.parseInt(numString));
                numString = "";
            }
            if( ++pos >= rd.length() )break;
            a = rd.charAt( pos );
        }
        if( numString.length() > 0 ) signal.add(Integer.parseInt(numString));
        return  pos;
    }

    public int get( int i ){
        return signal.get( i );
    }

    public Signal getSignal( int i ){
        return signalInterior.get( i );
    }

    public int size() {
        return signal.size();
    }
    public String getRd(){ return  rd; }


    public int compare( Signal s2 ){
        int solv  = 0;
        int i = 0;
        int min = Math.min( size() , s2.size() );
        int j1 = 0;
        int j2 = 0;
        while( i < min ) {
            int n1 = get( i );
            int n2 = s2.get( i );
            if ( n1 == -1 && n2 >= 0 ) {
                Signal saux2 = new Signal( n2 );
                solv =  getSignal( j2++ ).compare( saux2 );
            }
            else if ( n2 == -1 && n1 >= 0 ) {
                Signal saux1 = new Signal( n1 ) ;
                solv = saux1.compare( s2.getSignal( j1++ ) );
            }
            else if ( n1 == -1 && n2 == -1 )
                solv =  getSignal( j1++ ).compare( s2.getSignal( j2++ ) ) ;
            else if ( n1 > n2 ) return +1; // numbers
            else if ( n1 < n2 ) return -1;
            if ( solv != 0 ) return solv ;
            i++ ;
        }
        if( size()-1 == i && s2.size()-1 == i ) return 0 ; // continue
        if( size()-1 == i ) return +1; // correct
        return -1;
    }




    @Override
    public String toString() {
        String str = "[ ";
        int s = 0;
        for (int i : signal)
            if( i >= 0 ) str+= " "+i+" ";
            else str+= signalInterior.get( s++ ).toString();
        str+=" ]";
        return str;
    }
}
