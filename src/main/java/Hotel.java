import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

public class Hotel implements Serializable {

    private static final long serialVersionUID = 6465708055693810563L;
    List<Room> rooms;

    public boolean isRoomAvailable(int nr, LocalDate d) {

        Room x = rooms.get( nr );
        return x.isAvailable( d );
    }

    public boolean reserveRoom(int nr, LocalDate d, String name) {
        Room x = rooms.get( nr );
        return x.reserve( d, name );
    }

    public Hotel() {
        rooms = new ArrayList<Room>();

        for (int i = 0; i < 3; ++i) {
            rooms.add( new Room( i ) );
        }
    }

    public int getAvailableRoom(LocalDate d) {
        for (int i = 0; i < rooms.size(); ++i) {
            if (rooms.get( i ).isAvailable( d )) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return rooms + "";
    }

    public void save() {
        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream( "hotel.db" );
            ObjectOutputStream out = new ObjectOutputStream( file );

            // Method for serialization of object
            out.writeObject( this );

            out.close();
            file.close();

            System.out.println( "Object has been serialized" );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialization:
    public static Hotel load()
    {
        Hotel object1 = null;
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("hotel.db");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object1 = (Hotel)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(object1 == null) {
            object1 = new Hotel();
        }
        return object1;
    }

    public List<Reservation> getAllReservationsSortedByName() {
        //Pasul 1 obtinem o lista cu toate rezervarile tuturor camerelor

        List<Reservation> acumulator = new ArrayList<Reservation>(  );
        for(Room c : rooms) {
            List<Reservation> tmp = c.getAllReservations();
            acumulator.addAll( tmp );
        }
        //Pasul 2 le sortez
        acumulator.sort(new Reservation.ComparatorByName().reversed() );

        //Pasul 3 le returnez
        return acumulator;
    }
}
