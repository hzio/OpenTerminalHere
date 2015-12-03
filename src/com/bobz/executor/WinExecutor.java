package com.bobz.executor;

import java.io.File;

/**
 * Author: BobZhao
 * Date:   12/2/15 18:43
 * Description: command executor for Windows Platform
 */
public class WinExecutor extends CommandExecutor {

    private static final String WIN_CMD = "C:\\Windows\\System32\\cmd.exe";

    public WinExecutor(String targetPath) {
        super.targetPath = targetPath;
    }

    @Override
    public String getTerminalPath() {
        return WIN_CMD;
    }

    @Override
    public Command buildCommand() {

        String terminalPath = this.getTerminalPath();
        String[] cmdArr = {terminalPath, "/k", "start", "cd", getTargetPath()};

        return new Command(cmdArr, null, new File(getTargetPath()));
    }

}
