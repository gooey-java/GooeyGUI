package gooeygui;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * The main window of a GooeyGUI program. Components are placed in the window
 * using {@link #addComponent(Component)}.
 *
 * <p>The window's width and height describe the usable area inside the window,
 * not counting the title bar or borders, so a component at
 * {@code y = getHeight() - 30} with a height of 30 fits exactly at the bottom.
 * The window cannot be resized by the user, and closing it ends the program.</p>
 *
 * <pre>{@code
 * Window window = new Window("My App", 500, 400);
 * window.addComponent(new Label("Hello!", 20, 20, 100, 30));
 * }</pre>
 */
public class Window {
    private JFrame frame;
    private int width;
    private int height;

    /**
     * Constructs and shows a window centered on the screen.
     *
     * @param title the text shown in the window's title bar
     * @param width the width of the usable area inside the window, in pixels (at least 1)
     * @param height the height of the usable area inside the window, in pixels (at least 1)
     * @throws GooeyGUIException if {@code title} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public Window(String title, int width, int height) {
        Check.text(title, "a Window's title");
        Check.size(width, "width", "a Window");
        Check.size(height, "height", "a Window");
        this.width = width;
        this.height = height;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Size the inside of the window (the content pane) rather than the outer
        // frame, so the title bar and borders do not cover any components.
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Returns the underlying Swing frame. Package-private, so hidden from students.
     *
     * @return the Swing frame
     */
    JFrame getFrame() {
        return frame;
    }

    /**
     * Adds a component to the window at the component's (x, y) position.
     *
     * <p>This method accepts any kind of component, such as a {@link Button}
     * or a {@link Label}, because they are all subclasses of {@link Component}.</p>
     *
     * @param comp the component to add
     * @throws GooeyGUIException if {@code comp} is {@code null} or has already
     *                           been added to a Window, Panel, or TabbedPane
     */
    public void addComponent(Component comp) {
        Check.component(comp, "add a component to a Window");
        comp.attachTo(this);
        frame.add(comp.getSwingComponent());
        frame.revalidate(); // Recalculates the layout
        frame.repaint();    // Redraws the window
    }

    /**
     * Returns the text shown in the window's title bar.
     *
     * @return the window's title
     */
    public String getTitle() {
        return frame.getTitle();
    }

    /**
     * Sets the text shown in the window's title bar.
     *
     * @param title the new title
     * @throws GooeyGUIException if {@code title} is {@code null}
     */
    public void setTitle(String title) {
        Check.text(title, "a Window's title");
        frame.setTitle(title);
    }

    /**
     * Returns the width of the usable area inside the window.
     *
     * @return the width, in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the usable area inside the window.
     *
     * @return the height, in pixels
     */
    public int getHeight() {
        return height;
    }
}
