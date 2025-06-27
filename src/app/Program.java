package app;

import model.entitites.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) { //METODO MAIN PODE LANCAR ESSA EXCEÇÃO

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            int roomNumber = sc.nextInt();

            System.out.print("Check-in date (dd/MM/yyyy): ");
            Date checkinDate = sdf.parse(sc.next());// SEM O THROW ELE OBRIGA A TRATAR A EXCEÇÃO DO PARSE
            System.out.print("Check-out date (dd/MM/yyyy): ");
            Date checkoutDate = sdf.parse(sc.next());


            Reservation reservation = new Reservation(roomNumber, checkinDate, checkoutDate);
            System.out.print("\nReservation: " + reservation);

            System.out.print("\nEnter data to update the reservation: ");
            System.out.print("\nCheck-in date (dd/MM/yyyy): ");
            checkinDate = sdf.parse(sc.next());
            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkoutDate = sdf.parse(sc.next());

            reservation.updateDates(checkinDate, checkoutDate);
            System.out.print("\nReservation: " + reservation);
        }
        catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        catch (DomainException e) {
            System.out.println("Error in reservation: " + e.getMessage());
        }
        sc.close();
    }
}