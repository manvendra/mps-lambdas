package com.mps;

import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * @author manvendrasingh
 * @since 2022-May-23
 * <p>
 * </p>
 * <p>
 * A common Utils class for all MPS modules
 * </p>
 **/
public class MpsUtils {

    //OffsetDateTime to string has ISO-8601 format
    public static String getCurrentUtcTime() {
        return OffsetDateTime
                .now()
                .atZoneSameInstant(ZoneId.of(Constants.ZONE_UTC))
                .toString();
    }
}
