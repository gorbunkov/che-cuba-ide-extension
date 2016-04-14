/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.haulmont.cuba;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.action.ActionManager;
import org.eclipse.che.ide.api.action.DefaultActionGroup;
import org.eclipse.che.ide.api.action.IdeActions;
import org.eclipse.che.ide.api.constraints.Constraints;
import org.eclipse.che.ide.api.extension.Extension;

@Singleton
@Extension(title = "CUBA Extension", version = "1.0.0")
public class CubaExtension {

    @Inject
    public CubaExtension(ActionManager actionManager,
                         ReloadFileAction reloadFileAction,
                         OpenStudioAction openStudioAction) {
        DefaultActionGroup editMenu = (DefaultActionGroup) actionManager.getAction(IdeActions.GROUP_EDIT);
        actionManager.registerAction("ReloadFileActionID", reloadFileAction);
        editMenu.add(reloadFileAction);

        DefaultActionGroup mainMenu = (DefaultActionGroup) actionManager.getAction(IdeActions.GROUP_MAIN_MENU);
        DefaultActionGroup openStudioMenu = new DefaultActionGroup("Open Studio", true, actionManager);
        mainMenu.add(openStudioMenu, Constraints.LAST);
        actionManager.registerAction("OpenStudioActionID", openStudioAction);
        actionManager.registerAction("OpenStudioMenuID", openStudioMenu);
    }
}
