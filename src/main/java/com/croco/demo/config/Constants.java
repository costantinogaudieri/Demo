package com.croco.demo.config;

import java.time.LocalDate;

public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String MDC_USER = "MDC_USER";
    public static final String MDC_ROLE = "MDC_ROLE";
    public static final String MDC_PDV = "MDC_PDV";

    public static final int MAXSIZE_OP = 20;

    public static final LocalDate DEFAULT_DATA_FINE_VALIDITA = LocalDate.now().withYear(3000).withMonth(12)
            .withDayOfMonth(31);

    public static final String DEFAULT_CHANNEL = "I";



    private Constants() {
    }
}
