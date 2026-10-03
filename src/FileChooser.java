import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A modal file picker popup dialog wrapping Java Swing's JFileChooser.
 * Used exclusively as a popup window for selecting, opening, or saving files.
 */
public class FileChooser {
    private JFileChooser jFileChooser;

    /**
     * Constructs a FileChooser popup dialog starting at the default system directory.
     */
    public FileChooser() {
        this.jFileChooser = new JFileChooser();
    }

    /**
     * Constructs a FileChooser popup dialog starting at a specific directory path.
     *
     * @param currentDirectoryPath The initial directory path to display (e.g., "." for project root).
     */
    public FileChooser(String currentDirectoryPath) {
        this.jFileChooser = new JFileChooser(currentDirectoryPath);
    }

    /**
     * Displays a modal "Open File" dialog window centered over a parent component.
     *
     * @param parent The parent component or window over which to center the dialog (can be null).
     * @return The selected java.io.File object, or null if the user canceled.
     */
    public File showOpenDialog(Component parent) {
        int result = jFileChooser.showOpenDialog(parent != null ? parent.getSwingComponent() : null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return jFileChooser.getSelectedFile();
        }
        return null;
    }

    /**
     * Displays a modal "Open File" dialog window centered on the screen.
     *
     * @return The selected java.io.File object, or null if the user canceled.
     */
    public File showOpenDialog() {
        return showOpenDialog(null);
    }

    /**
     * Displays a modal "Save File" dialog window centered over a parent component.
     *
     * @param parent The parent component or window over which to center the dialog (can be null).
     * @return The selected java.io.File object, or null if the user canceled.
     */
    public File showSaveDialog(Component parent) {
        int result = jFileChooser.showSaveDialog(parent != null ? parent.getSwingComponent() : null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return jFileChooser.getSelectedFile();
        }
        return null;
    }

    /**
     * Displays a modal "Save File" dialog window centered on the screen.
     *
     * @return The selected java.io.File object, or null if the user canceled.
     */
    public File showSaveDialog() {
        return showSaveDialog(null);
    }

    /**
     * Restricts selectable files using file extensions.
     *
     * @param description A text description of the file type (e.g., "Text Files (*.txt)").
     * @param extensions One or more allowed file extensions without leading dots (e.g., "txt", "csv").
     */
    public void setFilter(String description, String... extensions) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extensions);
        jFileChooser.setFileFilter(filter);
    }

    /**
     * Sets the working directory of the file chooser.
     *
     * @param path The directory path string.
     */
    public void setCurrentDirectory(String path) {
        jFileChooser.setCurrentDirectory(new File(path));
    }

    /**
     * Retrieves the last selected file.
     *
     * @return The selected java.io.File, or null if no file was selected.
     */
    public File getSelectedFile() {
        return jFileChooser.getSelectedFile();
    }
}