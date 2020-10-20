package packman.models.mails.details;

public class Size3D implements Size {
    private double length;
    private double width;
    private double height;

    /* Constructor */
    public Size3D(double length, double width, double height) {
        this.length = length;
        this.width  = width;
        this.height = height;
    }

    /* Setter */
    public void setLength(double length) { this.length = length; }
    public void setWidth(double width) { this.width = width; }
    public void setHeight(double height) { this.height = height; }

    /* Getter */
    public double getLength() { return length; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    @Override
    public double getVolume() { return length * width * height; }

    private double sumMeasure() { return length + width + height; }

    @Override
    public String getSize() {
        /* REFERENCE */ /* https://www.aladinplaza.com/pageconfig/viewcontent/viewcontent1.asp?pageid=3107&directory=15156&contents=50732 */
        if      (sumMeasure() <= 60)  { return "S"; }
        else if (sumMeasure() <= 90)  { return "M"; }
        else if (sumMeasure() <= 120) { return "L"; }
        else if (sumMeasure() <= 150) { return "XL"; }
        return "OverSize";
    }
}
