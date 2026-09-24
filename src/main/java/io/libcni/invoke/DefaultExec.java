package io.libcni.invoke;

import io.libcni.version.PluginDecoder;
import io.libcni.version.PluginInfo;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * The default {@link Exec}: executes plugins from disk via {@link RawExec} and
 * decodes version info with {@link PluginDecoder}. Mirrors {@code invoke.DefaultExec}.
 */
public class DefaultExec implements Exec {

    private final RawExec rawExec;

    public DefaultExec() {
        this(0);
    }

    /** Applies a per-invocation timeout (in milliseconds); {@code 0} means no limit. */
    public DefaultExec(long timeoutMillis) {
        this.rawExec = new RawExec(timeoutMillis);
    }

    @Override
    public byte[] execPlugin(String pluginPath, byte[] stdinData, Map<String, String> environ) {
        return rawExec.execPlugin(pluginPath, stdinData, environ);
    }

    @Override
    public String findInPath(String plugin, List<String> paths) {
        return FindInPath.find(plugin, paths);
    }

    @Override
    public PluginInfo decode(byte[] jsonBytes) {
        return PluginDecoder.decode(new String(jsonBytes, StandardCharsets.UTF_8));
    }
}
