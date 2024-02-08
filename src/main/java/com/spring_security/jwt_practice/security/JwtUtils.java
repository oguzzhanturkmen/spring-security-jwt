package com.spring_security.jwt_practice.security;

import com.spring_security.jwt_practice.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;



import java.util.Date;

@Component
public class JwtUtils {

    private String jwtSecret = "sboot";
    private long jwtExpirationMs = 86400000;

    //------- GENERATE TOKEN -------
    /*
    The authenticated user is passed to the generateJwtToken() method, and the method returns a JWT after setting the user's username, the token's expiration date, and the secret key.
    The Authentication  object comes from the Spring Security context and contains the authenticated user's username and password.

     */
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new java.util.Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret).compact();
    }
    //------- VALIDATE TOKEN -------
    /*
    Valide edilirken yapilan adimlar :

        1)  Tokenin Yapısının Kontrolü: İlk adımda, JWT'nin doğru bir şekilde üç bölümden (header, payload, signature)
        oluşup oluşmadığı kontrol edilir. Bu bölümler nokta işareti (.) ile ayrılır.

        2)  İmzanın Doğrulanması: JWT, oluşturulurken belirlenen bir algoritma ve secret key (gizli anahtar)
        kullanılarak imzalanır. Sunucu, kendisine iletilen tokenin imzasını, aynı algoritma ve secret key kullanarak
        doğrular. Eğer imza geçerliyse, tokenin içeriği güvenilir kabul edilir. Bu adım, tokenin bütünlüğünün
        korunduğunu ve değiştirilmediğini garanti eder.

        3)  Son Kullanma Tarihinin Kontrolü (Expiration Time - exp): JWT'nin payload kısmında, tokenin ne zaman sona
        ereceğini belirten bir son kullanma tarihi (exp) bulunabilir. Tokenin geçerlilik süresi kontrol edilir ve
        sürenin dolmuş olması durumunda token reddedilir.

     */

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;

    }
    //------- GET USERNAME FROM TOKEN -------

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }


}
