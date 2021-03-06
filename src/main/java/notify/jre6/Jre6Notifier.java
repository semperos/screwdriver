package notify.jre6;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import notify.MessageType;
import notify.Notifier;
import notify.UnableToNotifyException;

/**
 * Java Implementation
 * 
 * @author francois wauquier
 * Modified 18 APR 2013 by Kirill Orlov:
 * <ul>
 *  <li>changed readImage() method to use getClass() directly for resource loading</li>
 *  <li>added durationTime_MILLIS argument to notify()</li>
 * </ul>
 * 
 */
public class Jre6Notifier implements Notifier {

    @Override
    public boolean isSupported() {
	try {
	    Class.forName("java.awt.SystemTray");
	    return SystemTray.isSupported();
	} catch (ClassNotFoundException e) {
	    return false;
	}
    }

    @Override
    public void notify(MessageType messageType, String title, String message, final long duration_MILLIS) {
	try {
	    final SystemTray systemTray = SystemTray.getSystemTray();
	    String imageName = messageType.name().toLowerCase() + ".png";
	    Image image = Toolkit.getDefaultToolkit().createImage(readImage(imageName));
	    final TrayIcon trayIcon = new TrayIcon(image, title);
	    systemTray.add(trayIcon);
	    trayIcon.displayMessage(title, message, TrayIcon.MessageType.valueOf(messageType.name()));
	    new Thread(new Runnable() {
		@Override
		public void run() {
		    try {
			Thread.sleep(duration_MILLIS);
		    } catch (InterruptedException e) {
			// ignore
		    }
		    systemTray.remove(trayIcon);
		}
	    }).start();
	} catch (Exception e) {
	    throw new UnableToNotifyException("Unable to notify with java", e);
	}
    }

    private byte[] readImage(String imageName) throws IOException {
	InputStream imageStream = getClass().getResourceAsStream(imageName);
	if (imageStream == null) {
	    throw new FileNotFoundException(imageName);
	}
	ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	byte[] buf = new byte[0x1000];
	int b = imageStream.read(buf);
	while (b != -1) {
	    bytes.write(buf, 0, b);
	    b = imageStream.read(buf);
	}
	return bytes.toByteArray();
    }
}
