package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IContestService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","CRIODO2_CONTEST","LOW Monica","40"]
    // or
    // ["CREATE_CONTEST","CRIODO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
        int size=tokens.size();
        if(size==5){
            String contestName=tokens.get(1);
            String level=tokens.get(2);
            String contestCreator=tokens.get(3);
            Integer numQuestion=Integer.parseInt(tokens.get(4));
            try {
                Contest contest=contestService.create(contestName, Level.valueOf(level), contestCreator, numQuestion);
                System.out.println(contest);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else{
            String contestName=tokens.get(1);
            String level=tokens.get(2);
            String contestCreator=tokens.get(3);
            try {
                Contest contest=contestService.create(contestName, Level.valueOf(level), contestCreator, null);
                System.out.println(contest);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
}
