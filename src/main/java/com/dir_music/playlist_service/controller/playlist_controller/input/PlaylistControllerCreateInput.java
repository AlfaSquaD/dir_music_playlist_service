package com.dir_music.playlist_service.controller.playlist_controller.input;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistControllerCreateInput {
    private String playlistName;
    private Long userId;
    private String userName;
    private boolean isPublic;
}
