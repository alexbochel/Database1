import java.util.Iterator;

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


    public void removeArtist(String name)
    {
        Iterator<KVPair> iterator = this.iterator();

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (memManager.getArtistString(pair.getKey().getOffset())
                    .equals(name))
                {
                    System.out.print("The KVPair (|");

                    if (isArtistTree)
                    {
                        System.out.print(memManager.getArtistString(pair
                            .getKey().getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getSongString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }
                    else
                    {
                        System.out.print(memManager.getSongString(pair.getKey()
                            .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getArtistString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }

                    System.out.print(pair.getKey().getOffset() + "," + pair
                        .getValue().getOffset());

                    System.out.print(" is deleted from the tree.");
                    super.delete(pair);
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (memManager.getArtistString(pair.getValue().getOffset())
                    .equals(name))
                {
                    System.out.print("The KVPair (|");

                    if (isArtistTree)
                    {
                        System.out.print(memManager.getArtistString(pair
                            .getKey().getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getSongString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }
                    else
                    {
                        System.out.print(memManager.getSongString(pair.getKey()
                            .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getArtistString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }

                    System.out.print(pair.getKey().getOffset() + "," + pair
                        .getValue().getOffset());

                    System.out.print(" is deleted from the tree.");
                    super.delete(pair);
                }
            }
        }
    }


    public void removeName(String name)
    {
        Iterator<KVPair> iterator = this.iterator();

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (memManager.getArtistString(pair.getValue().getOffset())
                    .equals(name))
                {
                    System.out.print("The KVPair (|");

                    if (isArtistTree)
                    {
                        System.out.print(memManager.getArtistString(pair
                            .getKey().getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getSongString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }
                    else
                    {
                        System.out.print(memManager.getSongString(pair.getKey()
                            .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getArtistString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }

                    System.out.print(pair.getKey().getOffset() + "," + pair
                        .getValue().getOffset());

                    System.out.print(" is deleted from the tree.");
                    super.delete(pair);
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (memManager.getArtistString(pair.getKey().getOffset())
                    .equals(name))
                {
                    System.out.print("The KVPair (|");

                    if (isArtistTree)
                    {
                        System.out.print(memManager.getArtistString(pair
                            .getKey().getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getSongString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }
                    else
                    {
                        System.out.print(memManager.getSongString(pair.getKey()
                            .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getArtistString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }

                    System.out.print(pair.getKey().getOffset() + "," + pair
                        .getValue().getOffset());

                    System.out.print(" is deleted from the tree.");
                    super.delete(pair);
                }
            }
        }
    }


    public void listArtist(String artist)
    {
        Iterator<KVPair> iterator = this.iterator();

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (pair.getKey().equals(artist))
                {
                    // TODO figure out how to format list
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (pair.getValue().equals(artist))
                {
                    // TODO figure out how to format list
                }
            }
        }
    }


    public void listSong(String name)
    {
        Iterator<KVPair> iterator = this.iterator();

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (pair.getValue().equals(name))
                {
                    // TODO figure out how to format list
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (pair.getKey().equals(name))
                {
                    // TODO figure out how to format list
                }
            }
        }
    }


    public void delete(String artist, String name)
    {
        Iterator<KVPair> iterator = this.iterator();
        boolean found = false;

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // if artists are the same
                if (memManager.getArtistString(pair.getKey().getOffset())
                    .equals(artist))
                {
                    // if song name also matches
                    if (memManager.getSongString(pair.getValue().getOffset())
                        .equals(name))
                    {
                        found = true;
                        // TODO print it out
                        super.delete(pair);
                    }
                }
            }

            if (!found)
            {
                // TODO print out that it wasn't there
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // if artists are the same
                if (memManager.getArtistString(pair.getValue().getOffset())
                    .equals(artist))
                {
                    // if song name also matches
                    if (memManager.getSongString(pair.getKey().getOffset())
                        .equals(name))
                    {
                        found = true;
                        // TODO print it out
                        super.delete(pair);
                    }
                }
            }

            if (!found)
            {
                // TODO print out that it wasn't there
            }

        }
    }


    public void printTree()
    {
        
    }
}
