import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class Reservation implements Serializable {

    private static final long serialVersionUID = 8016178674377707869L;
    LocalDate reservationDate;
    String clientName;
    int roomNumber;

    public Reservation(LocalDate reservationDate, String clientName, int roomNumber) {
        this.reservationDate = reservationDate;
        this.clientName = clientName;
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object x) {
        if (x instanceof Reservation) {
            Reservation tmp = (Reservation) x;
            return this.reservationDate.equals( tmp.reservationDate ) &&
                    this.roomNumber == tmp.roomNumber;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + reservationDate + clientName;
    }

    public static class ComparatorByName implements Comparator<Reservation> {

        @Override
        public int compare(Reservation o1, Reservation o2) {
            return o1.clientName.compareTo( o2.clientName );
        }
    }
}
