package com.dir_music.playlist_service.controller.playlist_controller.output;

import com.dir_music.playlist_service.service.playlist_service.output.PlaylistServicePlaylistOutput;
import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistControllerPlaylistOutput {
    private String playlistId;
    private String playlistName;
    private Long userId;
    private String userName;
    private boolean isPublic;
    private List<Long> songIds;


    static public PlaylistControllerPlaylistOutput fromPlaylistServicePlaylistOutput
            (PlaylistServicePlaylistOutput playlistServicePlaylistOutput) {
        return PlaylistControllerPlaylistOutput.builder()
                .playlistId(playlistServicePlaylistOutput.getPlaylistId())
                .playlistName(playlistServicePlaylistOutput.getPlaylistName())
                .userId(playlistServicePlaylistOutput.getUserId())
                .userName(playlistServicePlaylistOutput.getUserName())
                .isPublic(playlistServicePlaylistOutput.isPublic())
                .songIds(playlistServicePlaylistOutput.getSongIds())
                .build();
    }
}
