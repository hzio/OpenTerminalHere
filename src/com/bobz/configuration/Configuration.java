package com.bobz.configuration;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.FileUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.util.xmlb.XmlSerializerUtil;

public class Configuration implements SearchableConfigurable, PersistentStateComponent {

  public State state;

  public static String TERM_PATH;

  private static final String TEXT_DISPLAY_NAME = "Open Terminal Here";

  private static final String ID = "OpenTerminalHere.Configuration";

  private JTextField termPath;

  @Nullable
  @Override
  public JComponent createComponent() {
    JPanel panel = initComponent();
    return panel;
  }

  @Override
  public boolean isModified() {
    return !StringUtils.equals(TERM_PATH, termPath.getText());
  }

  @Override
  public void apply() throws ConfigurationException {
    if (termPath.getText() != null && FileUtils.fileExists(termPath.getText())) {
      TERM_PATH = termPath.getText();
    }
  }

  @Override
  public void reset() {
    termPath.setText(TERM_PATH);
  }

  @Nls
  @Override
  public String getDisplayName() {
    return TEXT_DISPLAY_NAME;
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return "Specify the paths for which terminal to use.";
  }

  private JPanel initComponent() {
    JLabel pathLabel = new JLabel("Path for which terminal to use:");
    termPath = new JTextField();
    termPath.setMaximumSize(new Dimension(Integer.MAX_VALUE, termPath.getPreferredSize().height));
    termPath.setMinimumSize(new Dimension(20, termPath.getPreferredSize().height));

    JPanel panel = new JPanel();
    panel.add(pathLabel);
    panel.add(termPath);

    panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

    return panel;
  }

  @Nullable
  @Override
  public Configuration getState() {
    return this;
  }

  @Override
  public void loadState(Object o) {
    XmlSerializerUtil.copyBean(state, this);
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }
}
