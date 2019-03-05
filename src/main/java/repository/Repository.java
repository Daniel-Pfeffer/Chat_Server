package repository;

import entities.GroupBO;
import entities.UserBO;
import helper.Header;
import helper.Jwt;
import org.bouncycastle.util.encoders.Hex;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.websocket.Session;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Repository {
    private Jwt jwt;
    private EntityManagerFactory emf;
    private EntityManager em;
    private ArrayList<UserBO> users;
    private ArrayList<GroupBO> groups;

    public Repository() {
        emf = Persistence.createEntityManagerFactory("whatsAppClone");
        em = emf.createEntityManager();
        jwt = new Jwt();
        this.users = new ArrayList<>();
    }


    public UserBO login(String body, Session session) {
        List<String> params = Arrays.asList(body.split("\\|"));
        em.getTransaction().begin();
        UserBO user = em.createNamedQuery("User_getUserByEmail", UserBO.class)
                .setParameter("mail", params.get(0))
                .getSingleResult();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Hex.decode(user.getSalt()));
            byte[] hash = md.digest(params.get(1).getBytes(StandardCharsets.UTF_8));

            if (!new String(Hex.encode(hash)).equals(user.getPasswordHash())) {
                em.getTransaction().commit();
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String token = jwt.create(user.getUsername());
        System.out.println(user.getUsername() + " has " + token);
        user.setJwt(token);
        user.setSession(session);
        em.getTransaction().commit();
        this.users.add(user);
        return user;
    }

    public UserBO register(String body, Session session) {
        String[] para = body.split("\\|");
        List<String> params = Arrays.asList(para);
        em.getTransaction().begin();
        int size = em.createNamedQuery("User_getUserByEmail", UserBO.class)
                .setParameter("mail", params.get(1))
                .getResultList()
                .size();
        if (size == 0) {
            UserBO user = new UserBO(params.get(0), params.get(1), params.get(2));
            user.setSession(session);
            em.persist(user);
            em.getTransaction().commit();

            String token = jwt.create(user.getUsername());
            System.out.println(user.getUsername() + " has " + token);
            user.setJwt(token);
            this.users.add(user);
            return user;
        } else {
            em.getTransaction().commit();
            return null;
        }
    }

    public boolean checkSign(Header header) {
        String token = header.getToken();
        return jwt.checkForSubject(token) != null;
    }

    public GroupBO createGroup(String body) {
        return null;
    }
}
