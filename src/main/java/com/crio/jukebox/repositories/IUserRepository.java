package com.crio.jukebox.repositories;

import java.util.Optional;
import com.crio.jukebox.entities.User;

public interface IUserRepository extends CRUDRepository<User,Integer>{
    Optional<User> userHasPlayList(Integer userId,Integer playListId);
    User addPlayList(Integer userId,Integer playListId);
    void removePlayList(Integer userId,Integer PlayListId);
}
