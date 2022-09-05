package com.dimata.service.general.harisma.core.util.jackson;

import com.dimata.service.general.harisma.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;

public class DateDeserialize extends StdConverter<String, LocalDate> {
    @Override
    public LocalDate convert(String value) {
        return FormatUtil.convertDateToLocalDate(value, "dd-MM-yyyy");
    }
}
