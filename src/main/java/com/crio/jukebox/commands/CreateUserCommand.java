package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }
    @Override
    public void execute(List<String> tokens) {
        try {
            String name=tokens.get(1);
            User user = userService.createUser(name);
            System.out.println(user.getId()+" "+user.getUserName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
