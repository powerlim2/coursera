package java;

/**
 * Created by jlim on 8/3/14.
 */
public class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<Integer>( );

        for( int i = 0; i < 10; i++ )
            lst.add( i );
        for( int i = 20; i < 30; i++ )
            lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );

        java.util.Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )
        {
            itr.next( );
            itr.remove( );
            System.out.println( lst );
        }
    }
}