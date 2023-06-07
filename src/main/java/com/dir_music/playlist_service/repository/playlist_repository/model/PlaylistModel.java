package com.dir_music.playlist_service.repository.playlist_repository.model;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "playlists")
public class PlaylistModel {
    @MongoId
    @Field("playlistId")
    private ObjectId playlistId;
    @Field("playlistName")
    private String playlistName;
    @Field("userId")
    private Long userId;
    @Field("userName")
    private String userName;
    @Field("isPublic")
    private boolean isPublic;
    @Field("songIds")
    private List<Long> songIds = List.of();
}
