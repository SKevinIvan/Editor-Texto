package Interfaces;

import Utils.TypeFile;
import java.util.List;

public interface Inode {

    public TypeFile getTypeFile();

    public List<Inode> getChilds();

    public Inode getChild();

    @Override
    public String toString();
}
