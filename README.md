# GooeyGUI

GooeyGUI lets you build windows with buttons, text boxes, sliders, and more using simple, object-oriented Java. It is built on Java Swing but hides Swing's complexity. Part of the [Gooey Java](https://gooey-java.github.io/) libraries for AP Computer Science A.

**Website:** https://gooey-java.github.io/GooeyGUI/

**Docs:** https://gooey-java.github.io/GooeyGUI/docs/

**Download:** [GooeyGUI.jar](https://gooey-java.github.io/GooeyGUI/GooeyGUI.jar) and [GooeyGUI-sources.jar](https://gooey-java.github.io/GooeyGUI/GooeyGUI-sources.jar)

## Setting up a project

1. Open VS Code and type **Ctrl + Shift + P** to open the Command Palette. Then start typing "Create..." and select "Create Java Project". Select "No Build Tool", choose the folder that your projects live in, and then name your new project something.
2. Make a `lib` folder in your project and put `GooeyGUI.jar` and `GooeyGUI-sources.jar` in it.
3. For organization, put any images and data files in folders inside your project folder (for example `images/cat.png`), not inside `src`.
4. Import, write your code, and hit Run:

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

## Buttons with pictures

Give a `Button` an `Image` instead of (or along with) text. `new Image("path")` loads a picture without needing a position or size, since the button resizes it to fit:

```java
Image playPicture = new Image("images/play.png");
Button play = new Button(playPicture, 20, 20, 60, 60, this::play);

Button save = new Button("Save", new Image("images/disk.png"), 100, 20, 120, 40, this::save);
```

The picture fits inside the button without being stretched out of shape. Use `setImage(pausePicture)` to change it while the program runs.

## Components

| Class | What it is |
|---|---|
| `Window` | The main window. Add components to it with `addComponent`. |
| `Label` | Text the user can't edit. |
| `Button` | Runs a method when clicked. Can show text, a picture, or both. |
| `TextField` | One line of typed text. |
| `TextArea` | Many lines of text, with scrolling. |
| `CheckBox` | An on/off box. |
| `RadioButton` + `RadioGroup` | Pick one option from a group. |
| `Dropdown` | Pick one option from a menu. |
| `Slider` | Pick a whole number in a range. |
| `Image` | A picture from a .png, .jpg, or .gif file. Can be shown in a window or on a button. |
| `Panel` | Groups components so they can be hidden, shown, or disabled together. |
| `TabbedPane` | Tabs that each show a `Panel`. |
| `FileChooser` | A pop-up for picking a file (works with `Scanner`). |

The full documentation is at https://gooey-java.github.io/GooeyGUI/docs/.

## Errors

If you use a component incorrectly, GooeyGUI stops with a `GooeyGUIException` that explains the **Problem** and **How to fix** it. The first `at` line shows the line in *your* code that caused it.

If one of your own methods crashes while running as a button click (or other action), GooeyGUI prints a short message pointing to your code and the program keeps running.

## Name conflicts

Some GooeyGUI class names (`Window`, `Label`, `Image`, `Panel`, `Button`, `TextField`, `TextArea`, `Component`) are also used by `java.awt`. Don't write `import java.awt.*;` in the same file as `import gooeygui.*;`. If you need something from `java.awt`, import just that class, such as `import java.awt.Color;`.

## What's in this repository

| Path | What it is |
|---|---|
| `src/gooeygui/` | The library's source code |
| `GooeyGUI.jar` | The compiled library |
| `GooeyGUI-sources.jar` | The source code as a jar, for hover docs in VS Code |
| `site/` | The showcase page (the website's home page) |
| `build.sh`, `build.bat` | Build the jars, the Javadoc, and the website |
| `javadoc-options.txt` | Settings for the docs (title, footer, Java version) |
| `.github/workflows/pages.yml` | Publishes the website after every push |
| `LICENSE` | The MIT License |

## Building and publishing

You need a JDK 17 or newer. Run `./build.sh` (Mac/Linux) or `build.bat`
(Windows). That rebuilds `GooeyGUI.jar` and `GooeyGUI-sources.jar` in this folder
(compiled to run on Java 8 or newer) and builds the whole website in
`_site/`: the showcase page, the Javadoc in `_site/docs/`, and both jars. Open
`_site/index.html` to preview it. (`_site/` isn't committed; GitHub builds its own.)

Publishing is automatic: after every push to `main`, GitHub runs `build.sh` and
publishes `_site/` to https://gooey-java.github.io/GooeyGUI/. The **Actions** tab
on GitHub shows each run. This needs a one-time setting: **Settings > Pages >
Source: GitHub Actions**.

The showcase page is `site/index.html`. Its styles come from
`https://gooey-java.github.io/gooey.css` in the gooey-java.github.io repo, which
every Gooey Java page shares.

## License

GooeyGUI is released under the [MIT License](LICENSE): anyone may use, copy,
change, and share it, as long as the copyright notice and license are kept with it.
