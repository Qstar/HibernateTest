package entity;


import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
public class Students {
    private int sid;
    private String address;
    private Date birthday;
    private String gender;
    private String sname;
    private Blob picture;

    public Students() {
    }

    public Students(int sid, String address, Date birthday, String gender, String sname) {
        this.sid = sid;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.sname = sname;
    }

    @Id
    @Column(name = "sid", nullable = false)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "address", nullable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = 255)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "sname", nullable = true, length = 255)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Lob
    @Column(name = "picture", nullable = true, length = 255)
    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }
}
