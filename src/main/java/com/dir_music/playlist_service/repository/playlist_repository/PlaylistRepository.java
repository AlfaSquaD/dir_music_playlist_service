package com.dir_music.playlist_service.repository.playlist_repository;

import com.dir_music.playlist_service.repository.playlist_repository.model.PlaylistModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends MongoRepository<PlaylistModel, ObjectId> {

    void deleteByPlaylistId(ObjectId playlistId);

    Optional<List<PlaylistModel>> findAllByUserIdAndIsPublic(Long userId, boolean isPublic);

    Optional<List<PlaylistModel>> findByPlaylistNameIsLikeIgnoreCase(String playlistName);
}
