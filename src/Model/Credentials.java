package Model;

import java.time.LocalDateTime;

public class Credentials {
    private int id;
    private int user_id;
    private String serviceName;
    private String username;
    private String salt;
    private String passwordHash;
    private String notasHash;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Credentials(int user_id, String serviceName, String username, String passwordHash, String notasHash, String salt , LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user_id = user_id;
        this.serviceName = serviceName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.notasHash = notasHash;
        this.salt = salt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //user id
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    // servicename
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    //username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;

    }
    // passwordhash
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    // notashash
    public String getNotasHash() {
        return notasHash;
    }
    public void setNotasHash(String notasHash) {
        this.notasHash = notasHash;
    }
    // salt
    public String getSalt() {
        return salt;
    }
    public void setSAlt(String salt) {
        this.salt = salt;
    }
    // createdat
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    // updatedat
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
