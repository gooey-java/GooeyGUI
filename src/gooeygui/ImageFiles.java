package gooeygui;

import java.awt.MediaTracker;
import java.io.File;
import javax.swing.ImageIcon;

/**
 * Package-private helper methods for loading and scaling image files, shared by
 * {@link Image} and {@link Button}. Not visible to students.
 */
final class ImageFiles {
    /** Prevents instantiation; all methods are static. */
    private ImageFiles() {
    }

    /**
     * Loads an image file at its original size, with friendly errors for common
     * mistakes.
     *
     * @param filePath the path to the image file, starting from the project folder
     * @param owner a description of what the image is for, such as {@code "an Image"}
     * @return the loaded image
     * @throws GooeyGUIException if the path is {@code null}, the file cannot be
     *                           found, or it is not a readable image
     */
    static ImageIcon load(String filePath, String owner) {
        if (filePath == null) {
            throw new GooeyGUIException("The image file path for " + owner + " was null.",
                    "Give the path as a String, such as \"images/cat.png\".");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            String hint = "Check the spelling (including capital letters and the extension, "
                    + "like .png or .jpg). Paths start from your project folder, not the src folder.";
            if (new File("src", filePath).exists()) {
                hint = "The file is inside your src folder. Either move it to your project "
                        + "folder or change the path to \"src/" + filePath + "\".";
            }
            throw new GooeyGUIException(
                    "Could not find the image file \"" + filePath + "\" for " + owner
                    + ". GooeyGUI looked here: " + file.getAbsolutePath(),
                    hint);
        }
        if (file.isDirectory()) {
            throw new GooeyGUIException(
                    "\"" + filePath + "\" is a folder, not an image file.",
                    "Add the image's file name to the end of the path, such as \""
                    + filePath + "/cat.png\".");
        }
        ImageIcon icon = new ImageIcon(filePath);
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            throw new GooeyGUIException(
                    "\"" + filePath + "\" exists, but Java could not read it as an image.",
                    "Use a .png, .jpg, or .gif file. Renaming a file's extension does not "
                    + "convert it; re-save or export it in one of those formats.");
        }
        return icon;
    }

    /**
     * Stretches an image to exactly the given size.
     *
     * @param icon the image to scale
     * @param width the new width, in pixels (at least 1)
     * @param height the new height, in pixels (at least 1)
     * @return the stretched image
     */
    static ImageIcon stretch(ImageIcon icon, int width, int height) {
        // Fully-qualified java.awt.Image prevents a naming conflict with GooeyGUI's Image class.
        java.awt.Image scaled = icon.getImage().getScaledInstance(
                Math.max(1, width), Math.max(1, height), java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /**
     * Shrinks or enlarges an image to be as large as possible within a box
     * without changing its shape (its aspect ratio).
     *
     * @param icon the image to scale
     * @param maxWidth the width of the box, in pixels
     * @param maxHeight the height of the box, in pixels
     * @return the scaled image
     */
    static ImageIcon fit(ImageIcon icon, int maxWidth, int maxHeight) {
        double scale = Math.min((double) maxWidth / icon.getIconWidth(),
                (double) maxHeight / icon.getIconHeight());
        int width = (int) Math.round(icon.getIconWidth() * scale);
        int height = (int) Math.round(icon.getIconHeight() * scale);
        return stretch(icon, width, height);
    }
}
