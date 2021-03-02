package Utils;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Lee
 */
public enum TypeFile {
    PROYECT(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Project.png")), "Project"),
    PACKAGE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Package.png")), "Package"),
    CLASS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Class.png")), "Class"),
    IMAGE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Image.png")), "Image"),
    COMPILED_CLASS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Configuration.png")), "Compiled class"),
    FOLDER_SOURCE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/FolderCodes.png")), "Folder source"),
    FOLDER_BUILD(new ImageIcon(TypeFile.class.getResource("/Resources/Img/FolderBuild.png")), "Folder compiled class");

    private final Icon icon;
    private final String type;

    private TypeFile(Icon icon, String type) {
        this.icon = icon;
        this.type = type;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }

}
