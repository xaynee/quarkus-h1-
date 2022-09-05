package com.dimata.service.general.harisma.core.util.jackson;

import com.dimata.service.general.harisma.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

public class TimeSerialize extends StdConverter<LocalDateTime, String> {
  @Override
  public String convert(LocalDateTime time) {
    return FormatUtil.convertToString(time);
  }
}
