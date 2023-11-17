package socialnetworking;

import org.ocpsoft.prettytime.PrettyTime;

import java.time.Clock;
import java.util.List;
import java.util.Locale;

/**
 * @author Jens Piegsa
 */
public class TimelineRender {

    private final Clock clock;

    public TimelineRender(final Clock clock) {
        this.clock = clock;
    }

    public String render(final List<Post> timeline) {
        StringBuilder stringBuilder = new StringBuilder();

        PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
        prettyTime.setReference(clock.instant());

        for (Post post : timeline) {
            String timeCalculation = prettyTime.formatDuration(prettyTime.calculatePreciseDuration(post.getTime()));
            stringBuilder.append(post.getMessage() + " " + timeCalculation);
        }
        return stringBuilder.toString();
    }
}
