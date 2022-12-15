package Objects;

import java.util.ArrayList;

public class Path {
    ArrayList< Vec > path;
    int heuristic = 0;

    public Path(){
        path = new ArrayList<>();
    }
    public Path( ArrayList<Vec> path ){
        this.path = path;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public void add(Vec v ){
        if( isVisited( v ) ) return;
        path.add( v );
    }
    public boolean isVisited( Vec v){
        for( Vec vec : path)
            if( vec.equals( v ) ) return true;
        return false;
    }

    public Vec current(){ return path.get( path.size()-1 );}
    public int dist(){ return ( path.size() -1  + heuristic ) ; };

    public Path clone(){
        ArrayList<Vec> pathClonet = new ArrayList<>();
        path.forEach( vec -> pathClonet.add( vec.clone() ) );
        return new Path( pathClonet );
    }
    public int compare(Path path1){
        int distPath = dist();
        int distPath2 = path1.dist( );
        int result = 1;
        if( distPath == distPath2 ) result = 0;
        if( distPath < distPath2 ) result = -1;
        return result;

    }
}
