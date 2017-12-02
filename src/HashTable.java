import java.util.ArrayList;

/**
 * This class includes the impmentation for the hashing data structure. 
 * 
 * @author Alexander James Bochel
 * @version 12.2.2017
 *
 */
public class HashTable {

    private int initialSize;  
    private int currentTableSize;
    private int slotsOccupied;     
    private ArrayList<Integer> occupiedIndecies;
    private Handle[] handlesArray;  
    private Handle tombstone; // Tombstone handle: Do not rehash these. 
    private MemoryManager memManager;
    private boolean isSongTable = false;
    
    /**
     * The hash constructor creates the Array for the table as well as sets
     * the count for the size of the table. 
     */
    public HashTable(int size, MemoryManager manager, boolean isSong)
    {
        memManager = manager; // TODO: Does this update as mem manager updates in database?
        isSongTable = isSong;
        initialSize = size;
        handlesArray = new Handle[initialSize];
        currentTableSize = initialSize;
        slotsOccupied = 0; 
        tombstone = new Handle(-1);
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
     * This method determines whether or not a given handle is a tombstone
     * by checking to see if it's value is -1. 
     * @return Whether or not handle is tombstone. 
     */
    public boolean isTombstone(Handle handle)
    {
        return handle.getOffset() == -1;
    }
    
    // TODO
    public Handle getEntry(String string)
    {
        Handle returnHandle = find(string);
        
        return tombstone; // TODO: Do this nigga. 
    }
     
    // TODO
    private Handle find(String handle)
    {
        return tombstone;
    }
    
    /**
     * This method inserts a new handle in the hashtable.
     * @param string Name of artist or song title. 
     * @param handle Offset of element being inserted into the table.  
     * @return Index of element in table. 
     */
    public int insert(Handle handle)
    {
        // Handle string depends on type of table, song or artist. 
        String handleString = isSongTable ? 
                memManager.getSongString(handle.getOffset()) :
                memManager.getArtistString(handle.getOffset());
                
        int homeSlot = hash(handleString, currentTableSize);
        int slotCount = homeSlot;
        int probeOffset = 1;
        
        if (find(handleString) != null)
        {
            while (handlesArray[slotCount] != null &&
                    !isTombstone(handlesArray[slotCount]))
            {
                slotCount = homeSlot + probeOffset ^ 2; // TODO: Math pow
                probeOffset++;
            }
            
            
            handlesArray[slotCount] = handle;
            slotsOccupied++;         
            occupiedIndecies.add(slotCount);
            
            // If the table is over 50% full, call resizeTable. 
            if (slotsOccupied > (currentTableSize / 2))
            {
                expandTable();
            }
        }        
        return slotCount;
    }
    
    // TODO: Do we need this? Yes. 
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
        Handle[] temp = handlesArray;
        handlesArray = new Handle[currentTableSize * 2];
        
        // Get all occupied indecies and rehash them to thenew table. 
        for (int i = 0; i < occupiedIndecies.size(); i++)
        {
            Handle toInsert = temp[occupiedIndecies.get(i)];
            insert(toInsert);
        }
    }
    
    /**
     * Hash function for Project 4. 
     * It uses the "sfold" method from the OpenDSA* module on hash functions.
     * Computes hash of string s given a table with size m.
     * 
     * @param s String being hashed. 
     * @param m Table size. 
     * @return int Home slot in the table.  
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