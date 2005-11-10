/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.tasklist.usertasks.translators;

import java.io.File;
import java.io.InputStream;

import javax.swing.filechooser.FileSystemView;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.netbeans.modules.tasklist.core.export.ExportImportProvider;
import org.netbeans.modules.tasklist.core.export.SaveFilePanel;
import org.netbeans.modules.tasklist.core.util.ExtensionFileFilter;
import org.netbeans.modules.tasklist.core.util.SimpleWizardPanel;
import org.openide.DialogDisplayer;
import org.openide.ErrorManager;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.NbBundle;   

/**
 * Creates plain text file using XSL transformation.
 *
 * @author tl
 */
public class TextExportFormat extends XmlExportFormat {
    private String res = "usertasks-effort-text.xsl"; // NOI18N
    
    /** 
     * Creates a new instance of HTMLTranslator 
     */
    public TextExportFormat() {
    }
    
    public String getName() {
        return NbBundle.getMessage(TextExportFormat.class, "Text"); // NOI18N
    }
    
    public org.openide.WizardDescriptor getWizard() {
        SaveFilePanel chooseFilePanel = new SaveFilePanel();
        SimpleWizardPanel chooseFileWP = new SimpleWizardPanel(chooseFilePanel);
        chooseFilePanel.setWizardPanel(chooseFileWP);
        chooseFilePanel.getFileChooser().addChoosableFileFilter(
            new ExtensionFileFilter(
                NbBundle.getMessage(XmlExportFormat.class, 
                    "TextFilter"), // NOI18N
                new String[] {".txt"})); // NOI18N
        chooseFilePanel.setFile(new File(
            FileSystemView.getFileSystemView().
            getDefaultDirectory(), "tasklist.txt")); // NOI18N
        chooseFileWP.setContentHighlightedIndex(0);

        TextTemplatesPanel templatesPanel = new TextTemplatesPanel();
        SimpleWizardPanel templatesWP = new SimpleWizardPanel(templatesPanel);
        templatesWP.setFinishPanel(true);
        templatesWP.setContentHighlightedIndex(1);

        // create wizard descriptor
        WizardDescriptor.Iterator iterator = 
            new WizardDescriptor.ArrayIterator(
                new WizardDescriptor.Panel[] {chooseFileWP, templatesWP});
        WizardDescriptor wd = new WizardDescriptor(iterator);
        wd.putProperty("WizardPanel_contentData", // NOI18N
            new String[] {
                NbBundle.getMessage(
                    TextExportFormat.class, "TextChooseDestination"), // NOI18N
                NbBundle.getMessage(
                    TextExportFormat.class, "TextChooseLayout") // NOI18N
            }
        ); // NOI18N
        wd.putProperty("WizardPanel_autoWizardStyle", Boolean.TRUE); // NOI18N
        wd.putProperty("WizardPanel_contentDisplayed", Boolean.TRUE); // NOI18N
        wd.putProperty("WizardPanel_contentNumbered", Boolean.TRUE); // NOI18N
        wd.setTitle(NbBundle.getMessage(TextExportFormat.class,
            "ExportText")); // NOI18N
        wd.putProperty(getClass().getName() + 
            ".TemplatesPanel", templatesPanel); // NOI18N
        wd.putProperty(CHOOSE_FILE_PANEL_PROP, chooseFilePanel);
        wd.setTitleFormat(new java.text.MessageFormat("{0}")); // NOI18N
        
        return wd;
    }
    
    /**
     * Opens the specified file in the IDE.
     *
     * @param file file to be opened
     */
    private static void openFileInIde(File file) {
        try
        {
            FileObject fo = FileUtil.toFileObject(file);
            if (fo != null) {
                DataObject do_ = DataObject.find(fo);
                OpenCookie oc = (OpenCookie) do_.getCookie(OpenCookie.class);
                if (oc != null) {
                    oc.open();
                } else {
                    String msg = NbBundle.getMessage(TextExportFormat.class, 
                            "CannotOpenFile"); // NOI18N
                    NotifyDescriptor nd = new NotifyDescriptor.Message(
                            msg, NotifyDescriptor.ERROR_MESSAGE);
                    DialogDisplayer.getDefault().notify(nd);
                }
            } else {
                String msg = NbBundle.getMessage(TextExportFormat.class, 
                        "CannotFindFile"); // NOI18N
                NotifyDescriptor nd = new NotifyDescriptor.Message(
                        msg, NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(nd);
            }
        } catch (DataObjectNotFoundException e) {
            ErrorManager.getDefault().notify(e);
        }
    }
   
    protected Transformer createTransformer() {
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            InputStream xsl = TextExportFormat.class.
                getResourceAsStream(res);
            return tf.newTransformer(new StreamSource(xsl));
        } catch (TransformerConfigurationException e) {
            ErrorManager.getDefault().notify(e);
            return null;
        } catch (TransformerException e) {
            ErrorManager.getDefault().notify(e);
            return null;
        }
   }
    
    public void doExportImport(ExportImportProvider provider, 
    WizardDescriptor wd) {
        SaveFilePanel chooseFilePanel = (SaveFilePanel)
            wd.getProperty(CHOOSE_FILE_PANEL_PROP);
        TextTemplatesPanel templatesPanel = (TextTemplatesPanel)
            wd.getProperty(getClass().getName() + 
                ".TemplatesPanel"); // NOI18N
        super.doExportImport(provider, wd);
        if (templatesPanel.getOpenFile()) {
            openFileInIde(chooseFilePanel.getFile());
        }
    }
    
}
