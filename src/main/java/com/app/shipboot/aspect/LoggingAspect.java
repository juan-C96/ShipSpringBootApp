package com.app.shipboot.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.app.shipboot.service.ShipService.getShipById(..)) && args(id)")
    public void logNegativeId(long id) {
        if (id < 0) {
            logger.warn("Attempted to retrieve ship with negative id: {}", id);
        }
    }
}
