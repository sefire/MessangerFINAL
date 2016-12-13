package by.mnk.htp.glotovs.msr.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import java.util.GregorianCalendar;

/*
 * Created by Sefire on 25.10.2016.
 */

@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "message")
public class MessageEntity implements IEntity<Integer> {

    @Id
    @GenericGenerator(name="increment" , strategy="increment")
    @GeneratedValue(generator="increment")
    @Column(name = "idMessage")
    private int idMessage;

    @ManyToOne
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "idAuthor")
    private UserEntity userEntity;

    @ManyToOne
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "chatId")
    private ChatEntity chatEntity;

    @Column(name = "readStatus")
    private String readStatus;

    @Column(name = "dateTime")
    private Date dateTime;

    @Column(name = "text")
    private String text;

    public MessageEntity(){

    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ChatEntity getChatEntity() {
        return chatEntity;
    }

    public void setChatEntity(ChatEntity chatEntity) {
        this.chatEntity = chatEntity;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public MessageEntity(int idMessage, int chatId, Date date, String text) {
        this.idMessage = idMessage;
        this.dateTime = date;
        this.text = text;
    }

    public Integer getId() {
        return idMessage;
    }

    public void setId(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "idMessage=" + idMessage +
                ", dateTime=" + dateTime +
                ", text='" + text + '\'' +
                '}';
    }
}
