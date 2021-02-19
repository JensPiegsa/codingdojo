package advent2020.day06;

import java.util.Arrays;

public class Survey {
	
	public long countYesOfAnyoneInGroups(final String answers) {

		final String[] answersByGroup = answers.split("(\r\n|\n|\r){2}");

		return Arrays.stream(answersByGroup)
				.mapToLong(oneLine -> 
					oneLine.chars()
							.filter(c -> c != '\n' && c != '\r')
							.distinct()
							.count()
				)
				.sum();

		/* old
		
		for (String s: answersByGroup) {
			sumByGroup += s.chars()
					.filter(c -> c != '\n' && c != '\r')
					.distinct()
					.count();

		}
		
		 */
	}

	public long countYesOfEveryoneInGroups(final String answers) {

		final String[] answersByGroup = answers.split("(\r\n|\n|\r){2}");

		return Arrays.stream(answersByGroup)
				.mapToLong(this::countYesIfAllGroupMembersVoteYes)
				.sum();
	}
	
	private long countYesIfAllGroupMembersVoteYes(final String groupAnswer) {

		final String[] individualAnswers = groupAnswer.split("(\r\n|\n|\r)");
		String first = individualAnswers[0];

		if (individualAnswers.length > 1) {
			for (int i = 1; i < individualAnswers.length; i++) {
				first = getSameCharsOfBoth(first, individualAnswers[i]);
			}
		}
		return first.length();
	}
	
	private String getSameCharsOfBoth(final String one, final String two) {
		StringBuilder sameChars = new StringBuilder();

		for (char c : one.toCharArray()) {
			if (two.contains(c + "")) {
				sameChars.append(c);
			}
		}
		
		return sameChars.toString();
	}
}
