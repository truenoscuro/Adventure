package Objects;

import java.util.ArrayList;

public class Num {


    private ArrayList<Integer> num;
    public Num( ArrayList< Integer > num ){
        this.num = num;
    }
    public int get( int indx ){ return  num.get( indx ); }

}
