/*
 * Copyright(C) 2007-2013 National Institute of Informatics, All rights reserved.
 */
package app.checkout;

import java.util.Date;

import app.AppException;
import app.ManagerFactory;
import domain.payment.PaymentManager;
import domain.payment.PaymentException;
import domain.room.RoomManager;
import domain.room.RoomException;

/**
 * Control class for Check-out Customer
 * 
 */
public class CheckOutRoomControl {
	
	public void checkOut(String roomNumber) throws AppException {
		try {
			//Clear room
			/*
			 * Your code for clearing room by using domain.room.RoomManager
			 */
			// to get accommodation information

			/*
			 * Your code for consuming payment by using domain.payment.PaymentManager
			 */
			// to get payment information
			Date stayingDate = getRoomManager().getRoom(roomNumber).getStayingDate();
			if (stayingDate == null) {
				throw new RoomException(RoomException.CODE_ROOM_NOT_FULL);
                        }
			int amount = getPaymentManager().getPaymentAmount(stayingDate, roomNumber);
			System.out.println("Checkout fee is " + amount + " yen. Proceed with payment? (y/n)");
			java.util.Scanner scanner = new java.util.Scanner(System.in);
			String input = scanner.nextLine();
			if (!input.equalsIgnoreCase("y")) {
				throw new AppException("Checkout processs cancelled.");
                        }

                        getRoomManager().removeCustomer(roomNumber);
			getPaymentManager().consumePayment(stayingDate, roomNumber);

                        System.out.println("Payment completed. Thank you!");
			
		}
		catch (RoomException e) {
			AppException exception = new AppException("Failed to check-out", e);
			exception.getDetailMessages().add(e.getMessage());
			exception.getDetailMessages().addAll(e.getDetailMessages());
			throw exception;
		}
		catch (PaymentException e) {
			AppException exception = new AppException("Failed to check-out", e);
			exception.getDetailMessages().add(e.getMessage());
			exception.getDetailMessages().addAll(e.getDetailMessages());
			throw exception;
		}
	}

	private RoomManager getRoomManager() {
		return ManagerFactory.getInstance().getRoomManager();
	}

	private PaymentManager getPaymentManager() {
		return ManagerFactory.getInstance().getPaymentManager();
	}
}
