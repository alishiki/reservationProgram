package model.entitites;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation() {
        super();
    }

    public Reservation(Integer roomNumber, Date checkin, Date checkout) throws DomainException {
        //PROGRAMAÇÃO DEFENSIVA
        if (!checkout.after(checkin)) { //CONTEMPLA: CHECKOUT ANTES OU IGUAL AO CHECKIN
            throw new DomainException("\nCheck-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return checkin;
    }


    public Date getCheckout() {
        return checkout;
    }


    public long duration(){
        long differenceTime = checkout.getTime() - checkin.getTime(); // calcula diferença em milissegundos
        return TimeUnit.DAYS.convert(differenceTime, TimeUnit.MILLISECONDS); // converte milisec em dias
    }

    public void updateDates(Date checkin, Date checkout) throws DomainException { //METODO COM LANÇAMENTO DE EXCEÇÃO
        Date now = new Date();
        if(checkin.before(now) || checkout.before(now)) {
            throw new DomainException("Reservation dates for update must be future date");
        }
        if (!checkout.after(checkin)) { //CONTEMPLA: CHECKOUT ANTES OU IGUAL AO CHECKIN
            throw new DomainException("\nCheck-out date must be after check-in date");
        }
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkin)
                + ", check-out: "
                + sdf.format(checkout)
                + ", "
                + duration()
                + " nights.";
    }

}
