package gooeygui;

import java.util.ArrayList;

/**
 * The error GooeyGUI throws when a component is used incorrectly, such as
 * giving a button a negative width or selecting an index that does not exist.
 *
 * <p>Each message has two parts: <b>Problem</b> describes what went wrong, and
 * <b>How to fix</b> suggests what to change. GooeyGUI's own internal steps are
 * removed from the error's stack trace, so the first {@code at} line points to
 * the line in <em>your</em> code that caused the problem.</p>
 */
public class GooeyGUIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an error with a problem description and a hint. Only GooeyGUI
     * itself creates these errors.
     *
     * @param problem what went wrong
     * @param hint how to fix it, or {@code null} if there is no hint
     */
    GooeyGUIException(String problem, String hint) {
        super(buildMessage(problem, hint));
        setStackTrace(withoutLibraryFrames(getStackTrace()));
    }

    /**
     * Combines the problem and hint into one multi-line message.
     *
     * @param problem what went wrong
     * @param hint how to fix it, or {@code null}
     * @return the formatted message
     */
    private static String buildMessage(String problem, String hint) {
        String message = "\n  Problem:    " + problem;
        if (hint != null) {
            message += "\n  How to fix: " + hint;
        }
        return message + "\n";
    }

    /**
     * Removes stack trace lines that come from inside GooeyGUI, so the trace
     * starts at the student's code.
     *
     * @param frames the full stack trace
     * @return the stack trace without GooeyGUI lines, or the full trace if
     *         nothing would be left
     */
    private static StackTraceElement[] withoutLibraryFrames(StackTraceElement[] frames) {
        ArrayList<StackTraceElement> kept = new ArrayList<>();
        for (StackTraceElement frame : frames) {
            if (!frame.getClassName().startsWith("gooeygui.")) {
                kept.add(frame);
            }
        }
        if (kept.isEmpty()) {
            return frames;
        }
        return kept.toArray(new StackTraceElement[0]);
    }
}
