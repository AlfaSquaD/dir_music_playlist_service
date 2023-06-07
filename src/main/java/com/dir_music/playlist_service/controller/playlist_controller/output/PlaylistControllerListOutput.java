package com.dir_music.playlist_service.controller.playlist_controller.output;


import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistControllerListOutput {
    private List<PlaylistControllerPlaylistOutput> playlists;
}
