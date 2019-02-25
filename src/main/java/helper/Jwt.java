package helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Jwt {
    private String secret = null;

    public Jwt() {
        try (InputStream inputStream = new FileInputStream("src/main/resources/properties/config.properties")) {

            Properties properties = new Properties();
            properties.load(inputStream);
            secret = properties.getProperty("jwt_secret");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String create(String sub) {
        return Jwts.builder()
                .setSubject(sub)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String checkForSubject(String jwt) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();

    }
}
