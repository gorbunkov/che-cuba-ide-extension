/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p>
 * Contributors:
 * Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.haulmont.cuba;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.editor.EditorAgent;
import org.eclipse.che.ide.api.editor.EditorPartPresenter;
import org.eclipse.che.ide.api.event.FileContentUpdateEvent;
import org.eclipse.che.ide.api.project.tree.VirtualFile;

public class ReloadFileAction extends Action {

    private EditorAgent editorAgent;
    private EventBus eventBus;

    @Inject
    public ReloadFileAction(CubaExtensionResources resources,
                            EditorAgent editorAgent,
                            EventBus eventBus) {
        super("Reload File", "Reload file from disk", null, resources.reloadFileIcon());
        this.editorAgent = editorAgent;
        this.eventBus = eventBus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final EditorPartPresenter activeEditor = editorAgent.getActiveEditor();
        if (activeEditor != null) {
            VirtualFile file = activeEditor.getEditorInput().getFile();
            eventBus.fireEvent(new FileContentUpdateEvent(file.getPath()));
        }
    }
}
