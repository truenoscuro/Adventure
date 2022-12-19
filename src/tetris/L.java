package tetris;

public class L  extends Piece{
    protected L( Point point ){
        leftPoint = point;
        downPoint = point;
        for( int i = 0 ; i < 3;i++){
            cubs.add( point );
            point = point.move( new int [] { 0 ,1 } );
        }
        rightPoint = point;
        for( int i = 0; i < 2; i++){
            point = point.move( new int [] { 1 , 0 } );
            cubs.add( point );
        }
    }

}
