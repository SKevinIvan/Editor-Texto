package Utils;

import Interfaces.Inode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author APOOD9272
 */
public class Node implements Inode {

    private TypeFile type;
    private String name;
    private List<Inode> childs;

    public Node() {
        childs = new ArrayList<>();
    }

    public Node(TypeFile type, String name) {
        this();
        this.type = type;
        this.name = name;
    }

    public void addChild(Node node) {
        childs.add(node);
    }

    public void addChilds(Node... node) {
        childs.addAll(Arrays.asList(node));
    }

    public void addChilds(List<Node> nodes) {
        childs.addAll(nodes);
    }

    @Override
    public TypeFile getTypeFile() {
        return this.type;
    }

    public void setType(TypeFile type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Inode> getChilds() {
        return this.childs;
    }

    @Override
    public Inode getChild() {
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }
    

    
}
