package com.dir_music.playlist_service.service.playlist_service.input;

import com.dir_music.playlist_service.service.foundation.IServiceInput;
import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistServiceCreateInput implements IServiceInput {
    private String playlistName;
    private Long userId;
    private String userName;
    private boolean isPublic;
}
