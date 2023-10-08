package com.foodvilla.backend.constants;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ApplicationConstants {
    public static final String JWT_HEADER = "Authorization";
    public static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(2); // 2 days
}
