package com.springsecuritydemo.securitydemo.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

@Component
public class MyFilterConfiguration implements Filter {

    private static final Logger logger = Logger.getLogger(MyFilterConfiguration.class.getName());

    private static final HashMap<String , Integer> requestsMap = new HashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ip = request.getRemoteAddr();

        if (!requestsMap.containsKey(ip))
            requestsMap.put(ip, 1);
        else
            requestsMap.put(ip, requestsMap.get(ip) + 1);

        logger.info(ip + " for " + requestsMap.get(ip));

        if (requestsMap.get(ip) > 10)
            request.getRequestDispatcher("/myhost").forward(request, response);
        else
            chain.doFilter(request, response);
    }
}
