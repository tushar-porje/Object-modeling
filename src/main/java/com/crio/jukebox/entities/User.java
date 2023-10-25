package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.exceptions.UserDoNottHavePlayListException;

public class User extends BaseEntity{
    private final String userName;
    private List<Integer> playLists;

    public User(User user){
        this(user.id, user.userName, user.playLists);
    }
    public User(Integer Id, String userName, List<Integer> playList) {
        this(userName,playList);
        this.id = Id;
    }
    public User(String userName,List<Integer> playList) {
        this(userName);
        this.playLists=playList;
    }
    public User(String userName){
        this.userName=userName;
        this.playLists=new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }
    public List<Integer> getPlayList() {
        return playLists;
    }
    public void addPlayList(PlayList playList){
        playLists.add(playList.getId());
    }

    public void deletePlayList(PlayList playList) throws UserDoNottHavePlayListException{
        boolean flag=false;
        for(int i=0;i<playLists.size();i++){
            if(playLists.get(i)==playList.getId()){
                playLists.remove(i);
                flag=true;
            }
        }
        if(flag==false){
            throw new UserDoNottHavePlayListException("playlist is not present in user's playlist");
        }
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", playListId=" + playLists + ", userId=" + id + ", userName=" + userName
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((playLists == null) ? 0 : playLists.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (playLists == null) {
            if (other.playLists != null)
                return false;
        } else if (!playLists.equals(other.playLists))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }
}
