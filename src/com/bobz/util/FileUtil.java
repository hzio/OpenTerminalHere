package com.bobz.util;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Author: BobZhao
 * Date:   12/2/15 18:31
 * Description: tool for file operation
 */
public class FileUtil {

    /**
     * determine the VirtualFile which selected in Project view
     * @param project
     * @return
     */
    public static VirtualFile getSelectedProjectFile(@NotNull Project project) {

        AbstractProjectViewPane currentProjectViewPane = ProjectView.getInstance(project).getCurrentProjectViewPane();
        if (currentProjectViewPane == null) {
            return null;
        }
        DefaultMutableTreeNode node = currentProjectViewPane.getSelectedNode();
        if (node == null) {
            return null;
        }
        Object selected = null;
        Object userObject = node.getUserObject();
        if (userObject instanceof AbstractTreeNode) {
            selected =  ((AbstractTreeNode)userObject).getValue();
        }
        else  if (userObject instanceof NodeDescriptor) {
            selected =  ((NodeDescriptor)userObject).getElement();
        }

        if (selected == null) {
            return null;
        }

        VirtualFile vf = null;
        if (selected instanceof PsiDirectory) {
            vf = ((PsiDirectory)selected).getVirtualFile();
        }
        else if (selected instanceof PsiElement) {
            vf = ((PsiElement) selected).getContainingFile().getVirtualFile().getParent();
        }
        else {
            // ignored
        }

        return vf;
    }


}
