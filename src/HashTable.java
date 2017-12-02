/**
 * This class includes the impmentation for the hashing data structure. 
 * 
 * @author Alexander James Bochel
 * @version 12.2.2017
 *
 */
public class HashTable {

    private int initialSize; // Initial amount of table slots. 
    private int currentTableSize; // Current table size at any given time.
    private int slotsOccupied;   // Current amount of slots occupied. 
    private int[] handlesArray; // The actual table with handles stored. 
    
    /**
     * The hash constructor creates the Array for the table as well as sets
     * the count for the size of the table. 
     */
    public HashTable(int size)
    {
        initialSize = size;
        handlesArray = new int[initialSize];
        currentTableSize = initialSize;
        slotsOccupied = 0; 
    }
    
    /**
     * This method gets the size of the table at any given time. 
     * @return Table size. 
     */
    public int getCurrentTableSize()
    {
        return currentTableSize;
    }
    
    /**
     * This method inserts a new element in the hashtable. 
     * @return Index of element in table. 
     */
    public int insert()
    {
        // TODO: Actual inserting as well as quadtratic probing. 
        slotsOccupied++;
        
        // If the table is over 50% full, call resizeTable. 
        if (slotsOccupied > (currentTableSize / 2))
        {
            expandTable();
        }
        
        return -1;
    }
    
    // TODO: Do we need this?
    public int delete()
    {
        return -1;
    }
    
    /**
     * This method resizes he table and rehashes all of the entries once the
     * size is greater then 50% full. 
     */
    private void expandTable()
    {
        // Initialize new array with twice the space. 
        int[] newTable = new int[this.currentTableSize * 2];
        
    }
    
    /**
     * Hash function for Project 4. 
     * It uses the "sfold" method from the OpenDSA* module on hash functions.
     * Computes hash of string s given a table with size m.
     * 
     * @param s String being hashed. 
     * @param m Table size. 
     * @return TODO: ?? Spot in the table. 
     */
    public int h(String s, int m)
    {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++)
        {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++)
            {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++)
        {
            sum += c[k] * mult;
            mult *= 256;
        }
        return (int)(Math.abs(sum) % m);
    }
}
