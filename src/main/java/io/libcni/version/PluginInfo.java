package io.libcni.version;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Version information reported by a CNI plugin's {@code VERSION} command,
 * mirroring {@code version.PluginInfo} in libcni.
 */
public class PluginInfo {

    private final String cniVersion;
    private final List<String> supportedVersions;

    public PluginInfo(String cniVersion, List<String> supportedVersions) {
        this.cniVersion = cniVersion;
        this.supportedVersions = new ArrayList<>(supportedVersions);
    }

    public String cniVersion() {
        return cniVersion;
    }

    public List<String> supportedVersions() {
        return supportedVersions;
    }

    /** Returns a PluginInfo reporting the given versions as supported. */
    public static PluginInfo pluginSupports(String... versions) {
        if (versions.length < 1) {
            throw new IllegalArgumentException("programmer error: you must support at least one version");
        }
        return new PluginInfo(Version.CURRENT, Arrays.asList(versions));
    }
}
