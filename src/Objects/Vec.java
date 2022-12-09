package Objects;

public class Vector {

    private int x;
    private int y;

    public Vector(int [] v){
        this.x = v[0];
        this.y = v[0];
    }

    public static Vector nw (int [] v){
        return new Vector( v );
    }
    public int [] getV() {
        return new int [] { x , y };
    }
}
