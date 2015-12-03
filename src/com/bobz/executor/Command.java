package com.bobz.executor;

import java.io.File;

/**
 * Author: BobZhao
 * Date:   12/3/15 10:31
 * Description:
 */
public class Command {

    private String[] cmdArray;

    private String[] envp;

    private File dir;

    public Command(String[] cmdArray) {
        this.cmdArray = cmdArray;
    }

    public Command(String[] cmdArray, String[] envp, File dir) {
        this.cmdArray = cmdArray;
        this.envp = envp;
        this.dir = dir;
    }

    public String[] getCmdArray() {
        return cmdArray;
    }

    public void setCmdArray(String[] cmdArray) {
        this.cmdArray = cmdArray;
    }

    public String[] getEnvp() {
        return envp;
    }

    public void setEnvp(String[] envp) {
        this.envp = envp;
    }

    public File getDir() {
        return dir;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }
}
