package com.maideit.maideit_api.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimitingFilter extends OncePerRequestFilter {

    private static final int MAX_REQUESTS_PER_SECOND = 10; // Adjust as needed
    private final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();

    public RateLimitingFilter() {
        // Schedule a task to reset request counts every second
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(requestCounts::clear, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String clientIp = getClientIp(request);

        // Increment the request count for the IP
        requestCounts.putIfAbsent(clientIp, new AtomicInteger(0));
        AtomicInteger currentCount = requestCounts.get(clientIp);

        if (currentCount.incrementAndGet() > MAX_REQUESTS_PER_SECOND) {
            // Set the status to HTTP 429 Too Many Requests
            response.setStatus(429);
            response.getWriter().write("Too many requests - Rate limit exceeded.");
            return;
        }

        // Proceed with the filter chain
        try {
            filterChain.doFilter(request, response);
        } finally {
            // Optionally decrement if requests are very dynamic
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }
}
