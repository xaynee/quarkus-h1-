package com.dimata.service.general.harisma.core.security.jwt.tool;

import com.dimata.service.general.harisma.exception.ExceptionCode;
import com.dimata.service.general.harisma.exception.FormatException;
import lombok.Data;

import java.time.temporal.ChronoUnit;

@Data
public class JwtExpiredTime {
    private final int period;
    private final ChronoUnit unit;

    public static JwtExpiredTime fromFormat(String expiredFormat) {
        try {
            String[] split = expiredFormat.split("\\|");
            var period = Integer.valueOf(split[0]);
            var unit = ChronoUnit.valueOf(split[1].toUpperCase());
            return new JwtExpiredTime(period, unit);
        }catch (Exception e) {
            throw new FormatException(ExceptionCode.F_NV, "expiredFormat is wrong. Acceptable format is `period|unit`");
        }
    }
}
