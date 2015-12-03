package com.bobz.action;

import com.bobz.executor.CommandExecutor;
import com.bobz.executor.MacExecutor;
import com.bobz.executor.WinExecutor;
import com.bobz.util.FileUtil;
import com.bobz.util.NotificationTool;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * Author: BobZhao
 * Date:   12/2/15 18:31
 * Description: An IntelliJ plugin for opening current directory in terminal
 */
public class OpenTerminalHereAction extends AnAction {

    public static final String PLUGIN_NAME = "OpenTerminalHere";

    @Override
    public void actionPerformed(AnActionEvent event) {

        Project project = event.getProject();
        if (project == null) {
            return;
        }

        perform(project);

    }

    /**
     * perform the action
     * @param project
     */
    private void perform(@NotNull Project project) {

        VirtualFile selectedFile = FileUtil.getSelectedProjectFile(project);
        if (selectedFile == null) {
            return;
        }

        String targetPath = selectedFile.getPath();
        CommandExecutor executor = null;
        if (SystemInfo.isMac) {
            executor = new MacExecutor(targetPath);
        }
        else if (SystemInfo.isWindows) {
            executor = new WinExecutor(targetPath);
        }

        if (executor == null) {
            NotificationTool.notify(project, PLUGIN_NAME,
                    "Your operating system is not supported temporarily.", NotificationType.ERROR);
            return;
        }

        executor.openTerminal();

    }



}
