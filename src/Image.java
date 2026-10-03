import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Image extends Component {
    private JLabel jLabel;
    private String currentPath;

    public Image(String filePath, int x, int y, int width, int height) {
        super(new JLabel(), x, y, width, height);
        this.jLabel = (JLabel) getSwingComponent();
        setImagePath(filePath);
    }

    /**
     * Loads an image from a file path and scales it to fit the component's width and height.
     */
    public void setImagePath(String filePath) {
        this.currentPath = filePath;
        try {
            ImageIcon originalIcon = new ImageIcon(filePath);
            
            // Fully-qualified java.awt.Image prevents naming conflicts with this Image class
            java.awt.Image rawImage = originalIcon.getImage();
            java.awt.Image scaledImage = rawImage.getScaledInstance(
                getSwingComponent().getWidth(), 
                getSwingComponent().getHeight(), 
                java.awt.Image.SCALE_SMOOTH
            );

            this.jLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Could not load image file: " + filePath);
            e.printStackTrace();
        }
    }

    public String getImagePath() {
        return currentPath;
    }
}