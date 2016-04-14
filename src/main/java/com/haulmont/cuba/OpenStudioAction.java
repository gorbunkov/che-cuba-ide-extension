package com.haulmont.cuba;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.api.machine.shared.dto.ServerDto;
import org.eclipse.che.api.workspace.shared.dto.WorkspaceDto;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.app.AppContext;

import java.util.Map;

/**
 */
@Singleton
public class OpenStudioAction extends Action {

    private AppContext appContext;

    @Inject
    public OpenStudioAction(CubaExtensionResources resources,
                            AppContext appContext) {
        super("Open Studio", "Open Studio", null, resources.reloadFileIcon());
        this.appContext = appContext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WorkspaceDto workspace = appContext.getWorkspace();
        Map<String, ServerDto> servers = workspace.getRuntime().getDevMachine().getRuntime().getServers();
        String url = null;
        for (String key : servers.keySet()) {
            if (key.contains("8111")) {
                url = "http://" + servers.get(key).getAddress() + "/studio";
                break;
            }
        }
        if (url != null) {
            Window.open(url, "_blank", null);
        }
    }
}