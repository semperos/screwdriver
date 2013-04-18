package com.semperos.screwdriver.notification;

import java.util.ArrayList;
import java.util.List;

import notify.MessageType;
import notify.Notifier;
import notify.jre6.Jre6Notifier;
import notify.osd.OsdNotifier;

import org.apache.log4j.Logger;

public class TaskTrayNotification
{
    private static final Logger logger = Logger.getLogger(TaskTrayNotification.class);
    
    private static final int errorPopupDuration_MILLIS = 5000;
    
    private static final int defaultPopupDuration_MILLIS = 1200;
    
    private final Notifier notifier;
    

    private TaskTrayNotification()
    {
        // Go through the list of available non-JRE notifiers
        // and use the first one that is supported.  
        List<Notifier> notifiers = availableNotifiers();
        Notifier activeNotifier = null;
        
        for (Notifier notifier : notifiers)
        {
            if (notifier.isSupported())
            {
                activeNotifier = notifier;
                break;
            }
        }
        
        notifier = activeNotifier;
        if (notifier == null) 
        {
            logger.error("No supported task tray notifiers found - UI notifications will not be available.");
            return;
        }
        
        logger.info("Task tray notifications will be provided using " + notifier.getClass());
    }

    private void popNotification(final String title, final String message, MessageType messageType, int duration_MILLIS)
    {
        if (instance.notifier == null) return;
        String titleString = "Screwdriver: " + title;
        notifier.notify(messageType, titleString, message);
    }

    private static TaskTrayNotification instance = new TaskTrayNotification();

    public static void error(final String title, final String message)
    {
        instance.popNotification(title, message, MessageType.ERROR, errorPopupDuration_MILLIS);
    }

    public static void info(final String title, final String message)
    {
        instance.popNotification(title, message, MessageType.INFO, defaultPopupDuration_MILLIS);
    }
    
    public static void warning(final String title, final String message)
    {
        instance.popNotification(title, message, MessageType.WARNING, defaultPopupDuration_MILLIS);
    }
    
    public static void genericMessage(final String title, final String message)
    {
        instance.popNotification(title, message, MessageType.NONE, defaultPopupDuration_MILLIS);
    }
    
    private static List<Notifier> availableNotifiers()
    {
        List<Notifier> notifiers = new ArrayList<>();
        notifiers.add(new OsdNotifier());  // libnotify for Linux
        notifiers.add(new Jre6Notifier()); // Java java.awt.SystemTray
        return notifiers;
    }
}