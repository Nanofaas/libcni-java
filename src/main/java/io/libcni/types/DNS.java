package io.libcni.types;

import java.util.List;

/** DNS configuration, mirroring {@code types.DNS} in libcni. */
public class DNS {
    public List<String> nameservers;
    public String domain;
    public List<String> search;
    public List<String> options;

    public DNS() {
    }

    public boolean isEmpty() {
        return (nameservers == null || nameservers.isEmpty())
            && (domain == null || domain.isEmpty())
            && (search == null || search.isEmpty())
            && (options == null || options.isEmpty());
    }
}
