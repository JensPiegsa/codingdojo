package christmastree;

import static java.lang.Math.max;

public class ChristmasTree {

    private String[] chars;
    private int n;

    public ChristmasTree(String chars, int n) {
        this.chars = chars.split("");
        this.n = n;
    }

    public String draw() {
        return drawBody() +
                drawTrunk();
    }

    private String drawBody() {
        StringBuilder sb = new StringBuilder();
        int c = 0;

        for (int i = 0; i < n; i++) {

            sb.append(spaces(n - 1 - i));

            for (int j = 0; j <= i; j++) {

                String symbole = chars[c];
                sb.append(symbole).append(" ");
                c = (c + 1) % chars.length;
            }

            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
        }

        return sb.toString();
    }

    private String drawTrunk() {
        int length = max(n / 3, 1);
        int width = n - 1;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length - 1; i++) {
            sb.append(spaces(width)).append("|\n");
        }

        sb.append(spaces(width)).append("|");

        return sb.toString();
    }

    private String spaces(int count) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<count; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

}
