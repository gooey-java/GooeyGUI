package gooeygui;

import java.util.ArrayList;

/**
 * Package-private helper methods that check arguments and produce friendly
 * {@link GooeyGUIException} messages. Not visible to students.
 */
final class Check {
    /** The most stack trace lines to print for an error inside an action. */
    private static final int MAX_FRAMES = 8;

    /** Prevents instantiation; all methods are static. */
    private Check() {
    }

    /**
     * Throws an error if a piece of text is {@code null}.
     *
     * @param text the text to check
     * @param owner a description of what the text is for, such as {@code "a Button"}
     * @return the same text, so this can be used inside a {@code super(...)} call
     * @throws GooeyGUIException if {@code text} is {@code null}
     */
    static String text(String text, String owner) {
        if (text == null) {
            throw new GooeyGUIException(
                    "The text for " + owner + " was null.",
                    "Give it a String such as \"Hello\" (use \"\" for no text). If you used a "
                    + "variable, make sure it was assigned a value before this line.");
        }
        return text;
    }

    /**
     * Throws an error if a width or height is less than 1.
     *
     * @param value the width or height to check
     * @param dimension {@code "width"} or {@code "height"}
     * @param owner a description of the component, such as {@code "Button \"OK\""}
     * @throws GooeyGUIException if {@code value} is less than 1
     */
    static void size(int value, String dimension, String owner) {
        if (value < 1) {
            throw new GooeyGUIException(
                    "The " + dimension + " of " + owner + " must be at least 1 pixel, but it was "
                    + value + ".",
                    "Check the " + dimension + " you gave. To hide a component, use "
                    + "setActive(false) instead of making it smaller.");
        }
    }

    /**
     * Throws an error if a component being added is {@code null}.
     *
     * @param component the component to check
     * @param action a description of the action, such as {@code "add a component to a Window"}
     * @throws GooeyGUIException if {@code component} is {@code null}
     */
    static void component(Object component, String action) {
        if (component == null) {
            throw new GooeyGUIException(
                    "You tried to " + action + ", but the component was null.",
                    "Make sure the component was created with new before this line. If it is "
                    + "an instance variable, check that the constructor assigns it "
                    + "(myButton = new Button(...);) and doesn't declare a new local variable "
                    + "with the same name (Button myButton = new Button(...);).");
        }
    }

    /**
     * Throws an error if an index is outside the valid range.
     *
     * @param index the index to check
     * @param count the number of items
     * @param owner a description of the component, such as {@code "Dropdown"}
     * @param itemWord the word for one item, such as {@code "item"} or {@code "tab"}
     * @throws GooeyGUIException if {@code index} is negative or at least {@code count}
     */
    static void index(int index, int count, String owner, String itemWord) {
        if (index >= 0 && index < count) {
            return;
        }
        if (count == 0) {
            throw new GooeyGUIException(
                    "Index " + index + " cannot be selected because this " + owner + " has no "
                    + itemWord + "s yet.",
                    "Add at least one " + itemWord + " before selecting one.");
        }
        throw new GooeyGUIException(
                "Index " + index + " is out of bounds for a " + owner + " with " + count + " "
                + itemWord + (count == 1 ? "" : "s") + ".",
                "Valid indexes are 0 to " + (count - 1) + ". Remember that the first "
                + itemWord + " is at index 0.");
    }

    /**
     * Runs a student's action. If the action throws an error, a short message
     * pointing to the student's code is printed instead of a long Swing stack
     * trace, and the program keeps running.
     *
     * @param action the action to run; nothing happens if it is {@code null}
     * @param what a description of the action, such as
     *             {@code "the click action for Button \"OK\""}
     */
    static void runAction(Runnable action, String what) {
        if (action == null) {
            return;
        }
        try {
            action.run();
        } catch (Throwable error) {
            System.err.println();
            System.err.println("GooeyGUI: Your code caused an error while running " + what + ".");
            System.err.println("  " + error);
            StackTraceElement[] frames = studentFrames(error.getStackTrace());
            int shown = Math.min(frames.length, MAX_FRAMES);
            for (int i = 0; i < shown; i++) {
                System.err.println("      at " + frames[i]);
            }
            if (frames.length > shown) {
                System.err.println("      ... (" + (frames.length - shown) + " more lines)");
            }
            System.err.println("  The program is still running, but that action did not finish.");
            System.err.println();
        }
    }

    /**
     * Keeps only stack trace lines from the student's own classes (not from
     * GooeyGUI or Java's built-in libraries).
     *
     * @param frames the full stack trace
     * @return the student's lines, or the full trace if there are none
     */
    private static StackTraceElement[] studentFrames(StackTraceElement[] frames) {
        String[] hiddenPrefixes = {"gooeygui.", "java.", "javax.", "jdk.", "sun.", "com.sun."};
        ArrayList<StackTraceElement> kept = new ArrayList<>();
        for (StackTraceElement frame : frames) {
            boolean hidden = false;
            for (String prefix : hiddenPrefixes) {
                if (frame.getClassName().startsWith(prefix)) {
                    hidden = true;
                }
            }
            if (!hidden) {
                kept.add(frame);
            }
        }
        if (kept.isEmpty()) {
            return frames;
        }
        return kept.toArray(new StackTraceElement[0]);
    }
}
