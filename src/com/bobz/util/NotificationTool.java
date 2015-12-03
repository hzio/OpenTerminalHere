package com.bobz.util;

import com.bobz.action.OpenTerminalHereAction;
import com.intellij.notification.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;

/**
 * Author: BobZhao
 * Date:   12/2/15 18:31
 * Description: tool for notification
 */
public class NotificationTool {

    /**
     * notify message via Notification Bus component
     * @param project
     * @param title
     * @param message
     * @param notificationType
     */
    public static void notify(final Project project, final String title, final String message, final NotificationType notificationType) {
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
               @Override
               public void run() {
                   String groupId = OpenTerminalHereAction.PLUGIN_NAME;
                   NotificationsConfiguration.getNotificationsConfiguration().register(groupId, NotificationDisplayType.BALLOON, false);
                   final Notification notification = new Notification(groupId, title, message,
                           notificationType, null);
                   Notifications.Bus.notify(notification, project);
               }
           }
        );
    }
}
