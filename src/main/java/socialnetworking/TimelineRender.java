package socialnetworking;

import java.util.List;

/**
 * @author Jens Piegsa
 */
public class TimelineRender {

    public String render(final List<Post> timeline) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Post post : timeline) {
            stringBuilder.append(post.getMessage() + "(2 minutes ago)");
        }
        return stringBuilder.toString();
    }
}
