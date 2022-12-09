package Objects;

public class Vec {

    private int x;
    private int y;

    public Vec(int [] v){
        this.x = v[0];
        this.y = v[0];
    }

    public static Vec nw (int [] v){
        return new Vec( v );
    }
    public int [] getV() {
        return new int [] { x , y };
    }
}
