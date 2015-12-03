package com.bobz.executor;

import java.io.File;
import java.io.IOException;

/**
 * Author: BobZhao
 * Date:   12/2/15 18:41
 * Description: command execution template
 */
public abstract class CommandExecutor {

    /**
     * determine which terminal to be used
     * @return
     */
    abstract String getTerminalPath();

    /**
     * build the Command object
     * @return
     */
    abstract Command buildCommand();

    /** path to open */
    protected String targetPath;

    /**
     * determine if the specified terminal has been installed
     * @param terminalPath
     * @return
     */
    protected boolean isTerminalInstalled(String terminalPath) {
        File terminal = new File(terminalPath);
        return terminal.exists() && terminal.canExecute();
    }

    /**
     * open the target path in terminal
     */
    public void openTerminal() {

        Command cmd = this.buildCommand();

        try {
            Runtime.getRuntime().exec(cmd.getCmdArray(), cmd.getEnvp(), cmd.getDir());
        } catch (IOException e) {
            // ignored
        }
    };

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
