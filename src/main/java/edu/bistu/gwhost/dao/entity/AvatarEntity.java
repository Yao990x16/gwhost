package edu.bistu.gwhost.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_avatar")
public class AvatarEntity
{
    @Id
    private Long id;    //此变量等于用户ID

    @Column(columnDefinition = "longblob", nullable = false)
    private byte[] binaryData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }
}
