package socialnetworking;

import java.util.List;

/**
 * @author Jens Piegsa
 */
public interface PostStore {

    void persist(Post post);

    List<Post> readTimeline(String alice);
}
