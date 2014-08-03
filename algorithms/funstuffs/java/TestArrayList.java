package java;

/**
 * Created by jlim on 8/3/14.
 */
public class TestArrayList
{
    public static void main( String [ ] args )
    {
        MyArrayList<Integer> lst = new MyArrayList<Integer>( );

        for( int i = 0; i < 10; i++ )
            lst.add( i );
        for( int i = 20; i < 30; i++ )
            lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );
    }
}
