package Objects;

public class Vec {

    private int x;
    private int y;

    public Vec(int [] v){
        this.x = v[0];
        this.y = v[1];
    }

    public static Vec nw (int [] v){
        return new Vec( v );
    }
    public int [] getV() {
        return new int [] { x , y };
    }

    public Vec clone() throws CloneNotSupportedException {
        return new Vec(new int [] { x , y });
    };
}
