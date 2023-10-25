package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<Integer,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<Integer,User>();
    }

    @Override
    public User save(User entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            User u = new User(autoIncrement,entity.getUserName(),entity.getPlayList());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        return userMap.entrySet().stream().map(a -> a.getValue()).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(Integer id) {
        return userMap.entrySet().stream().map(a -> a.getKey()).anyMatch(a->a.equals(id));
    }


    @Override
    public long count() {
        return userMap.size();
    }

    @Override
    public void delete(User entity) {
        userMap.entrySet().removeIf(a -> a.getValue().equals(entity)); 
        
    }

    @Override
    public void deleteById(Integer id) {
        userMap.remove(id);
        
    }

    @Override
    public Optional<User> userHasPlayList(Integer userId,Integer playListId) {
            User user=userMap.get(userId);
            List<Integer> userplaylists=user.getPlayList();
            if(userplaylists.contains(playListId)){
                return Optional.ofNullable(user);
            }
            return Optional.ofNullable(null);
    }

    @Override
    public User addPlayList(Integer userId,Integer playListId) {
        User user=userMap.get(userId);
        List<Integer> playList=user.getPlayList();
        playList.add(playListId);
        User modifiedUser=new User(userId,user.getUserName(),playList);
        userMap.put(userId, modifiedUser);
        return modifiedUser;
    }

    @Override
    public void removePlayList(Integer userId, Integer PlayListId) {
        User user=userMap.get(userId);
        List<Integer> playList=user.getPlayList();
        playList.remove(PlayListId);
        User modifiedUser=new User(userId,user.getUserName(),playList);
        userMap.put(userId, modifiedUser);
    }
    
}
