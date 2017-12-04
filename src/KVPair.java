/**
 * This class will hold two handles, one for the song name, and one for the
 * artist name. KVPairs will be inserted in the BST trees
 * 
 * @author Brady Engleman and Alex Bochel
 * @version 12/2/2017
 */
public class KVPair implements Comparable<KVPair>
{
    private Handle key;
    private Handle value;


    /**
     * The constructor for the KVPair class. It will take in two handles to
     * store in the fields.
     * 
     * @param newKey
     * @param newValue
     */
    public KVPair(Handle newKey, Handle newValue)
    {
        key = newKey;
        value = newValue;
    }


    /**
     * Getter method for the key field
     * 
     * @return the key as a Handle
     */
    public Handle getKey()
    {
        return key;
    }


    /**
     * Getter method for the value field
     * 
     * @return the value as a Handle
     */
    public Handle getValue()
    {
        return value;
    }


    /**
     * Method to compare two KVPair objects. It will call the compareTo method
     * of the key Handles. If they are the same, then the method will compare
     * the value Handles
     * 
     * @return an int that will tell the BST which KVPair is "larger" than the
     *         other
     */
    public int compareTo(KVPair pair2)
    {
        if (this.getKey().compareTo(pair2.getKey()) != 0)
        {
            return this.getKey().compareTo(pair2.getKey());
        }

        return this.getValue().compareTo(pair2.getValue());
    }
}
