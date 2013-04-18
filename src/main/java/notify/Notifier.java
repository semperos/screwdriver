package notify;


/**
 * Notifier Interface
 * 
 * @author francois wauquier<br><br>
 * 
 * Modified 17 APR 2013 by Kirill Orlov:
 * <ul>
 *  <li>added durationTime_MILLIS argument to Notify
 *  <li>fleshed out Javadoc</li>
 * </ul>
 */
public interface Notifier 
{
    /**
     * @return whether or not this implementation is supported on this operating system.
     */
    boolean isSupported();

    /**
     * Show the message with the specified title using a popup of the specified type
     * that expires after the specified duration.
     * 
     * @param messageType The type of the popup to show, as specified by {@link MessageType}
     * @param title The text to display in the popup title.
     * @param message The text to display in the popup body.
     * @param durationTime_MILLIS The duration (in milliseconds) for which the popup is shown.
     */
    void notify(MessageType messageType, String title, String message, long durationTime_MILLIS);
}