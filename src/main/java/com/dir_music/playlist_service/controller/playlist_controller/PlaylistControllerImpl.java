package com.dir_music.playlist_service.controller.playlist_controller;

import com.dir_music.playlist_service.controller.playlist_controller.input.PlaylistControllerCreateInput;
import com.dir_music.playlist_service.controller.playlist_controller.input.PlaylistControllerRequesterUserIdInput;
import com.dir_music.playlist_service.controller.playlist_controller.input.PlaylistControllerUpdateInput;
import com.dir_music.playlist_service.controller.playlist_controller.output.PlaylistControllerListOutput;
import com.dir_music.playlist_service.controller.playlist_controller.output.PlaylistControllerPlaylistOutput;
import com.dir_music.playlist_service.service.playlist_service.PlaylistService;
import com.dir_music.playlist_service.service.playlist_service.input.*;
import com.dir_music.playlist_service.service.playlist_service.output.PlaylistServiceListOutput;
import com.dir_music.playlist_service.service.playlist_service.output.PlaylistServicePlaylistOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/playlists")
public class PlaylistControllerImpl implements PlaylistController {
    final PlaylistService playlistService;

    @Autowired
    public PlaylistControllerImpl(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public ResponseEntity<PlaylistControllerPlaylistOutput> createPlaylist(PlaylistControllerCreateInput input) {
        final PlaylistServiceCreateInput playlistServiceCreateInput = PlaylistServiceCreateInput.builder()
                .playlistName(input.getPlaylistName())
                .userName(input.getUserName())
                .isPublic(input.isPublic())
                .userId(input.getUserId())
                .build();

        final PlaylistServicePlaylistOutput playlistControllerPlaylistOutput
                = this.playlistService.createPlaylist(playlistServiceCreateInput);

        return ResponseEntity.ok(PlaylistControllerPlaylistOutput
                .fromPlaylistServicePlaylistOutput(playlistControllerPlaylistOutput));

    }

    @Override
    public ResponseEntity<?> deletePlaylist(String playlistId, PlaylistControllerRequesterUserIdInput input) {
        final PlaylistServiceDeleteInput playlistServiceDeleteInput = PlaylistServiceDeleteInput.builder()
                .playlistId(playlistId)
                .requesterUserId(input.getRequesterUserId())
                .build();

        this.playlistService.deletePlaylist(playlistServiceDeleteInput);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PlaylistControllerPlaylistOutput> updatePlaylist
            (String playlistId, PlaylistControllerUpdateInput input) {
        final PlaylistServiceUpdateInput playlistServiceUpdateInput = PlaylistServiceUpdateInput.builder()
                .playlistId(playlistId)
                .userName(input.getUserName())
                .playlistName(input.getPlaylistName())
                .isPublic(input.isPublic())
                .requesterUserId(input.getRequesterUserId())
                .build();

        final PlaylistServicePlaylistOutput playlistServicePlaylistOutput
                = this.playlistService.updatePlaylist(playlistServiceUpdateInput);

        return ResponseEntity
                .ok(PlaylistControllerPlaylistOutput.fromPlaylistServicePlaylistOutput(playlistServicePlaylistOutput));
    }

    @Override
    public ResponseEntity<PlaylistControllerPlaylistOutput> addSongToPlaylist
            (String playlistId, Long songId, PlaylistControllerRequesterUserIdInput input) {
        final PlaylistServiceAddSongInput playlistServiceAddSongInput = PlaylistServiceAddSongInput.builder()
                .playlistId(playlistId)
                .songId(songId)
                .requesterUserId(input.getRequesterUserId())
                .build();

        final PlaylistServicePlaylistOutput playlistServicePlaylistOutput
                = this.playlistService.addSongToPlaylist(playlistServiceAddSongInput);

        return ResponseEntity.ok(PlaylistControllerPlaylistOutput
                .fromPlaylistServicePlaylistOutput(playlistServicePlaylistOutput));
    }

    @Override
    public ResponseEntity<PlaylistControllerPlaylistOutput> removeSongFromPlaylist
            (String playlistId, Long songId, PlaylistControllerRequesterUserIdInput input) {
        final PlaylistServiceRemoveSongInput playlistServiceRemoveSongInput = PlaylistServiceRemoveSongInput.builder()
                .playlistId(playlistId)
                .songId(songId)
                .requesterUserId(input.getRequesterUserId())
                .build();

        final PlaylistServicePlaylistOutput playlistServicePlaylistOutput
                = this.playlistService.removeSongFromPlaylist(playlistServiceRemoveSongInput);

        return ResponseEntity.ok(PlaylistControllerPlaylistOutput
                .fromPlaylistServicePlaylistOutput(playlistServicePlaylistOutput));
    }

    @Override
    public ResponseEntity<PlaylistControllerPlaylistOutput> getPlaylistById(String playlistId, PlaylistControllerRequesterUserIdInput input) {
        final PlaylistServiceGetByIdInput playlistServiceGetByIdInput = PlaylistServiceGetByIdInput.builder()
                .playlistId(playlistId)
                .requesterUserId(input.getRequesterUserId())
                .build();

        final PlaylistServicePlaylistOutput playlistServicePlaylistOutput
                = this.playlistService.getPlaylistById(playlistServiceGetByIdInput);

        return ResponseEntity.ok(PlaylistControllerPlaylistOutput
                .fromPlaylistServicePlaylistOutput(playlistServicePlaylistOutput));
    }

    @Override
    public ResponseEntity<PlaylistControllerListOutput> getPlaylistsByUserId(Long userId, PlaylistControllerRequesterUserIdInput input) {
        final PlaylistServiceGetByUserIdInput playlistServiceGetByUserIdInput = PlaylistServiceGetByUserIdInput.builder()
                .userId(userId)
                .requesterUserId(input.getRequesterUserId())
                .build();

        final PlaylistServiceListOutput playlistServicePlaylistOutput
                = this.playlistService.getPlaylistByUserId(playlistServiceGetByUserIdInput);

        return ResponseEntity.ok(PlaylistControllerListOutput
                .builder()
                .playlists(
                        playlistServicePlaylistOutput
                                .getPlaylists().stream()
                                .map(PlaylistControllerPlaylistOutput::fromPlaylistServicePlaylistOutput)
                                .toList()
                )
                .build());
    }

    @Override
    public ResponseEntity<PlaylistControllerListOutput> searchPlaylists(String query, PlaylistControllerRequesterUserIdInput input) {
        final PlaylistServiceSearchInput playlistServiceSearchInput = PlaylistServiceSearchInput.builder()
                .searchQuery(query)
                .requesterUserId(input.getRequesterUserId())
                .build();

        final PlaylistServiceListOutput playlistServicePlaylistOutput
                = this.playlistService.searchPlaylist(playlistServiceSearchInput);

        return ResponseEntity.ok(PlaylistControllerListOutput
                .builder()
                .playlists(
                        playlistServicePlaylistOutput
                                .getPlaylists().stream()
                                .map(PlaylistControllerPlaylistOutput::fromPlaylistServicePlaylistOutput)
                                .toList()
                )
                .build());
    }

    @Override
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }
}
