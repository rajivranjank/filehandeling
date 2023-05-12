//package com.rajiv.psl.auth;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//@AllArgsConstructor
//@NoArgsConstructor
//public class JwtService {
//
//    @Value("${spring.jwt.secret}")
//    private String JWT_SECRET;
//    @Value("${spring.jwt.jwtExpirationInMs}")
//    private String JWT_EXPIRATION_TIME_IN_MILLISECONDS;
//
//
//    private String generateToken(String userName){
//        Map<String, Object> claims=new HashMap<>();
//        return tokenCreater (claims,userName);
//    }
//
//    public String tokenCreater(Map<String,Object> claims,String userName){
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userName)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_TIME_IN_MILLISECONDS))
//                .signWith(getSigndKey(),SignatureAlgorithm.HS256).compact();
//    }
//    private Key getSigndKey() {
//        byte[] keyByte= Decoders.BASE64.decode(JWT_SECRET);
//        return Keys.hmacShaKeyFor(keyByte);
//    }
//
//    public String extractUserNameFromToken(String theToken){
//        return extractClaim(theToken, Claims:: getSubject);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
//        final Claims claims=extractAllClaims(token);
//        return  claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSigndKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//
//    public Date extractExpirationTimeFromToken(String theToken){
//        return extractClaim(theToken,Claims:: getExpiration);
//    }
//    public Boolean validateToken(String theToken, UserDetails userDetails){
//       final String userName = extractUserNameFromToken(theToken);
//       return userName.equals(userDetails.getUsername())&& !isTokenExpired(theToken);
//    }
//
//    private boolean isTokenExpired(String theToken) {
////        return extractExpirationTimeFromToken(theToken).before(new Date());
//        return false;
//    }
//
//
//
//
//
//}
