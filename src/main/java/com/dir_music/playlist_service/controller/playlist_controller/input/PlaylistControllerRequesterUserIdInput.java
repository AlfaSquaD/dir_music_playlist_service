package com.dir_music.playlist_service.controller.playlist_controller.input;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistControllerRequesterUserIdInput {
    private Long requesterUserId;
}
