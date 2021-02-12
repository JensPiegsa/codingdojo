package advent2020.day05;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardingPassDecrypterTest {
	
	BoardingPassDecrypter decrypter = new BoardingPassDecrypter();
	
	@Test @DisplayName("decrypt row")
	void decryprRow() {
		// given
		String row = "FBFBBFF";
		
		// when
		int rowNumber = decrypter.decryptRow(row);
		
		// then
		then(rowNumber).isEqualTo(44);
	}

	@Test @DisplayName("decrypt row fast")
	void decryprRowFast() {
		// given
		String row = "FBFBBFF"; //127 -32 -8 -4 =

		// when
		int rowNumber = decrypter.fastDecryptRow(row);

		// then
		then(rowNumber).isEqualTo(44);
	}

	@Test @DisplayName("decrypt column fast")
	void decryptColumnFast() {
		// given
		String column = "RLL";
		
		// when
		int columnNumber = decrypter.fastDecryptColumn(column);
		
		// then
		then(columnNumber).isEqualTo(4);
	}
	
	@Test @DisplayName("decrypt seat id")
	void decryptSeatId() {
		// given
		String seat = "BFFFBBFRRR";
		
		// when
		int seatId = decrypter.decryptOneSeatId(seat);

		// then
		then(seatId).isEqualTo(567);
	}
	
	@Test @DisplayName("calculate highest seatId")
	void calculateHighestSeatId() {
		// given
		String seats = "BFFFBBFRRR\nFFFBBBFRRR\nBBFFBBFRLL";

		// when
		int seatId = decrypter.calculateHighestSeatId(seats);

		// then
		then(seatId).isEqualTo(820);
	}
	
	@Test @DisplayName("validation test with input.txt")
	void validationTestWithInputTxt() {
		// given
		String allSeats = contentOf(getClass().getResource("input.txt"));

		// when
		int seatId = decrypter.calculateHighestSeatId(allSeats);

		// then
		then(seatId).isEqualTo(987);
	}
	
	@Test @DisplayName("find empty seat (Part II)")
	void findEmptySeat() {
		// given
		String allSeats = contentOf(getClass().getResource("input.txt"));
		
		// when
		int seatId = decrypter.calculateEmptySeat(allSeats);
		
		// then
		then(seatId).isEqualTo(603);
	}
}