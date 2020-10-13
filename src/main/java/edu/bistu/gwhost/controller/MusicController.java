package edu.bistu.gwhost.controller;

import edu.bistu.gwhost.domain.Music;
import edu.bistu.gwhost.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MusicController
{
    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService)
    {
        this.musicService = musicService;
    }

    @PostMapping("/add_music")
    public Boolean addMusic(@RequestParam String musicName, @RequestParam MultipartFile multipartFile)
    {
        System.out.print("musicName: " + musicName + ", ");
        if(multipartFile == null)
        {
            System.out.println("file is null");
            return false;
        }
        System.out.println("file size: " + multipartFile.getSize() + "file name: " + multipartFile.getOriginalFilename());

        Music music = new Music();
        try
        {
            byte[] binaryData = multipartFile.getBytes();
            music.setBinaryData(binaryData);
            return musicService.addMusic(musicName, music);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("get_music")
    public ResponseEntity<byte[]> getMusic(@RequestParam Long id)
    {
        System.out.println("getMusic(), id = " + id);

        Music music = musicService.getMusic(id);
        if(music == null)
            return ResponseEntity.badRequest().body(null);

        byte[] binaryData = music.getBinaryData();
        return ResponseEntity.ok()
                .contentType(new MediaType("audio", "mpeg")).body(binaryData);
    }

}
