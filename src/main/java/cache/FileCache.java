package cache;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
*File cache container class
 */
public class FileCache<Key,Value  extends Serializable> implements FileCacheInterface<Key,Value> {
    private final HashMap<Key,String> fileMap;
    private static final String DIR="java.io.tmpdir";
    private static final String TMPDIR=System.getProperty(DIR);
    private Integer initialCapacity;
    private static Logger LOGGER = Logger.getLogger(FileCache.class.getName());
    public FileCache(Integer initialCapacity) {
        if (initialCapacity<=0){
            //default size
            initialCapacity=16;
            LOGGER.warning("Use default filecache capacity");
        }
        this.initialCapacity=initialCapacity;
        this.fileMap = new HashMap<Key,String>(initialCapacity);
    }


    @Override
    public Value get(Key key) {
        if (fileMap.get(key)==null){
            LOGGER.info("FileCache key miss");
            return null;
        }
        String fileName = fileMap.get(key);
        Object object=null;
        try(FileInputStream fis=new FileInputStream(TMPDIR+fileName)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                try {
                    object = ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (object!=null){
                    return (Value) object;
                }else {
                    return null;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {

            String fileName=UUID.randomUUID().toString();
            File file = new File(TMPDIR+fileName);
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
                oos.writeObject(value);
                fileMap.put(key,fileName);
            } catch (FileNotFoundException e) {
                LOGGER.log (Level.SEVERE,e.getMessage(),e);
                e.printStackTrace();
            } catch (IOException e) {
                LOGGER.log (Level.SEVERE,e.getMessage(),e);
                e.printStackTrace();
            }
    }
    public Boolean isNotFull(){
        return  initialCapacity>fileMap.size();
    }

    @Override
    public void remove(Key key) {
       String fileName=fileMap.remove(key);
       if (fileName!=null){
           File file=new File(TMPDIR+fileName);
           try {
               Files.deleteIfExists(file.toPath());
           } catch (IOException e) {
               LOGGER.log (Level.SEVERE,e.getMessage(),e);
               e.printStackTrace();
           }

       }
    }

    @Override
    public void clear() {
        this.fileMap.clear();;
    }

    @Override
    public Integer getSize() {
        return this.fileMap.size();
    }

    @Override
    public Boolean containsKey(Key key) {
        return this.fileMap.containsKey(key);
    }


}
