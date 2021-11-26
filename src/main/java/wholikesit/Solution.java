package wholikesit;

class Solution {

    public static final String LIKES_THIS = " likes this";

    public static String whoLikesIt(String... names) {

        String formattedNames = "no one";
        if (names.length > 0) {
            formattedNames = names[0];
        }
        return formattedNames + LIKES_THIS;
    }
}