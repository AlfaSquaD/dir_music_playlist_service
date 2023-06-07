package com.dir_music.playlist_service.service.playlist_service;

import com.dir_music.playlist_service.service.foundation.IService;
import com.dir_music.playlist_service.service.playlist_service.input.*;
import com.dir_music.playlist_service.service.playlist_service.output.PlaylistServiceListOutput;
import com.dir_music.playlist_service.service.playlist_service.output.PlaylistServicePlaylistOutput;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public interface PlaylistService extends IService {
    PlaylistServicePlaylistOutput createPlaylist(PlaylistServiceCreateInput input);

    PlaylistServicePlaylistOutput updatePlaylist(PlaylistServiceUpdateInput input)
            throws NoSuchElementException;

    void deletePlaylist(PlaylistServiceDeleteInput input);

    PlaylistServicePlaylistOutput addSongToPlaylist(PlaylistServiceAddSongInput input) throws NoSuchElementException;

    PlaylistServicePlaylistOutput removeSongFromPlaylist(PlaylistServiceRemoveSongInput input) throws NoSuchElementException;

    PlaylistServicePlaylistOutput getPlaylistById(PlaylistServiceGetByIdInput input) throws NoSuchElementException;

    PlaylistServiceListOutput getPlaylistByUserId(PlaylistServiceGetByUserIdInput input);

    PlaylistServiceListOutput searchPlaylist(PlaylistServiceSearchInput input);
}
