import java.util.ArrayList;

/**
 * This class includes the impmentation for the hashing data structure.
 * 
 * @author Alexander James Bochel
 * @version 12.2.2017
 */
public class HashTable
{
    private int                initialSize;
    private int                currentTableSize;
    private int                slotsOccupied;
    private ArrayList<Integer> occupiedIndecies;
    private Handle[]           handlesArray;
    private Handle             tombstone;
    private MemoryManager      memManager;
    private boolean            isSongTable = false;


    /**
     * The hash constructor creates the Array for the table as well as sets the
     * count for the size of the table.
     * 
     * @param size
     *            Initial Size of the hashtable.
     * @param manager
     *            Memory Manager from the main database class.
     * @param isSong
     *            Is the hastable a song or artist table?
     */
    public HashTable(int size, MemoryManager manager, boolean isSong)
    {
        memManager = manager;
        isSongTable = isSong;
        initialSize = size;
        occupiedIndecies = new ArrayList<Integer>();
        handlesArray = new Handle[initialSize];
        currentTableSize = initialSize;
        slotsOccupied = 0;
        tombstone = new Handle(-1);
    }


    /**
     * This method gets the size of the table at any given time.
     * 
     * @return Table size.
     */
    public int getCurrentTableSize()
    {
        return currentTableSize;
    }


    /**
     * This method gets the actual array table.
     * 
     * @return Table.
     */
    public Handle[] getTable()
    {
        return handlesArray;
    }


    /**
     * This method determines whether or not a given handle is a tombstone by
     * checking to see if it's value is -1.
     * 
     * @return Whether or not handle is tombstone.
     */
    public boolean isTombstone(Handle handle)
    {
        return handle.getOffset() == -1;
    }


    /**
     * This method returns the handle for a given string name/artist.
     * 
     * @param string
     *            Name/Artist being looked for.
     * @return Handle that correspons with the given name/artist.
     */
    public Handle getEntry(String string)
    {
        int handleSlot = find(string);
        return handlesArray[handleSlot];
    }


    /**
     * This method checks to see whether or not the given string exists in the
     * table.
     * 
     * @param handleString
     *            The string for the handle being looked for.
     * @return The slot of the item, or -1 if the item is not found.
     */
    private int find(String handleString)
    {
        int strSlot = -1;
        int homeSlot = hash(handleString, currentTableSize);
        int slotCount = homeSlot;
        int probeOffset = 1;

        while (handlesArray[slotCount] != null) // TODO: Might have to ensure
                                                // that we do not reach the end
                                                // of the array.
        {
            if (memManager.getItemString(handlesArray[slotCount].getOffset())
                .equals(handleString))
            {
                strSlot = slotCount;
                break;
            }
            else
            {
                slotCount = (int)(homeSlot + Math.pow(probeOffset, 2))
                    % this.currentTableSize;
                probeOffset++;
            }
        }
        return strSlot;
    }


    /**
     * This method inserts a new handle in the hashtable.
     * 
     * @param string
     *            Name of artist or song title.
     * @param handle
     *            Offset of element being inserted into the table.
     * @return Index of element in table.
     */
    public int insert(Handle handle)
    {
        // Handle string depends on type of table, song or artist.
        String handleString = memManager.getItemString(handle.getOffset());

        int homeSlot = hash(handleString, currentTableSize);
        int slotCount = homeSlot;
        int probeOffset = 1;

        if (find(handleString) == -1) // If doesn't already exist.
        {
            while (handlesArray[slotCount] != null && !isTombstone(
                handlesArray[slotCount]))
            {
                slotCount = (int)(homeSlot + Math.pow(probeOffset, 2))
                    % this.currentTableSize;
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

            if (!isSongTable)
            {
                System.out.println("|" + memManager.getItemString(handle
                    .getOffset()) + "| is added to the Artist database.");
            }
            else
            {
                System.out.println("|" + memManager.getItemString(handle
                    .getOffset()) + "| is added to the  Song database.");
            }
            return slotCount;
        }
        // duplicate
        else
        {
            if (!isSongTable)
            {
                System.out.println("|" + memManager.getItemString(handle
                    .getOffset())
                    + "| duplicates a record already in the Artist database.");
            }
            else
            {
                System.out.println("|" + memManager.getItemString(handle
                    .getOffset())
                    + "| duplicates a record already in the Song database.");
            }
            return -1;
        }
    }

    /**
     * This method "removes" a specified element from the hashtable by replacing
     * it with a tombstone.
     * 
     * @param strToDelete
     *            The artist/song that will be deleted from the table.
     * @return The slot from which the item was deleted.
     */
    public int delete(String strToDelete)
    {
        int homeSlot = hash(strToDelete, currentTableSize);
        int slotCount = homeSlot;
        int probeOffset = 1;

        // Probe until the correct string is found.
        while (!memManager.getItemString(handlesArray[slotCount].getOffset())
            .equals(strToDelete))
        {
            slotCount = (int)(homeSlot + Math.pow(probeOffset, 2)
                % this.currentTableSize);
            probeOffset++;
        }

        handlesArray[slotCount] = tombstone;
        slotsOccupied--;

        for (int i = 0; i < occupiedIndecies.size(); i++)
        {
            if (occupiedIndecies.get(i) == slotCount)
            {
                occupiedIndecies.remove(i);
            }
        }
        return slotCount;
    }


    /**
     * This method resizes he table and rehashes all of the entries once the
     * size is greater then 50% full.
     */
    private void expandTable()
    {
        // Initialize new array with twice the space.
        Handle[] temp = handlesArray;
        currentTableSize = currentTableSize * 2;
        handlesArray = new Handle[currentTableSize];
        ArrayList<Integer> oldIndecies = occupiedIndecies;
        occupiedIndecies.clear();
        // Get all occupied indecies and rehash them to the new table.
        for (int i = 0; i < oldIndecies.size(); i++)
        {
            Handle toInsert = temp[oldIndecies.get(i)];
            slotsOccupied--;
            insert(toInsert);
        }
    }


    /**
     * Hash function for Project 4. It uses the "sfold" method from the OpenDSA*
     * module on hash functions. Computes hash of string s given a table with
     * size m.
     * 
     * @param s
     *            String being hashed.
     * @param m
     *            Table size.
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


    /**
     * This method is used for the print artist/song maethod. It will print out
     * the elements followed by the offset of that element
     */
    public void listElements()
    {
        if (!isSongTable)
        {
            for (Handle h : handlesArray)
            {
                if (h != null)
                {
                    System.out.print("|");
                    System.out.print(memManager.getItemString(h.getOffset()));
                    System.out.println("| " + this.find(memManager
                        .getItemString(h.getOffset())));
                }
            }

            System.out.println("total artists: " + slotsOccupied);
        }
        else
        {
            for (Handle h : handlesArray)
            {
                if (h != null)
                {
                    System.out.print("|");
                    System.out.print(memManager.getItemString(h.getOffset()));
                    System.out.println("| " + this.find(memManager
                        .getItemString(h.getOffset())));
                }
            }

            System.out.println("total songs: " + slotsOccupied);
        }
    }


    /**
     * Returns a boolean that tells us whether this hash table contains the
     * element that we are looking for
     * 
     * @param element
     *            is the element in the table that we are looking for
     * @return true if it is in the table. False if it is not
     */
    public boolean containsElement(String element)
    {
        return (this.find(element) != -1);
    }
}
