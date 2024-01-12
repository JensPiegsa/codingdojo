package socialnetworking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jens Piegsa
 */
public class InMemoryPostStore implements PostStore {
    
    private List<Post> posts = new ArrayList<>();
    @Override
    public void persist(final Post post) {
        posts.add(post);
    }

    @Override
    public List<Post> readTimeline(final String author) {
        return posts.stream()
                .filter(post -> author.equals(post.getUsername()))
                .toList();
    }
}

