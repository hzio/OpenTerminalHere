package com.bobz.executor;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.bobz.configuration.Configuration;

/**
 * Author: BobZhao
 * Date:   12/2/15 18:43
 * Description: command executor for Windows Platform
 */
public class WinExecutor extends CommandExecutor {

    private static final String WIN_CMD = "C:\\Windows\\System32\\cmd.exe";

    private static final String WIN_PSH = "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe";

    public WinExecutor(String targetPath) {
        super.targetPath = targetPath;
    }

    @Override
    public String getTerminalPath() {
        if (!StringUtils.isBlank(Configuration.TERM_PATH)) {
            return Configuration.TERM_PATH;
        } else {
            return WIN_CMD;
        }
    }

    @Override
    public Command buildCommand() {
        String[] cmdArr;

        String terminalPath = this.getTerminalPath();
        if (terminalPath.equals(WIN_CMD)) {
            cmdArr = new String[] {terminalPath, "/k", "start", "cd", getTargetPath()};
        } else if (terminalPath.equals(WIN_PSH)){
            cmdArr = new String[] { "cmd",  "/c", "start", terminalPath, "-NoExit", "-Command", "cd", getTargetPath() };
        } else {
            cmdArr = new String[] {terminalPath};
        }

        return new Command(cmdArr, null, new File(getTargetPath()));
    }

}
