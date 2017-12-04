/**
 * This class extends the BST and handles printing.
 * 
 * @author Alexander James Bochel Brady Engleman
 * @version 12.3.2017
 */
public class KVPairTree extends BinarySearchTree<KVPair>
{

    private MemoryManager memManager;
    private boolean       isArtistTree;


    /**
     * This constructor calls the BST constructor.
     */
    public KVPairTree(MemoryManager manager, boolean isArtistTree)
    {
        super();
        memManager = manager;

        // if isArtistTree is true, that means that artists are the keys and
        // songs are the value
        this.isArtistTree = isArtistTree;
    }


    /**
     * This method inserts a Handle into the tree.
     * 
     * @param handle
     *            The handle being inserted.
     */
    public boolean insert(KVPair pair)
    {
        if (super.find(pair) != null)
        {
            if (super.find(pair).getValue().getOffset() == pair.getValue()
                .getOffset())
            {
                // there is a duplicate in the tree already
                
                System.out.print("The KVPair (|");

                if (isArtistTree)
                {
                    System.out.print(memManager.getArtistString(pair.getKey()
                        .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getSongString(pair.getValue()
                        .getOffset()));
                    System.out.print("|),");
                }
                else
                {
                    System.out.print(memManager.getSongString(pair.getKey()
                        .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getArtistString(pair.getValue()
                        .getOffset()));
                    System.out.print("|),");
                }

                System.out.print(pair.getKey().getOffset() + "," + pair
                    .getValue().getOffset());

                System.out.print(" duplicates a record already in the tree.");
                
                return false;
            }
        }

        System.out.print("The KVPair (|");

        if (isArtistTree)
        {
            System.out.print(memManager.getArtistString(pair.getKey()
                .getOffset()));
            System.out.print("|,|");
            System.out.print(memManager.getSongString(pair.getValue()
                .getOffset()));
            System.out.print("|),");
        }
        else
        {
            System.out.print(memManager.getSongString(pair.getKey()
                .getOffset()));
            System.out.print("|,|");
            System.out.print(memManager.getArtistString(pair.getValue()
                .getOffset()));
            System.out.print("|),");
        }

        System.out.print(pair.getKey().getOffset() + "," + pair.getValue()
            .getOffset());

        System.out.print(" is added to the tree.");
        return super.insert(pair);
    }


    public void remove(String name)
    {
        if (isArtistTree)
        {
            
        }
        
    }


    public void list()
    {

    }


    public void delete()
    {

    }


    public void printTree()
    {

    }
}
