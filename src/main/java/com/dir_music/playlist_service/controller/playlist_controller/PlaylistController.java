package com.dir_music.playlist_service.controller.playlist_controller;

import com.dir_music.playlist_service.controller.playlist_controller.input.PlaylistControllerCreateInput;
import com.dir_music.playlist_service.controller.playlist_controller.input.PlaylistControllerRequesterUserIdInput;
import com.dir_music.playlist_service.controller.playlist_controller.input.PlaylistControllerUpdateInput;
import com.dir_music.playlist_service.controller.playlist_controller.output.PlaylistControllerListOutput;
import com.dir_music.playlist_service.controller.playlist_controller.output.PlaylistControllerPlaylistOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController("PlaylistController")
@RequestMapping("/iplaylists")
public interface PlaylistController {
    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerPlaylistOutput> createPlaylist(@RequestBody PlaylistControllerCreateInput input);

    @DeleteMapping(path = "{playlistId}/delete", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> deletePlaylist(@PathVariable String playlistId, @RequestBody PlaylistControllerRequesterUserIdInput input);

    @PutMapping(path = "{playlistId}/update", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerPlaylistOutput> updatePlaylist(@PathVariable String playlistId,
                                                                    @RequestBody PlaylistControllerUpdateInput input);


    @PostMapping(path = "{playlistId}/add/{songId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerPlaylistOutput> addSongToPlaylist(@PathVariable String playlistId,
                                                                       @PathVariable Long songId,
                                                                       @RequestBody PlaylistControllerRequesterUserIdInput input);

    @PostMapping(path = "{playlistId}/remove/{songId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerPlaylistOutput> removeSongFromPlaylist(@PathVariable String playlistId,
                                                                            @PathVariable Long songId,
                                                                            @RequestBody PlaylistControllerRequesterUserIdInput input);

    @PostMapping(path = "/{playlistId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerPlaylistOutput> getPlaylistById(@PathVariable String playlistId,
                                                                     @RequestBody PlaylistControllerRequesterUserIdInput input);

    @PostMapping(path = "/user/{userId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerListOutput> getPlaylistsByUserId(@PathVariable Long userId,
                                                                      @RequestBody PlaylistControllerRequesterUserIdInput input);

    @PostMapping(path = "/search/{query}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerListOutput> searchPlaylists(@PathVariable String query,
                                                                 @RequestBody PlaylistControllerRequesterUserIdInput input);


    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e);


}
