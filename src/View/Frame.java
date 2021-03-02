package View;

import Utils.CustomJTree;
import Utils.Node;
import Utils.TypeFile;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Lee
 */
public class Frame extends JFrame {

    /**
     * @param args the command line arguments
     */
    private final CustomJTree tree = new CustomJTree();

    public static void main(String[] args) {

        Frame f = new Frame();
        f.loadProyect();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public Frame() throws HeadlessException {
        Dimension size = new Dimension(500, 700);
        super.setSize(size);
        super.add(tree);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadProyect() {

        Node package1 = new Node(TypeFile.PACKAGE, "Interfaces");
        package1.addChild(new Node(TypeFile.CLASS, "Inode.jey"));

        Node package2 = new Node(TypeFile.PACKAGE, "Resources");
        Node package22 = new Node(TypeFile.PACKAGE, "Img");
        package22.addChild(new Node(TypeFile.IMAGE, "BuildPackage.png"));
        package22.addChild(new Node(TypeFile.IMAGE, "class.png"));
        package22.addChild(new Node(TypeFile.IMAGE, "CodesPackage.png"));
        package22.addChild(new Node(TypeFile.IMAGE, "Configuration.png"));
        package2.addChild(package22);

        Node package3 = new Node(TypeFile.PACKAGE, "Utils");
        package3.addChild(new Node(TypeFile.CLASS, "CustomJtree.jey"));
        package3.addChild(new Node(TypeFile.CLASS, "Node.jey"));
        package3.addChild(new Node(TypeFile.CLASS, "Project.jey"));
        package3.addChild(new Node(TypeFile.CLASS, "TypeFile.jey"));

        Node package4 = new Node(TypeFile.PACKAGE, "View");
        package4.addChild(new Node(TypeFile.CLASS, "Frame.jey"));

        Node packageSource = new Node(TypeFile.FOLDER_SOURCE, "Source Packages");
        packageSource.addChilds(package1, package2, package3, package4);

        Node packageBuild = new Node(TypeFile.FOLDER_BUILD, "output");
        packageBuild.addChild(new Node(TypeFile.COMPILED_CLASS, "Node.out"));

        Node project = new Node(TypeFile.PROYECT, "CustomJTre");
        project.addChilds(packageSource, packageBuild);
        tree.loadNewProyect(project);
    }
}
