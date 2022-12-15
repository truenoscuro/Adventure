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
    public Signal (ArrayList<Integer> signal, ArrayList<Signal> signalInterior){
        this.signal = signal;
        this.signalInterior = signalInterior;
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
    public int pop( ){
        int num = signal.get( 0 );
        signal.remove(0);
        return num;
    }

    public Signal getSignal( int i ){
        return signalInterior.get( i );
    }
    public Signal popSignal( ){
        Signal s =  signalInterior.get( 0 );
        signalInterior.remove(0);
        return s;
    }
    public void push( int num ){ signal.add(0,num); }
    public void pushSignal( Signal s ){ signalInterior.add(0,s); }

    public void copy(  ){
        Signal s = popSignal();
        int leng = s.size(); int lengSignal = s.sizeSignal();
        push(-2);
        for ( int i = leng - 1 ; i >= 0 ; i-- ) push( s.get( i ) );
        for ( int i = lengSignal - 1 ; i >= 0 ; i-- ) pushSignal( s.getSignal( i ) );

    }
    public boolean isEmpty (){ return  size() == 0; }
    public void addMinus2(){ signal.add(0,-2); }
    public int size() {
        return signal.size();
    }
    public int sizeSignal() { return signalInterior.size(); }
    public String getRd(){ return  rd ; }

    public int compare( Signal s ){
        /*
        -1 si this < s
        0 si 0 == s
        1 si this > s
         */
        Signal s1 = clona();
        //s1.addMinus2();
        Signal s2 = s.clona();
        //s2.addMinus2();
        return  s1.compareIterative( s2 );
    }
    public int compareIterative( Signal s2 ){
        int n1 = 1; int n2 = 1 ;
        int solv = 1;
        while( !isEmpty() && !s2.isEmpty() ){
            if ( n1 != -1 && n2 != -1) {
                n1 =  pop() ; // ( 1, 1 )
                n2 =  s2.pop();
            } else { // ( 1 , -1 ) || ( -1 , 1) || (-1 ,-1)
                if (n1 == -1) n1 = pop();
                if (n2 == -1) n2 = s2.pop();
            }
            // lengs
            if( n1 == -2 && n2 == -2   ) continue;
            if( n1 == -2 ) return -solv;
            if( n2 == -2 ) return  solv;
            // compator
            if( n1 == -1 && n2 == -1){
                copy();
                s2.copy();
            } else if( n1 == -1 ){
                    copy();
                    s2.addMinus2();
            }else if ( n2 == -1 ){
                    s2.copy();
                    addMinus2();
            }
            if ( n1 == -1  || n2 == -1 ) continue;
            if ( n1 < n2 ) return -solv;
            if ( n1 > n2 ) return  solv;
        }

        if( isEmpty() && s2.isEmpty() ) return 0;
        if( isEmpty() && !s2.isEmpty() ) return  -solv;
        return  solv;
    }
    public int compareRecursive( Signal s2 ){
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


    public Signal clona (){
        ArrayList<Integer> signalCloned = new ArrayList<>( signal );
        ArrayList<Signal> signalInterorClonet = new ArrayList<>();
        signalInterior.forEach( s -> signalInterorClonet.add( s.clona() ) );
        return new Signal( signalCloned , signalInterorClonet );
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
