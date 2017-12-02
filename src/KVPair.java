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


    public KVPair(Handle newKey, Handle newValue)
    {
        key = newKey;
        value = newValue;
    }


    public Handle getKey()
    {
        return key;
    }


    public Handle getValue()
    {
        return value;
    }
}
