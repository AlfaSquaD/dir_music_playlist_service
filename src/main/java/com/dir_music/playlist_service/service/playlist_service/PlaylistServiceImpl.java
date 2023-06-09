package com.dir_music.playlist_service.service.playlist_service;

import com.dir_music.playlist_service.repository.playlist_repository.PlaylistRepository;
import com.dir_music.playlist_service.repository.playlist_repository.model.PlaylistModel;
import com.dir_music.playlist_service.service.playlist_service.input.*;
import com.dir_music.playlist_service.service.playlist_service.output.PlaylistServiceListOutput;
import com.dir_music.playlist_service.service.playlist_service.output.PlaylistServicePlaylistOutput;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    final private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public PlaylistServicePlaylistOutput createPlaylist(PlaylistServiceCreateInput input) {
        final PlaylistModel playlistModel = PlaylistModel.builder()
                .playlistName(input.getPlaylistName())
                .userId(input.getUserId())
                .userName(input.getUserName())
                .isPublic(input.isPublic())
                .songIds(Collections.emptyList())
                .build();

        final PlaylistModel savedPlaylistModel = playlistRepository.save(playlistModel);

        return PlaylistServicePlaylistOutput.fromPlaylistModel(savedPlaylistModel);
    }

    @Override
    public PlaylistServicePlaylistOutput updatePlaylist(PlaylistServiceUpdateInput input) throws NoSuchElementException {
        final ObjectId playlistId = new ObjectId(input.getPlaylistId());
        final PlaylistModel playlistModel = playlistRepository.findById(playlistId).orElseThrow();

        if (!playlistModel.getUserId().equals(input.getRequesterUserId())) {
            throw new NoSuchElementException();
        }

        playlistModel.setPlaylistName(input.getPlaylistName());
        playlistModel.setUserName(input.getUserName());
        playlistModel.setPublic(input.isPublic());


        final PlaylistModel savedPlaylistModel = playlistRepository.save(playlistModel);

        return PlaylistServicePlaylistOutput.fromPlaylistModel(savedPlaylistModel);
    }

    @Override
    public void deletePlaylist(PlaylistServiceDeleteInput input) throws NoSuchElementException {

        final ObjectId playlistId = new ObjectId(input.getPlaylistId());
        final PlaylistModel playlistModel = playlistRepository.findById(playlistId).orElseThrow();

        if (!playlistModel.getUserId().equals(input.getRequesterUserId())) {
            throw new NoSuchElementException();
        }

        playlistRepository.deleteByPlaylistId(playlistId);

    }

    @Override
    public PlaylistServicePlaylistOutput addSongToPlaylist(PlaylistServiceAddSongInput input)
            throws NoSuchElementException {
        final ObjectId playlistId = new ObjectId(input.getPlaylistId());
        final PlaylistModel playlistModel = playlistRepository.findById(playlistId).orElseThrow();

        if (!playlistModel.getUserId().equals(input.getRequesterUserId())) {
            throw new NoSuchElementException();
        }

        playlistModel.getSongIds().add(input.getSongId());

        final PlaylistModel savedPlaylistModel = playlistRepository.save(playlistModel);

        return PlaylistServicePlaylistOutput.fromPlaylistModel(savedPlaylistModel);
    }

    @Override
    public PlaylistServicePlaylistOutput removeSongFromPlaylist(PlaylistServiceRemoveSongInput input) throws NoSuchElementException {
        final ObjectId playlistId = new ObjectId(input.getPlaylistId());
        final PlaylistModel playlistModel = playlistRepository.findById(playlistId).orElseThrow();

        if (!playlistModel.getUserId().equals(input.getRequesterUserId())) {
            throw new NoSuchElementException();
        }

        playlistModel.getSongIds().remove(input.getSongId());

        final PlaylistModel savedPlaylistModel = playlistRepository.save(playlistModel);

        return PlaylistServicePlaylistOutput.fromPlaylistModel(savedPlaylistModel);
    }

    @Override
    public PlaylistServicePlaylistOutput getPlaylistById(PlaylistServiceGetByIdInput input)
            throws NoSuchElementException {
        final ObjectId playlistId = new ObjectId(input.getPlaylistId());
        final PlaylistModel playlistModel = playlistRepository.findById(playlistId).orElseThrow();

        if (!playlistModel.isPublic() && !playlistModel.getUserId().equals(input.getRequesterUserId())) {
            throw new NoSuchElementException();
        }

        return PlaylistServicePlaylistOutput.fromPlaylistModel(playlistModel);
    }

    @Override
    public PlaylistServiceListOutput getPlaylistByUserId(PlaylistServiceGetByUserIdInput input) {
        final List<PlaylistModel> playlistModels = playlistRepository.findAllByUserIdAndIsPublic
                        (input.getUserId(), !input.getRequesterUserId().equals(input.getUserId()))
                .orElse(Collections.emptyList());

        return PlaylistServiceListOutput.builder()
                .playlists(playlistModels.stream()
                        .map(PlaylistServicePlaylistOutput::fromPlaylistModel)
                        .toList())
                .build();


    }

    @Override
    public PlaylistServiceListOutput searchPlaylist(PlaylistServiceSearchInput input) {
        final List<PlaylistModel> playlistModels = playlistRepository
                .findByPlaylistNameIsLikeIgnoreCase((input.getSearchQuery())).orElse(Collections.emptyList());

        return PlaylistServiceListOutput.builder()
                .playlists(playlistModels.stream()
                        .map(playlistModel -> {
                            if (!playlistModel.isPublic() && !playlistModel.getUserId().equals(input.getRequesterUserId())) {
                                return null;
                            }
                            return PlaylistServicePlaylistOutput.fromPlaylistModel(playlistModel);
                        }).filter(Objects::nonNull).toList())
                .build();

    }
}
