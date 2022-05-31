package com.test.pims.common.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * Исключение при проблемах с верифицикацией идентфикатора
 *
 * Created by SafinaAA on 26.05.2022
 */
@Slf4j
public class IllegalIdException extends IllegalArgumentException {

    public IllegalIdException(String s) {
        super(s);

        log.error(s);
    }

}
