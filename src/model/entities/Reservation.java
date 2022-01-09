package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public Reservation() {
	}
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
		if(!checkOut.after(checkIn)){
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public long duration() {
		//converte a data em milisegundos
		long diff = checkOut.getTime() - checkIn.getTime();
		//pega os milisegundos e converte em dias usando a classe TimeUnit
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void uptadeDates(Date checkIn, Date checkOut) throws DomainException{
		
		//esse metodo está lançando a exception e quem vai tratar é o program principal
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		else if(!checkOut.after(checkIn)){
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	@Override
	public String toString() {
		StringBuilder sh = new StringBuilder();
		sh.append("Room ");
		sh.append(roomNumber );
		sh.append(", check-in: ");
		sh.append(sdf.format(checkIn));
		sh.append(", check-out: ");
		sh.append(sdf.format(checkOut));
		sh.append(", ");
		sh.append(duration()); 
		sh.append(" nigths");
		return sh.toString();
	}
	
}
