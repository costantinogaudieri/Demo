package com.croco.demo.security.jwt;

import com.croco.demo.config.SecurityUtils;
import io.github.jhipster.config.JHipsterProperties;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private static final String PDV_KEY = "pdv";

    private static final String PDVS_KEY = "listaPv";

    private static final String ROLE_KEY = "ruolo";

    private static final String ROLES_KEY = "listaRuolo";

    private static final String ADFS_USER_KEY = "isAdfs";

    private static final String NAME_KEY = "name";

    private static final String FAMILY_KEY = "surname";

    private String secretKey;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    private final JHipsterProperties jHipsterProperties;

    public TokenProvider(JHipsterProperties jHipsterProperties) {
        this.jHipsterProperties = jHipsterProperties;
    }

    @PostConstruct
    public void init() {
        this.secretKey
                = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();

        this.tokenValidityInMilliseconds
                = 1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe
                = 1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(Authentication authentication, Boolean rememberMe, List<String> roles, String role) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                //.claim(PDVS_KEY, pdvs)
                .claim(ROLE_KEY, role)
                .claim(ROLES_KEY, roles)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities
                = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            // Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            parseToken(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    /**
     *
     * @param autoToken
     * @return
     */
    private Jws<Claims> parseToken(String autoToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(autoToken);
    }

    public String getUtenteCorrente() {
        return SecurityUtils.getCurrentUserLogin();
    }

    public String getRuoloCorrente() {
        return getRuoloCorrente(SecurityUtils.getCurrentUserJWT());
    }

    public String getName() {
        return getName(SecurityUtils.getCurrentUserJWT());
    }

    public String getSurname() {
        return getSurname(SecurityUtils.getCurrentUserJWT());
    }

    public List<String> getRuoli() {
        return getRoles(SecurityUtils.getCurrentUserJWT());
    }

    /**
     *
     * @param autoToken
     * @return
     */

    private String getName(String autoToken) {
        Jws<Claims> claimsJws = parseToken(autoToken);
        Object obj = claimsJws.getBody().get(NAME_KEY);
        return (String) obj;
    }

    private List<String> getRoles(String autoToken) {

        List<String> ruoli = null;
        Jws<Claims> claimsJws = parseToken(autoToken);

        Object obj = claimsJws.getBody().get(ROLES_KEY);
        ruoli = (List<String>) obj;

        return ruoli;
    }

    private String getSurname(String autoToken) {
        Jws<Claims> claimsJws = parseToken(autoToken);
        Object obj = claimsJws.getBody().get(FAMILY_KEY);
        return (String) obj;
    }

    /**
     *
     * @param autoToken
     * @return
     */
    private String getRuoloCorrente(String autoToken) {
        String dto = null;
        Jws<Claims> claimsJws = parseToken(autoToken);

        dto = claimsJws.getBody().get(ROLE_KEY, String.class);
        if (dto == null) {
            return null;
        }
        return dto;
    }

}
