package socialnetworking;

import java.util.List;

/**
 * @author Jens Piegsa
 */
public class InMemoryPostStore implements PostStore {
    
    @Override
    public void persist(final Post post) {
        
    }

    @Override
    public List<Post> readTimeline(final String alice) {
        return null;
    }
}

