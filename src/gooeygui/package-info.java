/**
 * GooeyGUI: simple, object-oriented windows, buttons, and other graphical
 * components for Java, built on top of Swing.
 *
 * <p>Add {@code import gooeygui.*;} to the top of your file, create a
 * {@link gooeygui.Window}, then create components and add them to it.
 * Every component's position is the (x, y) of its top-left corner, in pixels,
 * measured from the top-left of the window. Y increases downward.</p>
 *
 * <h2>Components at a glance</h2>
 *
 * <p><img src="doc-files/GooeyGUI_Components.png"
 *   alt="The GooeyGUI components: Button, CheckBox, RadioButton, TextField, Dropdown, TextArea, Slider, FileChooser, Label, Image, Panel and TabbedPane"
 *   style="max-width:100%; height:auto;" width="1120"></p>
 * 
 * <h2>Running your code when something happens</h2>
 *
 * <p>Methods such as {@code setOnClick} and {@code setOnChange} take the name
 * of a method to run, written as {@code this::methodName}. The method must be
 * {@code void} and take no parameters. Because it uses {@code this}, set up
 * your components in a constructor (or another non-static method), not in
 * {@code main}.</p>
 *
 * <pre>{@code
 * import gooeygui.*;
 *
 * public class CounterApp {
 *     private int count;
 *     private Label countLabel;
 *
 *     public CounterApp() {
 *         Window window = new Window("Counter", 300, 200);
 *         countLabel = new Label("Count: 0", 100, 40, 100, 30);
 *         Button addButton = new Button("Add 1", 100, 90, 100, 30, this::addOne);
 *         window.addComponent(countLabel);
 *         window.addComponent(addButton);
 *     }
 *
 *     public void addOne() {
 *         count++;
 *         countLabel.setText("Count: " + count);
 *     }
 *
 *     public static void main(String[] args) {
 *         new CounterApp();
 *     }
 * }
 * }</pre>
 *
 * <h2>Errors</h2>
 *
 * <p>When a component is used incorrectly, GooeyGUI throws a
 * {@link gooeygui.GooeyGUIException} that explains the problem and how to fix
 * it, and points to the line in your code that caused it. If your own method
 * crashes while running as a click or change action, GooeyGUI prints a short
 * message pointing to your code and the program keeps running.</p>
 */
package gooeygui;
