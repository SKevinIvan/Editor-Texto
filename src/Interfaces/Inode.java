package Interfaces;

import Utils.TypeFile;
import java.util.List;

/**
 *
 * @author APOOD9272
 */
public interface Inode {
    public TypeFile getTypeFile();  
    public List<Inode> getChilds();
    public Inode getChild();
    @Override
    public String toString();
}
