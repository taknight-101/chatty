package com.webTraining.chatty.config.security.jwt;

import java.util.Date;

import com.webTraining.chatty.models.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import io.jsonwebtoken.*;

@Component
public class JwtUtils {

//  @Value("${storefront.app.jwtSecret}")
//  private String jwtSecret;
//
//  @Value("${storefront.app.jwtExpirationMs}")
//  private int jwtExpirationMs;

//  @Value("${storefront.app.saltRounds}")
//  private int rounds;
private String JWT_secret = "top-secret" ;
  public String hash(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt("$2b"));
  }

  public boolean verifyHash(String password, String hash) {
    return BCrypt.checkpw(password, hash);
  }



  public String generateJwtToken(Users theUser) {
    String token = Jwts.builder()
        .claim("id", Integer.toString(theUser.getId()))
        .claim("name", theUser.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + 1000*1000))
        .signWith(SignatureAlgorithm.HS512, JWT_secret)
        .compact();
    return token;
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser()
      .setSigningKey(JWT_secret)
      .parseClaimsJws(token)
      .getBody().get("name", String.class);
  }

  public int getIdFromJwtToken(String token) {
    String theId =  Jwts.parser()
      .setSigningKey(JWT_secret)
      .parseClaimsJws(token)
      .getBody().get("id", String.class);
    return Integer.valueOf(theId);
  }

  public String parseJwt(String headerAuth) {
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }

    return null;
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(JWT_secret).parseClaimsJws(authToken);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
