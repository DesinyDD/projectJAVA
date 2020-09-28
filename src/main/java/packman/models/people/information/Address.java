package packman.models.people.information;

public class Address {
    private String street;
    private String city;
    private String zip;

    /* Constructor */
    public Address(String street, String city, String zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    /* Setter */
    public void setStreet(String street) { this.street = street; }
    public void setCity(String city) { this.city = city; }
    public void setZip(String zip) { this.zip = zip; }

    /* Getter */
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getZip() { return zip; }

    @Override
    public String toString() {
        return street + ',' + city + ',' + zip;
    }
}
