package edu.bistu.gwhost.dao.entity;

import javax.persistence.*;

@Entity(name = "music")
public class MusicEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String musicName;   //歌曲名

    @Column(nullable = false, columnDefinition = "longblob")
    private byte[] binaryData;  //二进制音乐文件

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }
}
