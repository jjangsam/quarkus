package io.quarkus.devtools.commands;

import static com.google.common.base.Preconditions.checkNotNull;

import io.quarkus.devtools.commands.data.QuarkusCommandException;
import io.quarkus.devtools.commands.data.QuarkusCommandInvocation;
import io.quarkus.devtools.commands.data.QuarkusCommandOutcome;
import io.quarkus.devtools.commands.handlers.ListExtensionsCommandHandler;
import io.quarkus.devtools.project.QuarkusProject;
import io.quarkus.devtools.project.extensions.ExtensionManager;
import io.quarkus.platform.tools.ToolsConstants;
import io.quarkus.platform.tools.ToolsUtils;

/**
 * Instances of this class are not thread-safe. They are created per single invocation.
 */
public class ListExtensions {
    public static final String NAME = "list-extensions";
    private static final String PARAM_PREFIX = ToolsUtils.dotJoin(ToolsConstants.QUARKUS, NAME);
    public static final String ALL = ToolsUtils.dotJoin(PARAM_PREFIX, "all");
    public static final String FORMAT = ToolsUtils.dotJoin(PARAM_PREFIX, "format");
    public static final String SEARCH = ToolsUtils.dotJoin(PARAM_PREFIX, "search");
    public static final String EXTENSION_MANAGER = ToolsUtils.dotJoin(PARAM_PREFIX, "extension-manager");

    private final QuarkusCommandInvocation invocation;
    private final ListExtensionsCommandHandler handler = new ListExtensionsCommandHandler();

    public ListExtensions(final QuarkusProject quarkusProject) {
        this.invocation = new QuarkusCommandInvocation(quarkusProject);
    }

    public ListExtensions all(boolean all) {
        invocation.setValue(ALL, all);
        return this;
    }

    public ListExtensions format(String format) {
        invocation.setValue(FORMAT, format);
        return this;
    }

    public ListExtensions extensionManager(ExtensionManager extensionManager) {
        invocation.setValue(EXTENSION_MANAGER, checkNotNull(extensionManager, "extensionManager is required"));
        return this;
    }

    public ListExtensions search(String search) {
        invocation.setValue(SEARCH, search);
        return this;
    }

    public QuarkusCommandOutcome execute() throws QuarkusCommandException {
        return handler.execute(invocation);
    }
}
