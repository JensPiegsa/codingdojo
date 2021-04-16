package advent2020.day08;

public class SimpleInterpreter {
	
	public int executeLoopSafe(final String code) {
		
		int accumulator = 0;
		int instructionPointer = 0;
		
		final String[] instructions = code.split("\n");
		
		while (instructionPointer < instructions.length) {

			final String instruction = instructions[instructionPointer];
			final String[] tokens = instruction.split(" ");
			
			String operator = tokens[0];
			String operand = tokens[1];

			switch (operator) {
				case "nop":
					instructionPointer++;
					break;
				case "acc":
					accumulator += Integer.parseInt(operand);
					instructionPointer++;
					break;
				case "jmp":
					instructionPointer += Integer.parseInt(operand);
					break;
				default:
					throw new UnsupportedOperationException("Instruction: " +instruction);
			}
			
		}

		return accumulator;
	}
}
