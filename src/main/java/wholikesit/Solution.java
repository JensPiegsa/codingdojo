package wholikesit;

class Solution {

    public static String whoLikesIt(final String... names) {

        final int count = names.length;
        String formattedNames;

        if (count == 0) {
            formattedNames = "no one";
        } else if (count == 1) {
            formattedNames = names[0];
        } else if (count == 2) {
            formattedNames = names[0] + " and " + names[1];
        } else if (count == 3) {
            formattedNames = names[0] + ", " + names[1] + " and " + names[2];
        } else {
            formattedNames = names[0] + ", " + names[1] + " and " + (count - 2) + " others";
        }
        return formattedNames + (count <= 1 ? " likes this" : " like this");
    }
}