package by.mnk.htp.glotovs.msr.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sefire on 25.10.2016.
 */

@Entity
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "friend")
public class FriendEntity implements IEntity<Integer> {

    @Id
    @GenericGenerator(name="increment" , strategy="increment")
    @GeneratedValue(generator="increment")
    @Column(name = "idFriend")
    private int idFriend;


    @ManyToOne
    //@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "idUser")
    private UserEntity userEntity;


    @Column(name = "userFriendId")
    private int userFriendId;

    public FriendEntity(){
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Integer getId() {
        return idFriend;
    }

    public void setId(Integer idFriend) {
        this.idFriend = idFriend;
    }

    public int getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(int userFriendId) {
        this.userFriendId = userFriendId;
    }

    @Override
    public String toString() {
        return "FriendEntity{" +
                "idFriend=" + idFriend +
                ", userFriendId=" + userFriendId +
                '}';
    }
}
