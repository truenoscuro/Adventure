package Objects;

import java.util.ArrayList;

public class SetInteger {
    ArrayList<Integer> list = new ArrayList<>();


    public void add( int i ){
        if(list.stream().anyMatch( e -> e == i )) return;
        list.add( i ) ;
    }
    public int get(int i){
        return list.get( i );
    }
    public int size( ){
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
