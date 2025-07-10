/*
 * Copyright(C) 2007-2013 National Institute of Informatics, All rights reserved.
 */
package app.reservation;

import java.util.Date;

import app.AppException;

/**
 * Form class for Reserve Room
 * 
 */
public class ReserveRoomForm {

	private ReserveRoomControl reserveRoomHandler = new ReserveRoomControl();

	private Date stayingDate;



	// 予約番号を覚える場所を追加
	private String reservationNumber;

	// 予約番号をセットするメソッド
	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	// 予約番号を取得するメソッド
	public String getReservationNumber() {
		return reservationNumber;
	}

	// 予約キャンセル用のメソッド
	public void cancelReservation() throws AppException {
		ReserveRoomControl control = getReserveRoomHandler();
		control.cancelReservation(reservationNumber);
	}



	private ReserveRoomControl getReserveRoomHandler() {
		return reserveRoomHandler;
	}

	public String submitReservation() throws AppException {
		ReserveRoomControl reserveRoomHandler = getReserveRoomHandler();
		return reserveRoomHandler.makeReservation(stayingDate);
	}

	public Date getStayingDate() {
		return stayingDate;
	}

	public void setStayingDate(Date stayingDate) {
		this.stayingDate = stayingDate;
	}

}
