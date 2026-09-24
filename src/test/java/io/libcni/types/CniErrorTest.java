package io.libcni.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class CniErrorTest {

    @Test
    void messageIncludesDetails() {
        CniError e = new CniError(CniErrorCode.INVALID_NETWORK_CONFIG, "bad config", "line 3");
        assertEquals("bad config; line 3", e.getMessage());
    }

    @Test
    void messageOmitsEmptyDetails() {
        CniError e = new CniError(CniErrorCode.INVALID_NETWORK_CONFIG, "bad config", "");
        assertEquals("bad config", e.getMessage());
    }

    @Test
    void parsesFromJson() {
        CniError e = CniError.fromJsonString("{\"code\":5,\"msg\":\"io\",\"details\":\"d\"}");
        assertEquals(5, e.code());
        assertEquals("io", e.msg());
        assertEquals("d", e.details());
    }

    @Test
    void carriesCause() {
        RuntimeException cause = new RuntimeException("boom");
        CniError e = new CniError(CniErrorCode.DECODING_FAILURE, "msg", "", cause);
        assertSame(cause, e.getCause());
    }
}
