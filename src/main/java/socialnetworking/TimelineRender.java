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
        final StringBuilder stringBuilder = new StringBuilder();

        final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
        prettyTime.setReference(clock.instant());

        for (final Post post : timeline) {
            final String timeCalculation = prettyTime.format(post.getTime());
            stringBuilder.append(post.getMessage())
                    .append(" (")
                    .append(timeCalculation)
                    .append(")");
        }
        return stringBuilder.toString();
    }
}
