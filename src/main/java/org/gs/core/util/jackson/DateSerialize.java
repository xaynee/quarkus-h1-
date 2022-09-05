package com.dimata.service.general.harisma.core.util.jackson;

import com.dimata.service.general.harisma.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;

public class DateSerialize extends StdConverter<LocalDate, String> {
    @Override
    public String convert(LocalDate time) {
        return FormatUtil.convertDateToString(time);
    }
}
