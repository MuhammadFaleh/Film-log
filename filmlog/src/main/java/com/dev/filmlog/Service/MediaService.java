package com.dev.filmlog.Service;

import com.dev.filmlog.Api.ApiException;
import com.dev.filmlog.Model.Crew;
import com.dev.filmlog.Model.Genre;
import com.dev.filmlog.Model.Media;
import com.dev.filmlog.Repository.CrewRepository;
import com.dev.filmlog.Repository.GenreRepository;
import com.dev.filmlog.Repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;
    private final GenreRepository genreRepository;
    private final CrewRepository crewRepository;

    public void addMedia(Media media){
        mediaRepository.save(media);
    }

    public List<Media> getAllMedia(){
        return mediaRepository.findAll();
    }

    public Media getMediaById(Integer mediaId){
        return mediaRepository.findMediaById(mediaId);
    }

    public void updateMedia(Integer mediaId, Media media){
        Media oldMedia =mediaRepository.findMediaById(mediaId);
        if (oldMedia==null){
            throw new ApiException("Media not found");
        }

        oldMedia.setName(media.getName());
        oldMedia.setDescription(media.getDescription());
        oldMedia.setRuntime(media.getRuntime());
        oldMedia.setAgeRating(media.getAgeRating());
        oldMedia.setType(media.getType());
        oldMedia.setReleaseDate(media.getReleaseDate());
        oldMedia.setImageUrl(media.getImageUrl());
        mediaRepository.save(oldMedia);
    }

    public void deleteMedia(Integer mediaId){
        Media media =mediaRepository.findMediaById(mediaId);
        if (media ==null){
            throw new ApiException("Media not found");
        }

        mediaRepository.delete(media);
    }

    public void assignGenreToMedia(Integer genreId, Integer mediaId){
        Genre genre=genreRepository.findGenreById(genreId);
        Media media=mediaRepository.findMediaById(mediaId);

        genre.getMedias().add(media);
        media.getGenres().add(genre);
        mediaRepository.save(media);
        genreRepository.save(genre);
    }

    public void unAssignGenreToMedia(Integer genreId, Integer mediaId){
        Genre genre=genreRepository.findGenreById(genreId);
        Media media=mediaRepository.findMediaById(mediaId);

        genre.getMedias().remove(media);
        media.getGenres().remove(genre);
        mediaRepository.save(media);
        genreRepository.save(genre);
    }

    public void assignCrewToMedia(Integer crewId, Integer mediaId){
        Crew crew =crewRepository.findCrewById(crewId);
        Media media=mediaRepository.findMediaById(mediaId);

        crew.getMedias().add(media);
        media.getCrews().add(crew);
        mediaRepository.save(media);
        crewRepository.save(crew);
    }

    public void unAssignCrewToMedia(Integer crewId, Integer mediaId){
        Crew crew =crewRepository.findCrewById(crewId);
        Media media=mediaRepository.findMediaById(mediaId);

        crew.getMedias().remove(media);
        media.getCrews().remove(crew);
        mediaRepository.save(media);
        crewRepository.save(crew);
    }

}
