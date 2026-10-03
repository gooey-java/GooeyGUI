import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class RadioGroup {
    private ButtonGroup buttonGroup;

    public RadioGroup() {
        this.buttonGroup = new ButtonGroup();
    }

    /**
     * Binds radio buttons together so only one can be selected at a time.
     */
    public void addRadioButton(RadioButton radioButton) {
        // Casts the general JComponent returned by getSwingComponent() to JRadioButton
        buttonGroup.add((JRadioButton) radioButton.getSwingComponent());
    }
}