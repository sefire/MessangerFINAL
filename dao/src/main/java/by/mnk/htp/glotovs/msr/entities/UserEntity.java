package by.mnk.htp.glotovs.msr.entities;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Sefire on 24.10.2016.
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "user")
public class UserEntity implements IEntity<Integer> {

    @Id
    @GenericGenerator(name="increment" , strategy="increment")
    @GeneratedValue(generator="increment")
    @Column(name = "idUser")
    private Integer idUser;

    @Column(name = "phone")
    private String phone;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private String type = "user";

    @Column(name = "access")
    private String access = "true";

    @Column(name = "deletionStatus")
    private String deletionStatus = "false";

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private Set<FriendEntity> friendEntities;

    @OneToMany(mappedBy = "userEntity")
    private Set<MessageEntity> messageEntities;

    @ManyToMany//(cascade = {CascadeType.ALL})
   // @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "user_chat",
            joinColumns = {@JoinColumn(name = "idUser")},
            inverseJoinColumns = {@JoinColumn(name = "idChat")})
    private Set<ChatEntity> chatEntities;


    public UserEntity() {
    }


    public UserEntity(Integer idUser, String phone, String firstName, String lastName, Integer age, String country, String city, String password, String type, String access, String deletionStatus) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.password = password;
        this.type = type;
        this.access = access;
        this.deletionStatus = deletionStatus;
    }

    public Set<FriendEntity> getFriendEntities() {
        return friendEntities;
    }

    public void setFriendEntities(Set<FriendEntity> friendEntities) {
        this.friendEntities = friendEntities;
    }

    public Set<MessageEntity> getMessageEntities() {
        return messageEntities;
    }

    public void setMessageEntities(Set<MessageEntity> messageEntities) {
        this.messageEntities = messageEntities;
    }

    public Set<ChatEntity> getChatEntities() {
        return chatEntities;
    }

    public void setChatEntities(Set<ChatEntity> chatEntities) {
        this.chatEntities = chatEntities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getDeletionStatus() {
        return deletionStatus;
    }

    public void setDeletionStatus(String deletionStatus) {
        this.deletionStatus = deletionStatus;
    }

    public Integer getId() {
        return this.idUser;
    }

    public void setId(Integer idUser) {
        this.idUser = idUser;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        return new EqualsBuilder()
                .append(phone, that.phone)
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(age, that.age)
                .append(country, that.country)
                .append(city, that.city)
                .append(password, that.password)
                .append(type, that.type)
                .append(access, that.access)
                .append(deletionStatus, that.deletionStatus)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(phone)
                .append(firstName)
                .append(lastName)
                .append(age)
                .append(country)
                .append(city)
                .append(password)
                .append(type)
                .append(access)
                .append(deletionStatus)
                .toHashCode();
    }

    @Override
    public String toString() {
        return //"UserEntity{" +
                "idUser = " + idUser + '\'' +
                ", phone = " + phone + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", country = '" + country + '\'' +
                ", city = '" + city + '\'' +
                ", age = " + age //+
                //", password='" + password + '\'' +
                //'}'
                ;
    }
}
