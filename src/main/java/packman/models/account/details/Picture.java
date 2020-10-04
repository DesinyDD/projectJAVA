package packman.models.account.details;

import javafx.scene.image.Image;

public class Picture {
    private Image image;
    private String path;

    /* Constructor */
    public Picture(String url) {
        this.image = new Image(url);
        this.path = url;
    }

    /* Setter */
    public void setImage(Image image) { this.image = image; }
    public void setPath(String path) { this.path = path; }

    /* Getter */
    public Image getImage() { return image; }
    public String getPath() { return path; }
}
