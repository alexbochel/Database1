import java.io.UnsupportedEncodingException;

/**
 * This class contains byte arrays as well as management tools for the 
 * artists and songs data. 
 * 
 * @author Alexander James Bochel
 * @version 12.2.2017
 *
 */
public class MemoryManager {
    private byte[] songNames;
    private byte[] artistNames;
    private int initialSize;
    private static final int OFFSET_LENGTH_ONE = 1;
    private static final int OFFSET_LENGTH_TWO = 2;
    private static final int OFFSET_STRING_BEGIN = 3;
    
    /**
     * This constructor sets the initial size of the byte arrays. 
     * @param size Initial Size of the byte arrays. 
     */
    public MemoryManager(int size)
    {
        initialSize = size;
        songNames = new byte[initialSize];
        artistNames = new byte[initialSize];
    }
    
    /**
     * This uses an offset to find a String representation of the song. 
     * @param offset Place in array for song. 
     * @return String Songs name. 
     */
    public String getSongString(int offset)
    {
        byte[] songByteLength = new byte[2];
        songByteLength[0] = songNames[offset + OFFSET_LENGTH_ONE]; 
        songByteLength[1] = songNames[offset + OFFSET_LENGTH_TWO];
        int length = bytesToInt(songByteLength);
        
        byte[] songStringBytes = new byte[length];
        System.arraycopy(songNames, offset + OFFSET_STRING_BEGIN,
                songStringBytes, 0, length);
        String songTitle = "";
        try 
        {
            songTitle = new String(songStringBytes, "ISO-8859-1");
        } 
        catch (UnsupportedEncodingException e) 
        {
            e.printStackTrace();
        }
        
        return songTitle;
    }
    
    /**
     * This uses an offset to find a String representation of the artist. 
     * @param offset Place in array for artist. 
     * @return String Artists name. 
     */
    public String getArtistString(int offset)
    {
        byte[] artistByteLength = new byte[2];
        artistByteLength[0] = artistNames[offset + OFFSET_LENGTH_ONE]; 
        artistByteLength[1] = artistNames[offset + OFFSET_LENGTH_TWO];
        int length = bytesToInt(artistByteLength);
        
        byte[] artistStringBytes = new byte[length];
        System.arraycopy(artistNames, offset + OFFSET_STRING_BEGIN,
                artistStringBytes, 0, length);
        String artistName = "";
        
        try 
        {
            artistName = new String(artistStringBytes, "ISO-8859-1");
        } 
        catch (UnsupportedEncodingException e) 
        {
            e.printStackTrace();
        }
        
        return artistName;
    }
    
    /**
     * This method converts bytes to an integer value. 
     * @param bytes Byte array to turn into int. 
     * @return int Representation of the byte array. 
     */
    private int bytesToInt(byte[] bytes)
    {
        int asInt = java.nio.ByteBuffer.wrap(bytes).getInt();
        return asInt; 
    }
}
