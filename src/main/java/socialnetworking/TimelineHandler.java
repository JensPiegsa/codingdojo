package socialnetworking;

import org.apache.commons.lang3.StringUtils;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import static socialnetworking.HttpMethod.GET;
import static socialnetworking.HttpMethod.POST;

public class TimelineHandler implements HttpRequestHandler {
    private static final int OK = 200;
    private static final int ACCEPTED = 202;
    private static final int BAD_REQUEST = 400;
    static final Logger logger = Logger.getLogger(SocialNetworkServer.class.getName());
    private final PostStore postStore;
    private final TimelineRender timelineRenderer;
    private final Clock clock;

    TimelineHandler(PostStore postStore, TimelineRender timelineRender, Clock clock) {
        this.postStore = postStore;
        this.timelineRenderer = timelineRender;
        this.clock = clock;
    }
    
    @Override
    public String getUrlPartPath() {
        return "/timeline";
    }

    @Override
    public Response get(String urlPathPart, String requestBody) {
        logger.info("requestMethod: GET urlPathPart: " + urlPathPart + " requestBody: " + requestBody);

        final String responseBody;
        final String username = StringUtils.removeStart(urlPathPart, "/timeline/");
        if ("alice".equals(username.toLowerCase(Locale.ROOT))) {
            final List<Post> timeline = postStore.readTimeline("alice");
            final String prettyTimeline = timelineRenderer.render(timeline);
            responseBody = prettyTimeline;
        } else {
            responseBody = "";
        }
        return new Response(responseBody, OK);
    }

    @Override
    public Response post(String urlPathPart, String requestBody) {
        logger.info("requestMethod: POST urlPathPart: " + urlPathPart + " requestBody: " + requestBody);

        final String urlSegmentTail = StringUtils.removeStart(urlPathPart, "/timeline");
        logger.info("Posting!");
        final String username = StringUtils.removeStart(urlSegmentTail, "/");

        logger.info("username: " + username);
        final int responseStatusCode;
        if (StringUtils.isEmpty(username)) {
            responseStatusCode = BAD_REQUEST;
        } else {
            responseStatusCode = ACCEPTED;
            final ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(clock.instant(), clock.getZone());
            postStore.persist(new Post(username, requestBody, zonedDateTime));
        }
        return new Response("", responseStatusCode);
    }
}
