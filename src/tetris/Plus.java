package tetris;

public class Plus extends Piece {
    protected Plus( Point point ){
        super();

        cubs.add( point );
        leftPoint = point;

        Point bf = point.move( new int [] { 0 ,1 } );

        cubs.add( bf );
        cubs.add( bf.move( new int [] { 0 , 1 } ) );

        rightPoint = bf.move( new int [] { 0 , 1 } );

        cubs.add( bf.move( new int [] { 1 , 0 } ) );
        cubs.add( bf.move( new int [] { -1 , 0 } ) );

        downPoint = bf.move( new int [] { -1 , 0 } );
    }
}
