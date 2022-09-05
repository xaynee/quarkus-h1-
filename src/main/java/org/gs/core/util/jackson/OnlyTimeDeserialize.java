package com.dimata.service.general.harisma.core.util.jackson;

import com.dimata.service.general.harisma.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalTime;

public class OnlyTimeDeserialize extends StdConverter<String, LocalTime> {
    @Override
    public LocalTime convert(String time) {
        return FormatUtil.convertTimeToLocalTime(time,"HH:mm:ss");
    }
    
}
