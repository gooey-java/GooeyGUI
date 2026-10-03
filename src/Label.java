import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Label extends Component {
    private JLabel jLabel;

    public Label(String text, int x, int y, int width, int height) {
        super(new JLabel(text), x, y, width, height);
        this.jLabel = (JLabel) getSwingComponent();
    }

    public String getText() {
        return jLabel.getText();
    }

    public void setText(String text) {
        jLabel.setText(text);
    }

    /**
     * Optional helper to set text alignment ("LEFT", "CENTER", "RIGHT").
     */
    public void setAlignment(String alignment) {
        if ("CENTER".equalsIgnoreCase(alignment)) {
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        } else if ("RIGHT".equalsIgnoreCase(alignment)) {
            jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        } else {
            jLabel.setHorizontalAlignment(SwingConstants.LEFT);
        }
    }
}