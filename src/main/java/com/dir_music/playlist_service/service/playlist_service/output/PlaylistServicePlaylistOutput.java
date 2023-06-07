package com.dir_music.playlist_service.service.playlist_service.output;

import com.dir_music.playlist_service.repository.playlist_repository.model.PlaylistModel;
import com.dir_music.playlist_service.service.foundation.IServiceOutput;
import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistServicePlaylistOutput implements IServiceOutput {
    private String playlistId;
    private String playlistName;
    private Long userId;
    private String userName;
    private boolean isPublic;
    private List<Long> songIds;

    public static PlaylistServicePlaylistOutput fromPlaylistModel(PlaylistModel playlistModel) {
        return PlaylistServicePlaylistOutput.builder()
                .playlistId(playlistModel.getPlaylistId().toHexString())
                .playlistName(playlistModel.getPlaylistName())
                .userId(playlistModel.getUserId())
                .userName(playlistModel.getUserName())
                .isPublic(playlistModel.isPublic())
                .songIds(playlistModel.getSongIds())
                .build();
    }
}
