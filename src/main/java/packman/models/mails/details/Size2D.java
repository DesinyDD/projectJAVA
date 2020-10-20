package packman.models.mails.details;

public class Size2D implements Size {
    private double length;
    private double width;

    /* Constructor */
    public Size2D(double length, double width) {
        this.length = length;
        this.width = width;
    }

    /* Setter */
    public void setLength(double length) { this.length = length; }
    public void setWidth(double width) { this.width = width; }

    /* Getter */
    public double getLength() { return length; }
    public double getWidth() { return width; }

    @Override
    public double getVolume() { return length * width; }

    @Override
    public String getSize() { return String.format("%.2f x %.2f", length, width); }
}
