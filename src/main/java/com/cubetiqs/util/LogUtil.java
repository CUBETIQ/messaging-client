package com.cubetiqs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogUtil {
    private static final Logger log = getLogger(LogUtil.class);

    public static void info(Object ob, Object... args) {
        if (ob == null) {
            log.info("null");
            return;
        }

        log.info(ob.toString(), args);
    }

    public static void warn(Object ob, Object... args) {
        if (ob == null) {
            log.warn("null");
            return;
        }

        log.warn(ob.toString(), args);
    }

    public static void error(Object ob, Object... args) {
        if (ob == null) {
            log.error("null");
            return;
        }

        log.error(ob.toString(), args);
    }

    public static void debug(Object ob, Object... args) {
        if (ob == null) {
            log.debug("null");
            return;
        }

        log.debug(ob.toString(), args);
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
