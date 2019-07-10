import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

public class Room implements Serializable {
    private static final long serialVersionUID = -3538539269938996738L;
    int roomNumber;
    List<Reservation> list;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        list = new ArrayList<Reservation>(  );
    }

    public boolean reserve(LocalDate d, String name) {
        Reservation x = new Reservation( d, name, roomNumber );
        if (list.contains( x )) {
            return false;
        }
        list.add( x );
        return true;
    }

    public boolean isAvailable(LocalDate d) {
        Reservation r = new Reservation( d, "", roomNumber );
        return list.contains( r ) == false;

    }

    public List<Reservation> getAllReservations() {
        return list;
    }

    @Override
    public String toString() {
        return "" + roomNumber + list;
    }
}
