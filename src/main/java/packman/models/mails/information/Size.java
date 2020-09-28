package packman.models.mails.information;

public class Size {
    private double length;
    private double width;
    private double height;

    /* Constructor */
    public Size(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }
    public Size(double length, double width) {
        setLength(length);
        setWidth(width);
        this.height = -1;
    }

    /* Setter */
    public boolean setLength(double length) {
        if (length > 0) {
            this.length = length;
            return true;
        }
        return false;
    }
    public boolean setWidth(double width) {
        if (width > 0) {
            this.width = width;
            return true;
        }
        return false;
    }
    public boolean setHeight(double height) {
        if (height > 0) {
            this.height = height;
            return true;
        }
        return false;
    }

    /* Getter */
    public double getLength() { return length; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    public double getVolume() {
        if (this.height == -1) {
            return this.length * this.height;
        }
        return this.length * this.height * this.height;
    }
}
