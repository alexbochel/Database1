/**
 * This class will hold two handles, one for the song name, and one for the
 * artist name. KVPairs will be inserted in the BST trees
 * 
 * @author Brady Engleman and Alex Bochel
 * @version 12/2/2017
 */
public class KVPair
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
}
