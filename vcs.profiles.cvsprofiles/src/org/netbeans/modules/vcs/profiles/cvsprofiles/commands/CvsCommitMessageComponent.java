/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.vcs.profiles.cvsprofiles.commands;

import org.netbeans.modules.vcscore.util.NestableInputComponent;
import org.netbeans.modules.vcscore.util.VariableInputNest;
import org.netbeans.modules.vcscore.commands.PreCommandPerformer;
import org.netbeans.modules.vcscore.commands.CommandExecutionContext;
import org.openide.util.UserCancelException;
import org.openide.util.RequestProcessor;
import org.openide.util.NbBundle;
import org.openide.ErrorManager;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.io.*;
import java.util.Map;

/**
 * Implements VID.JCOMPONENT for commit message featuring
 * [load template] button.
 *
 * @author Petr Kuzel
 */
public class CvsCommitMessageComponent extends JPanel implements NestableInputComponent {

    /** PreCommandPerformer command. */
    private static final String COMMAND = "{INSERT_OUTPUT_OF_COMMIT_TEMPLATE_GETTER(0, true)}"; // NOI18N

    /** Name of associated variable in cvs.xml profile */
    private static final String VARIABLE = "TEMPLATE_FILE"; // NOI18N

    private VariableInputNest nest;

    private final JLabel label = new JLabel();
    private final JTextArea textArea = new JTextArea();
    private final JButton loadButton = new JButton();
    private RequestProcessor.Task fetcherTask;

    private boolean wasValid = false;

    /** NestableInputComponent is created by reflection. */
    public CvsCommitMessageComponent() {
    }

    public void joinNest(VariableInputNest container) {
        this.nest = container;
        setLayout(new BorderLayout());

        label.setText(getString("COMMAND_COMMIT_Reason"));
        label.setLabelFor(textArea);
        loadButton.setMnemonic(getString("COMMAND_COMMIT_Reason_mne").charAt(0));
        add(label, BorderLayout.NORTH);

        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setColumns(80);
        textArea.setRows(8);
        textArea.setToolTipText(getString("COMMAND_COMMIT_Reason_desc"));
        Font font = textArea.getFont();
        textArea.setFont(new java.awt.Font("Monospaced", font.getStyle(), font.getSize()));  // NOI18N
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateValidity();
            }

            public void removeUpdate(DocumentEvent e) {
                updateValidity();
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        add(scrollableTextArea, BorderLayout.CENTER);

        loadButton.setText(getString("COMMAND_COMMIT_tg"));
        loadButton.setToolTipText(getString("COMMAND_COMMIT_tg_desc"));
        loadButton.setMnemonic(getString("COMMAND_COMMIT_tg_mne").charAt(0));
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setEditable(false);
                updateValidity();
                textArea.setText(getString("COMMAND_COMMIT_tl"));
                loadButton.setEnabled(false);
                //loadButton.setText("Cancel Loading");
                TemplateFetcher fetcher = new TemplateFetcher(nest.getCommandExecutionContext(), nest.getCommandHashtable());
                fetcherTask = RequestProcessor.getDefault().post(fetcher);
            }
        });
        JPanel trailingAlign = new JPanel(new FlowLayout(FlowLayout.TRAILING, 4, 0));
        trailingAlign.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        trailingAlign.add(loadButton);
        add(trailingAlign, BorderLayout.SOUTH);
    }

    /** @return Absolute path to message file. */
    public String getValue(String variable) {
        assert VARIABLE.equals(variable);

        if (validityCheck()) {
            String content = cleanupContent(textArea.getText());
            try {
                File tmpfile = File.createTempFile("cvs-commit-", ".input");  // NOI18N
                Writer w = new FileWriter(tmpfile);
                try {
                    w.write(content);
                    w.flush();
                    return tmpfile.getAbsolutePath();
                } finally {
                    try {
                        w.close();
                    } catch (IOException ex) {
                        // already closed
                    }
                }
            } catch (IOException e) {
                ErrorManager err = ErrorManager.getDefault();
                err.annotate(e, getString("COMMAND_COMMIT_ex"));
                err.notify(e);
            }
        }
        return null;
    }

    public String getVerificationMessage(String variable) {
        assert VARIABLE.equals(variable) : "Unexpected var:" + variable; // NOI18N
        if (validityCheck()) {
            return null;
        } else if (loadingInProgress()) {
            return getString("COMMAND_COMMIT_val_l");
        } else {
            return getString("COMMAND_COMMIT_val_m");
        }
    }

    /** Non empty text must be entered, and loading must not be in progress. */
    private boolean validityCheck() {
        return  loadingInProgress() == false && cleanupContent(textArea.getText()).length() > 0;
    }

    /** Is running background template loading? */
    private boolean loadingInProgress() {
        return textArea.isEditable() == false;
    }

    /** Removes all lines begining with "CVS:" */
    private static String cleanupContent(String template) {
        BufferedReader r = new BufferedReader(new StringReader(template));
        try {
            StringWriter w = new StringWriter(template.length());
            while (true) {
                try {
                    String line = r.readLine();
                    if (line == null) break;
                    if (line.startsWith("CVS:")) continue;   // NOI18N
                    w.write(line);
                } catch (IOException e) {
                    ErrorManager err = ErrorManager.getDefault();
                    err.notify(e);
                }
            }
            w.flush();
            return w.toString().trim();
        } finally {
            try {
                r.close();
            } catch (IOException e) {
                // already closed
            }
        }
    }

    /** Refires validity event. */
    private void updateValidity() {
        boolean valid = validityCheck();
        if (valid != wasValid) {
            nest.fireValueChanged(VARIABLE, null);
            wasValid = valid;
        }
    }

    public void leaveNest() {
        nest = null;
        if (fetcherTask != null) fetcherTask.cancel();
    }

    /** Seeks for loacalized string */
    private static String getString(String key) {
        return NbBundle.getMessage(CvsCommitMessageComponent.class, key);
    }

    public void updatedVars(Map variables) {
        String message = (String) variables.get("message");
        if (message != null) {
            textArea.setText(message);
        }
    }
    
    /** Asynchronously loads template from the server. */
    private class TemplateFetcher implements Runnable {

        private final CommandExecutionContext ctx;

        private final Hashtable env;

        public TemplateFetcher(CommandExecutionContext ctx, Hashtable env) {
            this.ctx = ctx;
            this.env = env;
        }

        public void run() {
            PreCommandPerformer executor = new PreCommandPerformer(ctx, env);
            try {
                final String content = executor.process(COMMAND);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        textArea.setText(content);
                        textArea.requestFocus();
                        textArea.getCaret().setDot(0);
                        textArea.setEditable(true);
                        loadButton.setEnabled(true);
                        fetcherTask = null;
                        updateValidity();
                    }
                });
            } catch (UserCancelException e) {
                ErrorManager err = ErrorManager.getDefault();
                err.annotate(e, "Unexpected dialog raised by " + COMMAND);  // NOU18N
                err.notify(e);
            }
        }
    }
}
