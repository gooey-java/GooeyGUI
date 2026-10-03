import javax.swing.JCheckBox;

public class CheckBox extends Component {
    private JCheckBox jCheckBox;
    private Runnable changeAction;

    public CheckBox(String text, int x, int y, int width, int height) {
        super(new JCheckBox(text), x, y, width, height);
        this.jCheckBox = (JCheckBox) getSwingComponent();

        // Trigger callback whenever toggled
        this.jCheckBox.addActionListener(e -> {
            if (changeAction != null) {
                try {
                    changeAction.run();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public boolean isSelected() {
        return jCheckBox.isSelected();
    }

    public void setSelected(boolean selected) {
        jCheckBox.setSelected(selected);
    }

    public String getText() {
        return jCheckBox.getText();
    }

    public void setText(String text) {
        jCheckBox.setText(text);
    }

    public void setOnChange(Runnable changeAction) {
        this.changeAction = changeAction;
    }
}