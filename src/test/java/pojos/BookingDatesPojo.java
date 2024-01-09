package pojos;

public class BookingDatesPojo {
    /*
    {
                            "checkin": "2018-01-01",
                            "checkout": "2018-01-01"
     */

    private String checkin;
    private String checkout;

    public BookingDatesPojo() { //constructor parametresiz oluştur

    }

    public BookingDatesPojo(String checkin, String checkout) { // constructor hepsini seçerek oluştur
        this.checkin = checkin;
        this.checkout = checkout;
    }

// bütün parametreler için getter setter
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // bütün parametreler için toString

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
