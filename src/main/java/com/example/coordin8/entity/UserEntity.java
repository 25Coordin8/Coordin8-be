package com.example.coordin8.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",
            columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String userName;

    @Column(name = "major",
            columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String userMajor;

    @Column(name = "bio",
            columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String bio;

    @Column(name = "project_count")
    private Integer projectCount = 0;  // 기본값 0

    @Column(name = "card_count")
    private Integer cardCount = 0;     // 기본값 0

    @ManyToOne
    @JoinColumn(name = "avatar_id")   // FK 컬럼명
    private AvatarEntity avatar;

    // 기본 생성자
    public UserEntity() {}

    // 편의 생성자 (필요 시 사용)
    public UserEntity(String userName, String userMajor, String bio) {
        this.userName = userName;
        this.userMajor = userMajor;
        this.bio = bio;
        this.projectCount = 0;
        this.cardCount = 0;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public AvatarEntity getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarEntity avatar) {
        this.avatar = avatar;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public Integer getCardCount() {
        return cardCount;
    }

    public void setCardCount(Integer cardCount) {
        this.cardCount = cardCount;
    }
}

