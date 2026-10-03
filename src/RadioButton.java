import javax.swing.JRadioButton;

public class RadioButton extends Component {
    private JRadioButton jRadioButton;
    private Runnable changeAction;

    public RadioButton(String text, int x, int y, int width, int height) {
        super(new JRadioButton(text), x, y, width, height);
        this.jRadioButton = (JRadioButton) getSwingComponent();

        this.jRadioButton.addActionListener(e -> {
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
        return jRadioButton.isSelected();
    }

    public void setSelected(boolean selected) {
        jRadioButton.setSelected(selected);
    }

    public String getText() {
        return jRadioButton.getText();
    }

    public void setText(String text) {
        jRadioButton.setText(text);
    }

    public void setOnChange(Runnable changeAction) {
        this.changeAction = changeAction;
    }

}