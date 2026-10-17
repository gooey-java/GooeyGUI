# GooeyGUI

GooeyGUI lets you build windows with buttons, text boxes, sliders, and more using simple, object-oriented Java. It is built on Java Swing but hides Swing's complexity.

## Setting up a project

1. Open VS Code and type **Ctrl + Shift + P** to open the Command Palette. Then start typing "Create..." and select "Create Java Project".
2. Select "No Build Tool", choose the folder that your projects live in, and then name your new project something like "GooeyGUI_Pizza".
3. Make a `lib` folder in your project and put `GooeyGUI.jar` and `GooeyGUI-sources.jar` in it.
4. In `.vscode/settings.json`, tell VS Code about both jars. The `sources` line is what makes the documentation pop up when you hover over GooeyGUI methods:

```json
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "bin",
    "java.project.referencedLibraries": {
        "include": ["lib/**/*.jar"],
        "sources": { "lib/GooeyGUI.jar": "lib/GooeyGUI-sources.jar" }
    }
}
```

3. Put images and data files in folders inside your project folder (for example `images/cat.png`), not inside `src`.

## Your first program

```java
import gooeygui.*;

public class CounterApp {
    private int count;
    private Label countLabel;

    public CounterApp() {
        Window window = new Window("Counter", 300, 200);
        countLabel = new Label("Count: 0", 100, 40, 100, 30);
        Button addButton = new Button("Add 1", 100, 90, 100, 30, this::addOne);
        window.addComponent(countLabel);
        window.addComponent(addButton);
    }

    public void addOne() {
        count++;
        countLabel.setText("Count: " + count);
    }

    public static void main(String[] args) {
        new CounterApp();
    }
}
```

Every component's position is the (x, y) of its top-left corner, in pixels, measured from the top-left of the window or panel it is in. **Y increases downward.**

## Running code when something happens

Methods like `setOnClick`, `setOnChange`, and `setOnSubmit` take a method to run, written as `this::methodName` (no parentheses). The method must be `void` with no parameters. Because it uses `this`, set up your components in a constructor, not in `main`.

## Components

| Class | What it is |
|---|---|
| `Window` | The main window. Add components to it with `addComponent`. |
| `Label` | Text the user can't edit. |
| `Button` | Runs a method when clicked. |
| `TextField` | One line of typed text. |
| `TextArea` | Many lines of text, with scrolling. |
| `CheckBox` | An on/off box. |
| `RadioButton` + `RadioGroup` | Pick one option from a group. |
| `Dropdown` | Pick one option from a menu. |
| `Slider` | Pick a whole number in a range. |
| `Image` | A picture from a .png, .jpg, or .gif file. |
| `Panel` | Groups components so they can be hidden, shown, or disabled together. |
| `TabbedPane` | Tabs that each show a `Panel`. |
| `FileChooser` | A pop-up for picking a file (works with `Scanner`). |

The full documentation is in the `docs` folder (open `docs/index.html`).

## Errors

If you use a component incorrectly, GooeyGUI stops with a `GooeyGUIException` that explains the **Problem** and **How to fix** it. The first `at` line shows the line in *your* code that caused it.

If one of your own methods crashes while running as a button click (or other action), GooeyGUI prints a short message pointing to your code and the program keeps running.

## Name conflicts

Some GooeyGUI class names (`Window`, `Label`, `Image`, `Panel`, `Button`, `TextField`, `TextArea`, `Component`) are also used by `java.awt`. Don't write `import java.awt.*;` in the same file as `import gooeygui.*;`. If you need something from `java.awt`, import just that class, such as `import java.awt.Color;`.

---

## For the teacher: building the jars

From the GooeyGUI project folder (Java 17 or newer):

```
javac --release 17 -d bin src/gooeygui/*.java
jar --create --file GooeyGUI.jar -C bin gooeygui
jar --create --file GooeyGUI-sources.jar -C src gooeygui
javadoc -d docs src/gooeygui/*.java
```
