package tetris;

import java.util.ArrayList;

public class Piece {
    protected ArrayList<Point> cubs;

    protected Point downPoint;
    protected Point leftPoint;
    protected Point rightPoint;
    protected Piece(){
        cubs = new ArrayList<>();
    }
    public static Piece generatePiece( int type , Point point ){
        return switch ( type ){
            case 0 -> new IDown(point);
            case 1 -> new Plus(point);
            case 2 -> new L( point );
            case 3 -> new I(point);
            default -> new O(point);
        };
    }


    public ArrayList<Point> getCubs() {
        return cubs;
    }

    public void down( ){
        cubs = new ArrayList<>(cubs.stream().map(cub -> cub.move( new int []{-1,0}) ).toList());
    }
    public void left( ){
        cubs = new ArrayList<>( cubs.stream().map(cub -> cub.move( new int []{0,-1}) ).toList());
    }
    public void right( ){
        cubs = new ArrayList<>( cubs.stream().map(cub -> cub.move( new int []{0,1}) ).toList() );
    }
    public boolean containPoint( Point point ){
        return cubs.stream().anyMatch( cub -> cub.equals( point ) );
    }









}
