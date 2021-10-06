import java.util.Random;

public class Project3 {
    
     // Simple insertion sort.
     // 'a' an array of Comparable items.

    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType [ ] a )
    {
        int j;

        for( int p = 1; p < a.length; p++ )
        {
            AnyType tmp = a[ p ];
            for( j = p; j > 0 && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }

    // Merge sort algorithm.
    // 'a' is an array of Comparable items.

    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort( AnyType [ ] a )
    {
        AnyType [ ] tmpArray = (AnyType[]) new Comparable[ a.length ];

        mergeSort( a, tmpArray, 0, a.length - 1 );
    }
    
    // Internal method that makes recursive calls.
    // 'a' is an array of Comparable items.
    // 'tmpArray' is an array to place the merged result.
    // 'left' is the left-most index of the sub array.
    // 'right' is the right-most index of the sub array.
    
    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort( AnyType [ ] a, AnyType [ ] tmpArray,
               int left, int right )
    {
        if( left < right )
        {
            int center = ( left + right ) / 2;
            mergeSort( a, tmpArray, left, center );
            mergeSort( a, tmpArray, center + 1, right );
            merge( a, tmpArray, left, center + 1, right );
        }
    }

     // Internal method that merges two sorted halves of a sub array.
     // 'a' is an array of Comparable items.
     // 'tmpArray' is an array to place the merged result.
     // 'leftPos' is the left-most index of the sub array.
     // 'rightPos' is the index of the start of the second half.
     // 'rightEnd' is the right-most index of the sub array.

    private static <AnyType extends Comparable<? super AnyType>>
    void merge( AnyType [ ] a, AnyType [ ] tmpArray, int leftPos, int rightPos, int rightEnd )
    {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 )
                tmpArray[ tmpPos++ ] = a[ leftPos++ ];
            else
                tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        while( leftPos <= leftEnd )    // Copy rest of first half
            tmpArray[ tmpPos++ ] = a[ leftPos++ ];

        while( rightPos <= rightEnd )  // Copy rest of second half
            tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        // Copy tmpArray back
        for( int i = 0; i < numElements; i++, rightEnd-- )
            a[ rightEnd ] = tmpArray[ rightEnd ];
    }

     // Quick sort algorithm.
     // 'a' is an array of Comparable items.

    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort( AnyType [ ] a )
    {
        quicksort( a, 0, a.length - 1 );
    }

    private static final int CUTOFF = 3;

    // Method to swap to elements in an array.
    // 'a' an array of objects.
    // 'index1' the index of the first object.
    // 'index2' the index of the second object.
    
    public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }
    
     // Return median of left, right, and center.
     // Order these and hide the pivot.
    
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3( AnyType [ ] a, int left, int right )
    {
        int center = ( left + right ) / 2;
        if( a[ center ].compareTo( a[ left ] ) < 0 )
            swapReferences( a, left, center );
        if( a[ right ].compareTo( a[ left ] ) < 0 )
            swapReferences( a, left, right );
        if( a[ right ].compareTo( a[ center ] ) < 0 )
            swapReferences( a, center, right );

            // Place pivot at position right - 1
        swapReferences( a, center, right - 1 );
        return a[ right - 1 ];
    }

     // Internal quick sort method that makes recursive calls.
     // Uses median-of-three partitioning and a cutoff of 10.
     // 'a' an array of Comparable items.
     // 'left' the left-most index of the sub array.
     // 'right' the right-most index of the sub array.
    
    private static <AnyType extends Comparable<? super AnyType>>
    void quicksort( AnyType [ ] a, int left, int right )
    {
        if( left + CUTOFF <= right )
        {
            AnyType pivot = median3( a, left, right );

                // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 ) { }
                while( a[ --j ].compareTo( pivot ) > 0 ) { }
                if( i < j )
                    swapReferences( a, i, j );
                else
                    break;
            }
            swapReferences( a, i, right - 1 );   // Restore pivot

            quicksort( a, left, i - 1 );    // Sort small elements
            quicksort( a, i + 1, right );   // Sort large elements
        }
        else  // Do an insertion sort on the sub array
            insertionSort( a, left, right );
    }
    
     // Internal insertion sort routine for sub arrays that is used by quick sort.
     // 'a' is an array of Comparable items.
     // 'left' is the left-most index of the sub array.
     // 'right' is the right-most index of the sub array.
    
    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType [ ] a, int left, int right )
    {
        for( int p = left + 1; p <= right; p++ )
        {
            AnyType tmp = a[ p ];
            int j;

            for( j = p; j > left && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }
    
    // Internal method for heapsort.
    // 'i' is the index of an item in the heap.
    // return the index of the left child.

    private static int leftChild( int i )
    {
        return 2 * i + 1;
    }
    
    // Internal method for heapsort that is used in deleteMax and buildHeap.
    // 'a' is an array of Comparable items.
    // index 'i' is the position from which to percolate down.
    // 'n' is the logical size of the binary heap.
    
    private static <AnyType extends Comparable<? super AnyType>>
    void percDown( AnyType [ ] a, int i, int n )
    {
        int child;
        AnyType tmp;

        for( tmp = a[ i ]; leftChild( i ) < n; i = child )
        {
            child = leftChild( i );
            if( child != n - 1 && a[ child ].compareTo( a[ child + 1 ] ) < 0 )
                child++;
            if( tmp.compareTo( a[ child ] ) < 0 )
                a[ i ] = a[ child ];
            else
                break;
        }
        a[ i ] = tmp;
    }
    
     // Standard heapsort.
     // 'a' an array of Comparable items.
        
    public static <AnyType extends Comparable<? super AnyType>>
    void heapsort( AnyType [ ] a )
    {
        for( int i = a.length / 2 - 1; i >= 0; i-- )  /* buildHeap */
            percDown( a, i, a.length );
        for( int i = a.length - 1; i > 0; i-- )
        {
            swapReferences( a, 0, i );                /* deleteMax */
            percDown( a, 0, i );
        }
    }
	
    public static void getTimingInfo( int n, int alg )
    {
        Integer [] test = new Integer[ n ];

        long startTime = System.currentTimeMillis( );
        long totalTime = 0;

        int i;

		for( i = 0; totalTime < 4000; i++ )
        {
            for( int j = 0; j < test.length; j++ )
                test[ j ] = rand.nextInt( 100 ) - 50;
            
            // Test each sorting algorithm
            switch( alg )
            {
              case 1:
            	insertionSort( test );
                break;
              case 2:
            	mergeSort( test );
                break;
              case 3:
            	quicksort( test );
                break;              
              case 4:
            	heapsort( test );
            	break;
            }
            totalTime = System.currentTimeMillis( ) - startTime;
        }
        System.out.println( String.format( "%12.6f", ( totalTime * 1000 / i ) / (double) 1000000 ) );
        
    } 
    private static Random rand = new Random( );
    
    public static void main( String [ ] args )
    {   
    int numHolder[] = {10000, 20000, 40000, 80000};
    	{
    // Get timing info
    		for( int n = 0; n < numHolder.length; n++ )
    		{
    			System.out.println( String.format( "N = %7d" , numHolder[n]) );
      
    			for( int alg = 1; alg <= 4; alg++ )
    			{
    				getTimingInfo( numHolder[n], alg );
    			}
    			System.out.println( );
    		}
    	}
    }
}
    
