//package com.ora.movieapi.config;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import com.google.common.base.Strings;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class SimpleCorsFilter implements Filter {
//
//    @Value("${app.origin}")
//    private String appOrigin;
//
//    public SimpleCorsFilter(){
//
//    }
//
//    @Override
//    public void destroy() {}
//    @Override
//    public void init(FilterConfig filterconfig) throws ServletException {}
//
//
//    @Override
//    public void doFilter
//            (ServletRequest req, ServletResponse res, FilterChain filterchain)
//            throws IOException, ServletException {
//
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpServletRequest request = (HttpServletRequest) req;
//        String origin = request.getHeader("origin");
//
//        if(!Strings.isNullOrEmpty(origin) && (origin.contains("localhost")) || origin.contains(appOrigin)){
//            response.setHeader("Access-Control-Allow-Origin", origin);
//        }
//
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers","*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Expose-Headers", HttpHeaders.CONTENT_DISPOSITION);
//        System.out.println((request.getMethod()));
//        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//        else{
//            filterchain.doFilter(req,res);
//        }
//    }
//
//}