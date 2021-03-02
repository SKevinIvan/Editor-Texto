package Utils;

import Interfaces.Inode;
import java.awt.Component;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 *
 * @author APOOD9272
 */
public class CustomJTree extends JScrollPane {

    private final DefaultMutableTreeNode nodeRoot = new DefaultMutableTreeNode("Proyectos");
    private final DefaultTreeModel treeModel = new DefaultTreeModel(nodeRoot);
    private final JTree tree = new JTree(treeModel);

    public CustomJTree() {
        tree.setCellRenderer(new DefaultTreeCellRenderer() {

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

                Object node = ((DefaultMutableTreeNode) value).getUserObject();
                if (node instanceof Inode) {
                    setIcon(((Inode) node).getTypeFile().getIcon());
                } else {
                    setIcon(null);
                }
                return this;
            }
        });
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);//true
        super.setViewportView(tree);
    }

    public void loadNewProyect(Inode newProject) {
        DefaultMutableTreeNode proyectoRoot = new DefaultMutableTreeNode(newProject);
        this.loadChilds(newProject.getChilds(), proyectoRoot);
        treeModel.insertNodeInto(proyectoRoot, nodeRoot, nodeRoot.getChildCount());
        treeModel.nodeStructureChanged((TreeNode) treeModel.getRoot());
    }

    private void loadChilds(List<Inode> child, DefaultMutableTreeNode nodeRoot) {
        for (Inode inode : child) {
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(inode);
            nodeRoot.add(newNode);
            loadChilds(inode.getChilds(), newNode);
        }
    }

    public DefaultMutableTreeNode getNodeRoot() {
        return nodeRoot;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

}
