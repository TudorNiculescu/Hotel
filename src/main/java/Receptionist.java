
import java.time.LocalDate;
import java.util.Scanner;

public class Receptionist {
// MENU class
    Hotel h;

    public Receptionist(Hotel h) {
        this.h = h;
    }

    private void showMenu() {
        System.out.println( "Choose key" );
        System.out.println( "1. To make a reservation" );
        System.out.println( "2. To show all the reservations of the hotel" );
        System.out.println( "3. To close the application" );
        System.out.println( "4. Show all reservations sorted by name" );
    }

    public void startWork() {

        Scanner sc = new Scanner( System.in );
        while (true) {
            showMenu();
            String option = sc.nextLine();
            switch (option) {
                case "1": // make a reservation
                    System.out.println( "Choose the reservation date (YYYY-mm-dd)" );
                    LocalDate d = LocalDate.parse( sc.nextLine() );

                    System.out.println( "Choose the name on the reservation" );
                    String name = sc.nextLine();

                    int nrCamera = h.getAvailableRoom( d );
                    if (nrCamera == -1) {
                        System.out.println( "Sorry, no rooms available at the requested date" );
                    } else {
                        h.reserveRoom( nrCamera, d, name );
                    }
                    break;

                case "2": // show all reservations
                    System.out.println( h );
                    break;

                case "3": // save and quit
                    h.save();
                    return;

                case "4": // show all reservations alphabetically sorted by name
                    System.out.println(h.getAllReservationsSortedByName());
                    break;
            }
        }
    }
}
