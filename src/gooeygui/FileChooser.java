package gooeygui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A pop-up dialog that lets the user browse for a file to open or a location
 * to save a file. A {@code FileChooser} is not added to a {@link Window};
 * instead, call one of its "show" methods when you need a file. The program
 * pauses on that line until the user closes the dialog.
 *
 * <p>The returned {@link File} works directly with {@code Scanner}:</p>
 *
 * <pre>{@code
 * FileChooser chooser = new FileChooser(".");
 * chooser.setFilter("Text files", "txt", "csv");
 * File file = chooser.showOpenDialog(window);
 * if (file != null) {
 *     Scanner input = new Scanner(file);
 *     // read the file...
 * }
 * }</pre>
 */
public class FileChooser {
    private JFileChooser jFileChooser;

    /**
     * Constructs a file chooser that starts in the user's default folder
     * (usually their home or Documents folder).
     */
    public FileChooser() {
        this.jFileChooser = new JFileChooser();
    }

    /**
     * Constructs a file chooser that starts in the given folder.
     *
     * @param currentDirectoryPath the folder to start in; {@code "."} means the
     *                             project folder
     * @throws GooeyGUIException if the folder does not exist
     */
    public FileChooser(String currentDirectoryPath) {
        this.jFileChooser = new JFileChooser(checkFolder(currentDirectoryPath));
    }

    /**
     * Checks that a folder path exists and is a folder.
     *
     * @param path the folder path
     * @return a {@code File} for the folder
     * @throws GooeyGUIException if the path is {@code null}, missing, or not a folder
     */
    private static File checkFolder(String path) {
        if (path == null) {
            throw new GooeyGUIException("The folder path for a FileChooser was null.",
                    "Use \".\" for the project folder, or a path such as \"data\".");
        }
        File folder = new File(path);
        if (!folder.isDirectory()) {
            String problem = folder.exists()
                    ? "\"" + path + "\" is a file, not a folder."
                    : "Could not find the folder \"" + path + "\". GooeyGUI looked here: "
                      + folder.getAbsolutePath();
            throw new GooeyGUIException(problem,
                    "Give the path of a folder that exists. Paths start from your project "
                    + "folder; \".\" means the project folder itself.");
        }
        return folder;
    }

    /**
     * Shows a dialog and returns the chosen file.
     *
     * @param open {@code true} for an Open dialog, {@code false} for a Save dialog
     * @param parent the Swing component to center over, or {@code null}
     * @return the chosen file, or {@code null} if the user canceled
     */
    private File show(boolean open, java.awt.Component parent) {
        int result = open ? jFileChooser.showOpenDialog(parent)
                          : jFileChooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            return jFileChooser.getSelectedFile();
        }
        return null;
    }

    /**
     * Shows an "Open" dialog centered on the screen and waits until the user
     * chooses a file or cancels.
     *
     * @return the file the user chose, or {@code null} if the user canceled
     */
    public File showOpenDialog() {
        return show(true, null);
    }

    /**
     * Shows an "Open" dialog centered over a window and waits until the user
     * chooses a file or cancels.
     *
     * @param parent the window to center the dialog over
     * @return the file the user chose, or {@code null} if the user canceled
     */
    public File showOpenDialog(Window parent) {
        return show(true, parent == null ? null : parent.getFrame());
    }

    /**
     * Shows an "Open" dialog centered over a component and waits until the user
     * chooses a file or cancels.
     *
     * @param parent the component to center the dialog over
     * @return the file the user chose, or {@code null} if the user canceled
     */
    public File showOpenDialog(Component parent) {
        return show(true, parent == null ? null : parent.getSwingComponent());
    }

    /**
     * Shows a "Save" dialog centered on the screen and waits until the user
     * chooses a location and file name or cancels. The chosen file does not
     * need to exist yet.
     *
     * @return the file the user chose, or {@code null} if the user canceled
     */
    public File showSaveDialog() {
        return show(false, null);
    }

    /**
     * Shows a "Save" dialog centered over a window and waits until the user
     * chooses a location and file name or cancels.
     *
     * @param parent the window to center the dialog over
     * @return the file the user chose, or {@code null} if the user canceled
     */
    public File showSaveDialog(Window parent) {
        return show(false, parent == null ? null : parent.getFrame());
    }

    /**
     * Shows a "Save" dialog centered over a component and waits until the user
     * chooses a location and file name or cancels.
     *
     * @param parent the component to center the dialog over
     * @return the file the user chose, or {@code null} if the user canceled
     */
    public File showSaveDialog(Component parent) {
        return show(false, parent == null ? null : parent.getSwingComponent());
    }

    /**
     * Limits the dialog to showing only files with certain extensions.
     *
     * <pre>{@code
     * chooser.setFilter("Text files", "txt", "csv");
     * }</pre>
     *
     * @param description the description shown in the dialog, such as
     *                    {@code "Text files"}
     * @param extensions one or more allowed extensions, without the dot, such as
     *                   {@code "txt"}
     * @throws GooeyGUIException if no extensions are given, or an extension is
     *                           {@code null}, empty, or starts with a dot
     */
    public void setFilter(String description, String... extensions) {
        Check.text(description, "a FileChooser filter description");
        if (extensions == null || extensions.length == 0) {
            throw new GooeyGUIException("setFilter() needs at least one file extension.",
                    "Add the extensions after the description, such as "
                    + "setFilter(\"Text files\", \"txt\", \"csv\").");
        }
        for (String extension : extensions) {
            if (extension == null || extension.isEmpty()) {
                throw new GooeyGUIException("One of the extensions given to setFilter() was "
                        + (extension == null ? "null." : "empty."),
                        "Give each extension as a String, such as \"txt\".");
            }
            if (extension.startsWith(".")) {
                throw new GooeyGUIException(
                        "The extension \"" + extension + "\" starts with a dot.",
                        "Leave out the dot: use \"" + extension.substring(1) + "\" instead of \""
                        + extension + "\".");
            }
        }
        jFileChooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
    }

    /**
     * Sets the folder that the dialog will start in the next time it is shown.
     *
     * @param path the folder to start in; {@code "."} means the project folder
     * @throws GooeyGUIException if the folder does not exist
     */
    public void setCurrentDirectory(String path) {
        jFileChooser.setCurrentDirectory(checkFolder(path));
    }

    /**
     * Returns the file most recently chosen in this dialog.
     *
     * @return the most recently chosen file, or {@code null} if no file has been
     *         chosen
     */
    public File getSelectedFile() {
        return jFileChooser.getSelectedFile();
    }
}
