package humanreadabletime;

public class HumanReadableTime {
  
  public static String makeReadable(final int seconds) {
    final int hoursOnly = seconds / 3600;
    final int secondsOnly = seconds % 60;
    final int minutesOnly = (seconds / 60) % 60;
    return String.format("%02d:%02d:%02d", hoursOnly, minutesOnly, secondsOnly);
  }
}