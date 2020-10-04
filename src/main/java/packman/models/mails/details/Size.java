package packman.models.mails.details;

public class Size {
    private double length;
    private double width;
    private double height;

    /* Constructor */
    public Size(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
    public Size(double length, double width) {
        this(length, width, -1);
    }

    /* Setter */
    public boolean setLength(double length) {
        if (length > 0) {
            this.length = length;
            return true;
        } else { return false; }
    }
    public boolean setWidth(double width) {
        if (width > 0) {
            this.width = width;
            return true;
        } else { return false; }
    }
    public boolean setHeight(double height) {
        if (height > 0) {
            this.height = height;
            return true;
        } else { return false; }
    }

    /* Getter */
    public double getLength() { return length; }
    public double getWidth() { return width; }
    public double getHeight() { if (height == -1) { return 0; } else { return height; } }

    public double getVolume() {
        if (height == -1) {
            return length * width;
        }
        return length * width * height;
    }
}
