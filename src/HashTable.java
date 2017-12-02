import java.util.ArrayList;

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
    private ArrayList<Integer> occupiedIndecies;
    private Handle[] handlesArray; // The actual table with handles stored. 
    
    /**
     * The hash constructor creates the Array for the table as well as sets
     * the count for the size of the table. 
     */
    public HashTable(int size)
    {
        initialSize = size;
        handlesArray = new Handle[initialSize];
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
     * This method inserts a new handle in the hashtable.
     * @param string Name of artist or song title. 
     * @param handle Offset of element being inserted into the table.  
     * @return Index of element in table. 
     */
    public int insert(String string, Handle handle)
    {
        int slot = hash(string, currentTableSize);
        
        if (handlesArray[slot] == null)
        {
            this.handlesArray[slot] = handle;   // Add handle to table.
        }
        else
        {
            // TODO: Do probing here. 
        }
        slotsOccupied++;                        // Increment slot occupied. 
        occupiedIndecies.add(slot);             // Add index to occupied ind..
        
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
        int[] newTable = new int[currentTableSize * 2];
        
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
    public int hash(String s, int m)
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