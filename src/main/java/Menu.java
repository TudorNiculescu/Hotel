public class Menu {

    public static void main(String[] args) {
        Receptionist r = new Receptionist(Hotel.load());
        r.startWork();
    }
}
