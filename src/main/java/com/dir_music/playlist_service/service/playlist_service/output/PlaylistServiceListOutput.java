package com.dir_music.playlist_service.service.playlist_service.output;


import com.dir_music.playlist_service.service.foundation.IServiceOutput;
import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistServiceListOutput implements IServiceOutput {
    private List<PlaylistServicePlaylistOutput> playlists;
}
