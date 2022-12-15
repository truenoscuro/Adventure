package Objects;

public class Vec {
    private Vec children ;
    private int x;
    private int y;

    private double dist ;
    private double h ;

    private char value;

    public Vec(int [] v){
        this.x = v[0];
        this.y = v[1];
        value = '0';
        h = 0 ;
        dist = 0;
    }
    public Vec(int x, int y){
        this( new int[]{x,y} );
    }
    public Vec(int x, int y,char value){
        this(x,y);
        this.value = value;
    }
    public Vec( int [] v  ,char value ){
        this( v );
        this.value = value;
    }
    public Vec ( int [] v , double dist ){
        this( v );
        this.dist = dist;
    }
    public Vec ( int [] v , double dist, double h ){
        this(v,dist);
        this.h = h;
    }
    public Vec(int [] v,char value, double dist, double h){
        this(v,dist,h);
        this.value = value;
    }

    public void setH(double h) {
        this.h = h;
    }
    public double getH(){return  h ;}
    public double getDist(){ return dist; } ;

    public char value(){
        return value;
    }
    public static Vec nw (int [] v){
        return new Vec( v );
    }
    public int [] getV() {
        return new int [] { x , y };
    }
    public void setDist(double dist){
        this.dist = dist;
    }
    public boolean equals(Vec vec){
        int [] v = vec.getV();
        return v[0] == x && v[1] == y;

    }
    public void setChildren(Vec children){
        this.children =children;
    }
    public Vec getChildren(){
        return this.children ;
    }
    public boolean equalsChilden(Vec vec){
        return equals(vec) && children.equals(vec.getChildren());
    }
    public boolean isChildren(Vec vec){
        return vec.getChildren().equalsChilden( children );
    }
    public int compare(Vec v){
        double distPath = getDist() +h;
        double distPath2 = v.getDist()+v.getH();
        int result = 1;
        if( distPath == distPath2 ) result = 0;
        if( distPath < distPath2 ) result = -1;
        return result;
    }

    public Vec clone() {
        return new Vec( new int [] {x , y} , value, dist, h );
    };
}
