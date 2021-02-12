package advent2020.day05;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.stream.Collectors;

public class BoardingPassDecrypter {

	public int calculateHighestSeatId(final String allSeats) {

		final List<String> seats = asList(allSeats.split("(\r\n|\n|\r)"));

		return seats.stream()
				.map(this::decryptOneSeatId)
				.max(Integer::compare)
				.orElse(-1);
	}

	int decryptOneSeatId(final String seat) {
		
		String row = seat.subSequence(0,7).toString();
		String col = seat.substring(7);

		int rowNumber = fastDecryptRow(row);
		int colNumber = fastDecryptColumn(col);
		
		return rowNumber * 8 + colNumber;
	}

	int fastDecryptColumn(final String column) {
		int sum = 0;
		int distance = 4;

		for (char c:column.toCharArray()) {
			if (c == 'R') {
				sum += distance;
			}
			distance /= 2;
		}
		return sum;
	}

	int fastDecryptRow(final String row) {
		
		int sum = 0;
		int distance = 64;

		for (char c:row.toCharArray()) {
			if (c == 'B') {
				sum += distance;
			}
			distance /= 2;
		}
		return sum;
	}

	int decryptRow(final String row) {
		
		int upperBorder = 127;
		int lowerBorder = 0;
		int distance = upperBorder - lowerBorder +1;
		
		for (char c:row.toCharArray()) {
			if (c == 'F') {
				upperBorder = distance / 2 - 1 + lowerBorder;
			} else if (c == 'B') {
				lowerBorder = distance / 2 + lowerBorder;
			} else {
				throw new IllegalArgumentException("Input must be F or B, but was: " + c);
			}
			distance = upperBorder - lowerBorder + 1;
			
			// output for test 
			// System.out.println("[" + lowerBorder + "|" + upperBorder + "] d= " + distance);
		}
		
		return upperBorder;
	}

	public int calculateEmptySeat(final String allSeats) {

		final List<String> seats = asList(allSeats.split("(\r\n|\n|\r)"));

		final List<Integer> seatIds = seats.stream()
				.map(this::decryptOneSeatId)
				.sorted()
				.collect(Collectors.toList());

		int lastSeatId = seatIds.get(0) - 1;
		for (int seatId : seatIds) {
			if (seatId != lastSeatId + 1) {
				return seatId-1;
			}
			lastSeatId++;
		}
		
		return -1;
	}
}
