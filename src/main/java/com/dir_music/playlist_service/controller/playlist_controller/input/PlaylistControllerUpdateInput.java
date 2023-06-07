package com.dir_music.playlist_service.controller.playlist_controller.input;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistControllerUpdateInput {
    private String playlistName;
    private String userName;
    private boolean isPublic;
    private Long requesterUserId;
}
