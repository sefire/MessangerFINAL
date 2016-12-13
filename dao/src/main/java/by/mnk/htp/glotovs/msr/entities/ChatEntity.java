package by.mnk.htp.glotovs.msr.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sefire on 25.10.2016.*/

@Entity
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "chat")
public class ChatEntity implements IEntity<Integer> {
    @Id
    @GenericGenerator(name="increment" , strategy="increment")
    @GeneratedValue(generator="increment")
    @Column(name = "idChat")
    private int idChat;

    @Column(name = "lastTimeMessage")
    private Date lastTimeMessage;

    @ManyToMany(mappedBy = "chatEntities", fetch = FetchType.EAGER)
    private Set<UserEntity> userEntities = new HashSet<UserEntity>();

    @OneToMany(mappedBy = "chatEntity", fetch = FetchType.EAGER)
    private Set<MessageEntity> messageEntities;



    @Transient
    private String lastTimeMessageString;

    public String getLastTimeMessageString() {
        return lastTimeMessageString;
    }

    public void setLastTimeMessageString(String lastTimeMessageString) {
        this.lastTimeMessageString = lastTimeMessageString;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public Set<MessageEntity> getMessageEntities() {
        return messageEntities;
    }

    public void setMessageEntities(Set<MessageEntity> messageEntities) {
        this.messageEntities = messageEntities;
    }

    public ChatEntity(){

    }

    public ChatEntity(Integer idChat, Date lastTimeMessage) {
        this.idChat = idChat;
        this.lastTimeMessage = lastTimeMessage;
    }

    public Integer getId() {
        return idChat;
    }

    public void setId(Integer id) {
        this.idChat = id;

    }

    public Date getLastTimeMessage() {
        return lastTimeMessage;
    }

    public void setLastTimeMessage(Date lastTimeMessage) {
        this.lastTimeMessage = lastTimeMessage;
    }

    @Override
    public String toString() {
        return "ChatEntity{" +
                "idChat=" + idChat +
                ", lastTimeMessage=" + lastTimeMessage +
                '}';
    }
}
