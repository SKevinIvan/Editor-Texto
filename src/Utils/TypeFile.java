package Utils;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Lee
 */
public enum TypeFile {
    CLASS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Class.png")), "Class"),
    PACKAGE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Package.png")), "Class"),
    PROYECT(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Project.png")), "Image"),
    COMPILED_CLASS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Configuration.png")), "Compiled class"),
    FOLDER_SOURCE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/FolderCodes.png")), "Folder source"),
    FOLDER_BUILD(new ImageIcon(TypeFile.class.getResource("/Resources/Img/FolderBuild.png")), "Folder compiled class"),
    Y(new ImageIcon(TypeFile.class.getResource("/Resources/Img/y.png")), "Folder compiled class"),
    WORD(new ImageIcon(TypeFile.class.getResource("/Resources/Img/word.png")), "Folder compiled class"),
    WEB(new ImageIcon(TypeFile.class.getResource("/Resources/Img/web.png")), "Folder compiled class"),
    VUEJS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/vuejs.png")), "Folder compiled class"),
    VIDEO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/video.png")), "Folder compiled class"),
    TXT(new ImageIcon(TypeFile.class.getResource("/Resources/Img/txt.png")), "Folder compiled class"),
    SQL(new ImageIcon(TypeFile.class.getResource("/Resources/Img/sql.png")), "Folder compiled class"),
    SKECTK(new ImageIcon(TypeFile.class.getResource("/Resources/Img/sketch.png")), "Folder compiled class"),
    S(new ImageIcon(TypeFile.class.getResource("/Resources/Img/s.png")), "Folder compiled class"),
    RASPBERRY(new ImageIcon(TypeFile.class.getResource("/Resources/Img/raspberry.png")), "Folder compiled class"),
    PYTHON(new ImageIcon(TypeFile.class.getResource("/Resources/Img/python.png")), "Folder compiled class"),
    PYSOURCE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/pysource.png")), "Folder compiled class"),
    POWERPOINT(new ImageIcon(TypeFile.class.getResource("/Resources/Img/powerpoint.png")), "Folder compiled class"),
    PL(new ImageIcon(TypeFile.class.getResource("/Resources/Img/pl.png")), "Folder compiled class"),
    PICTURE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/picture.png")), "Folder compiled class"),
    PHP(new ImageIcon(TypeFile.class.getResource("/Resources/Img/php.png")), "Folder compiled class"),
    PAINT(new ImageIcon(TypeFile.class.getResource("/Resources/Img/paint.png")), "Folder compiled class"),
    P(new ImageIcon(TypeFile.class.getResource("/Resources/Img/p.png")), "Folder compiled class"),
    O(new ImageIcon(TypeFile.class.getResource("/Resources/Img/o.png")), "Folder compiled class"),
    NUEVOFOLDER(new ImageIcon(TypeFile.class.getResource("/Resources/Img/nuevoFolder.png")), "Folder compiled class"),
    LOGOGITHUB(new ImageIcon(TypeFile.class.getResource("/Resources/Img/logoGitHub.png")), "Folder compiled class"),
    L(new ImageIcon(TypeFile.class.getResource("/Resources/Img/l.png")), "Folder compiled class"),
    JS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/js.png")), "Folder compiled class"),
    JAVA(new ImageIcon(TypeFile.class.getResource("/Resources/Img/java.png")), "Folder compiled class"),
    J(new ImageIcon(TypeFile.class.getResource("/Resources/Img/j.png")), "Folder compiled class"),
    HTML5(new ImageIcon(TypeFile.class.getResource("/Resources/Img/html5.png")), "Folder compiled class"),
    H(new ImageIcon(TypeFile.class.getResource("/Resources/Img/h.png")), "Folder compiled class"),
    GITHUB(new ImageIcon(TypeFile.class.getResource("/Resources/Img/github.png")), "Folder compiled class"),
    GITHUB2(new ImageIcon(TypeFile.class.getResource("/Resources/Img/github2.png")), "Folder compiled class"),
    FUENTE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/fuente.png")), "Folder compiled class"),
    FORM(new ImageIcon(TypeFile.class.getResource("/Resources/Img/form.png")), "Folder compiled class"),
    FOLDERWEB(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderWeb.png")), "Folder compiled class"),
    FOLDERVIDEO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderVideo.png")), "Folder compiled class"),
    FOLDERVERDE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderVerde.png")), "Folder compiled class"),
    FOLDERSONIDO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderSonido.png")), "Folder compiled class"),
    FOLDERROJO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderRojo.png")), "Folder compiled class"),
    FOLDERRAYO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderRayo.png")), "Folder compiled class"),
    FOLDERPUBLIC(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderPublic.png")), "Folder compiled class"),
    FOLDERMUSIC(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderMusic.png")), "Folder compiled class"),
    FOLDERIMPRESORA(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderImpresora.png")), "Folder compiled class"),
    FOLDERIMAGENES(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderImagenes.png")), "Folder compiled class"),
    FOLDERGRIS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderGris.png")), "Folder compiled class"),
    FOLDERFAVORITO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderFavorito.png")), "Folder compiled class"),
    FOLDERDOCUMENTOS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderDocumentos.png")), "Folder compiled class"),
    FOLDERCONFIG(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderConfig.png")), "Folder compiled class"),
    FOLDERBLOQUEADO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderBloqueado.png")), "Folder compiled class"),
    FOLDERAMARILLO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folderAmarillo.png")), "Folder compiled class"),
    FOLDER(new ImageIcon(TypeFile.class.getResource("/Resources/Img/folder.png")), "Folder compiled class"),
    F(new ImageIcon(TypeFile.class.getResource("/Resources/Img/f.png")), "Folder compiled class"),
    EXCEL(new ImageIcon(TypeFile.class.getResource("/Resources/Img/excel.png")), "Folder compiled class"),
    ESCRITORIO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/escritorio.png")), "Folder compiled class"),
    ELEFANT(new ImageIcon(TypeFile.class.getResource("/Resources/Img/elefant.png")), "Folder compiled class"),
    EDICION(new ImageIcon(TypeFile.class.getResource("/Resources/Img/edicion.png")), "Folder compiled class"),
    CSS3(new ImageIcon(TypeFile.class.getResource("/Resources/Img/css3.png")), "Folder compiled class"),
    CSS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/css.png")), "Folder compiled class"),
    CAFETAZA(new ImageIcon(TypeFile.class.getResource("/Resources/Img/cafeTaza.png")), "Folder compiled class"),
    C(new ImageIcon(TypeFile.class.getResource("/Resources/Img/c.png")), "Folder compiled class"),
    ARCHIVOSTEXTO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/archivotexto.png")), "Folder compiled class"),
    ARCHIVOSVACIO(new ImageIcon(TypeFile.class.getResource("/Resources/Img/archivoVacio.png")), "Folder compiled class"),
    ANGULAR(new ImageIcon(TypeFile.class.getResource("/Resources/Img/anngular.png")), "Folder compiled class"),
    ACROBOAD(new ImageIcon(TypeFile.class.getResource("/Resources/Img/acroread.png")), "Folder compiled class"),
    ADOBE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/acrobatadobe.png")), "Folder compiled class"),
    ACCESS(new ImageIcon(TypeFile.class.getResource("/Resources/Img/access.png")), "Folder compiled class"),
    FOLDERPAQUETES(new ImageIcon(TypeFile.class.getResource("/Resources/Img/FolderPaquetes.png")), "Folder compiled class"),
    IMAGEPNG(new ImageIcon(TypeFile.class.getResource("/Resources/Img/Image.png")), "Folder compiled class"),
    MUSIC(new ImageIcon(TypeFile.class.getResource("/Resources/Img/FolderBuild.png")), "Folder compiled class"),
    SOUND(new ImageIcon(TypeFile.class.getResource("/Resources/Img/FolderBuild.png")), "Folder compiled class"),
    IMAGE(new ImageIcon(TypeFile.class.getResource("/Resources/Img/FolderBuild.png")), "Folder compiled class")
    
    
    
    ;

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
