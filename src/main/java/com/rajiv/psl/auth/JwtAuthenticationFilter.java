//package com.rajiv.psl.auth;
//
//import io.micrometer.common.util.internal.logging.Slf4JLoggerFactory;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//
//    private final JwtService jwtService;
//
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//        String token=null;
//        String userName=null;
//
//        if (authHeader !=null && authHeader.startsWith("Bearer")){
//            token=authHeader.substring(7);
//            userName=jwtService.extractUserNameFromToken(token);
//            logger.info(token);
//
//        }
//
////        if (userName != null & SecurityContextHolder.getContext().getAuthentication() == null) {
////            UserDetails userDetails = libraryUserDetailsService.loadUserByUsername(userName);
////            if(jwtService.validateToken(token, userDetails)) {
////                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
////                        userDetails.getAuthorities());
////                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                SecurityContextHolder.getContext().setAuthentication(authToken);
////            }
////        }
//        filterChain.doFilter(request, response);
//
//
//
//
//    }
//}
