package app;

import model.entitites.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException { //MEU METODO MAIN PODE LANCAR ESSA EXCEÇÃO

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Room number: ");
        int roomNumber = sc.nextInt();

        System.out.print("Check-in date (dd/MM/yyyy): ");
        Date checkinDate = sdf.parse(sc.next());
        System.out.print("Check-out date (dd/MM/yyyy): ");
        Date checkoutDate = sdf.parse(sc.next());

        if (!checkoutDate.after(checkinDate)) { //VERY BAD SOLUTION
            System.out.println("\nError in reservation: Check-out date must be after check-in date");
        }else{
            Reservation reservation = new Reservation (roomNumber, checkinDate, checkoutDate);
            System.out.print("\nReservation: " + reservation);

            System.out.print("\nEnter data to update the reservation: ");
            System.out.print("\nCheck-in date (dd/MM/yyyy): ");
            checkinDate = sdf.parse(sc.next());
            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkoutDate = sdf.parse(sc.next());

            Date now = new Date();
            if(checkinDate.before(now) || checkoutDate.after(now)) {
                System.out.println("\nError in reservation: Reservation dates for update must be future date");
            }else if (!checkoutDate.after(checkinDate)){
                System.out.println("\nError in reservation: Check-out date must be after check-in date");
            }else {
                reservation.updateDates(checkinDate, checkoutDate);
                System.out.print("\nReservation: " + reservation);
            }
        }
        sc.close();
    }
}