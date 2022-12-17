package Objects;

public class Interval {
    int x ;
    int y ;

    public  Interval(int x, int y){
        this.x = x ;
        this.y = y ;
    }

    public Interval interseccio (Interval interval){
        int xI = interval.getX();
        int yI = interval.getY();
        return new Interval( Math.max(xI,x),Math.min(yI,y));
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public int comp(Interval interval){
        int xI = interval.getX();
        int solv = 1;
        if( x < xI ) solv = -1;
        if( x == xI ){
            int yI = interval.getY();
            if( y < yI) solv = -1;
            if(y == yI) solv = 0;
        }
        return solv;
    }


    @Override
    public String toString() {
        return "[ "+ x + " , " + y + " ] ";
    }
}
