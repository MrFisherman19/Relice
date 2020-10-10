package com.mrfisherman.relice.Configuration.Security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@PropertySource(value={"classpath:application.properties"})
public class JwtTokenUtil {

    private String secret;
    private int jwtExpirationInMs;

    public JwtTokenUtil() {
        this.secret = "ReliceAppSecret";
        this.jwtExpirationInMs = Integer.MAX_VALUE;
    }

    public void setSecret(String secret) {
        this.secret = "ReliceAppSecret";
    }

    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = 0;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            claims.put("isUser", true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            claims.put("isAdmin", true);
        }
        System.out.println(roles + " from generated token.");
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)).signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            System.out.println(jwtToken);
            return true;
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException exception) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", exception);
        }
    }

    public String getUsernameFromToken(String jwtToken) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
        return claims.getSubject();
    }

    public Collection<? extends GrantedAuthority> getRolesFromToken(String jwtToken) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
        System.out.println(jwtToken);
        List<SimpleGrantedAuthority> roles = null;

        Boolean isAdmin = claims.get("isAdmin", Boolean.class);
        Boolean isUser = claims.get("isUser", Boolean.class);

        if (isAdmin != null && isAdmin) {
            roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (isUser != null && isUser) {
            roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return roles;
    }


}
