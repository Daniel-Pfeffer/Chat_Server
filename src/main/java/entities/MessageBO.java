package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Websockets_Message")
public class MessageBO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private GroupBO group;
    @ManyToOne
    private UserBO user;

    private String msg;

    @Temporal(TemporalType.DATE)
    private Date timeSent;
}
