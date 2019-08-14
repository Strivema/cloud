package com.ray.cloud.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Ray.Ma
 * @date 2019/8/14 15:34
 */
public class IOUtils {
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }
}
