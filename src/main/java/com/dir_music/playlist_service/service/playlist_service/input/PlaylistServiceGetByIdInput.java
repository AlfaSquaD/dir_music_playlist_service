package com.dir_music.playlist_service.service.playlist_service.input;

import com.dir_music.playlist_service.service.foundation.IServiceInput;
import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistServiceGetByIdInput implements IServiceInput {
    private String playlistId;
    private Long requesterUserId;
}
