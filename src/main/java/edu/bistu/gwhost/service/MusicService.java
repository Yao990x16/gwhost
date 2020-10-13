package edu.bistu.gwhost.service;

import edu.bistu.gwhost.dao.entity.MusicEntity;
import edu.bistu.gwhost.dao.repository.MusicRepository;
import edu.bistu.gwhost.domain.Music;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MusicService
{
    private MusicRepository musicRepository;

    @Autowired
    public MusicService(MusicRepository musicRepository)
    {
        this.musicRepository = musicRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Boolean addMusic(String musicName, Music music)
    {
        try
        {
            MusicEntity musicEntity = toMusicEntity(music);
            musicEntity.setMusicName(musicName);
            musicRepository.save(musicEntity);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Music getMusic(Long id)
    {
        Optional<MusicEntity> optional = musicRepository.findById(id);
        if(!optional.isPresent())
            return null;
        MusicEntity musicEntity = optional.get();
        return toMusic(musicEntity);
    }

    private MusicEntity toMusicEntity(Music music)
    {
        MusicEntity musicEntity = new MusicEntity();
        BeanUtils.copyProperties(music, musicEntity);
        return musicEntity;
    }

    private Music toMusic(MusicEntity musicEntity)
    {
        Music music = new Music();
        BeanUtils.copyProperties(musicEntity, music);
        return music;
    }
}
