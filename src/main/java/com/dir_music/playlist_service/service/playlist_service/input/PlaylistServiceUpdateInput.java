package com.dir_music.playlist_service.service.playlist_service.input;

import com.dir_music.playlist_service.service.foundation.IServiceInput;
import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistServiceUpdateInput implements IServiceInput {
    private String playlistId;
    private String playlistName;
    private String userName;
    private boolean isPublic;
    private Long requesterUserId;
}
