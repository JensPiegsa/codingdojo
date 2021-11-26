package wholikesit;

class Solution {

    public static final String LIKES_THIS = " likes this";

    public static String whoLikesIt(String... names) {

        if (names.length > 0) return names[0] + LIKES_THIS;
        return "no one" + LIKES_THIS;
    }
}