package io.libcni.invoke;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The arguments passed to a single CNI plugin invocation, mirroring
 * {@code invoke.Args} in libcni. These are turned into the {@code CNI_*}
 * environment variables consumed by the plugin.
 */
public class Args {

    public String command;
    public String containerID;
    public String netNS;
    /** Ordered list of {@code [key, value]} pairs. */
    public List<String[]> pluginArgs;
    public String ifName;
    public String path;

    public Args() {
    }

    /** Builds the {@code CNI_*} environment variables for this invocation. */
    public Map<String, String> asEnv() {
        Map<String, String> env = new LinkedHashMap<>();
        env.put("CNI_COMMAND", command == null ? "" : command);
        env.put("CNI_CONTAINERID", containerID == null ? "" : containerID);
        env.put("CNI_NETNS", netNS == null ? "" : netNS);
        env.put("CNI_ARGS", pluginArgs == null ? "" : pluginArgs.stream()
            .map(kv -> kv[0] + "=" + kv[1]).collect(Collectors.joining(";")));
        env.put("CNI_IFNAME", ifName == null ? "" : ifName);
        env.put("CNI_PATH", path == null ? "" : path);
        return env;
    }
}
