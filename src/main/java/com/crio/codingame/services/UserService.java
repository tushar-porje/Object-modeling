package com.crio.codingame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.ContestStatus;
import com.crio.codingame.entities.RegisterationStatus;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.IContestRepository;
import com.crio.codingame.repositories.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IContestRepository contestRepository;

    public UserService(IUserRepository userRepository, IContestRepository contestRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
    }
    // TODO: CRIO_TASK_MODULE_SERVICES
    // Create and store User into the repository.
    @Override
    public User create(String name) {
        User user=new User(name, 0);
        return this.userRepository.save(user);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Users in Ascending Order w.r.t scores if ScoreOrder ASC.
    // Or
    // Get All Users in Descending Order w.r.t scores if ScoreOrder DESC.

    @Override
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder){
            List<User> user=new ArrayList<>();
            user=this.userRepository.findAll();
        if(scoreOrder==scoreOrder.ASC){
            return user.stream().sorted((a, b) -> (a.getScore()).compareTo(b.getScore())).collect(Collectors.toList());
        }else{
            return user.stream().sorted((a, b) -> (b.getScore()).compareTo(a.getScore())).collect(Collectors.toList());
        }
    }

    @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.REGISTERED);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Withdraw the user from the contest
    // Hint :- Refer Unit Testcases withdrawContest method

    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Optional<User> userOptional = userRepository.findByName(userName);
        Optional<Contest> contestOptional = contestRepository.findById(contestId);
        if(contestOptional.isPresent()){
            Contest contest = contestOptional.get();
            if(userOptional.isPresent()){
                User user = userOptional.get();
                if(user.checkIfContestExists(contest) && !user.equals(contest.getCreator()) && 
                    contest.getContestStatus().equals(ContestStatus.NOT_STARTED)){
                        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(contest.getName(),user.getName(), RegisterationStatus.NOT_REGISTERED);
                        user.deleteContest(contest);
                        this.userRepository.save(user);
                        return userRegistrationDto;
                }else{
                    throw new InvalidOperationException();
                }
            }else{
                throw new UserNotFoundException();
            }
        }else{
            throw new ContestNotFoundException();
        }
    }
    /*@Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        UserRegistrationDto userRegistrationDto;
        Optional<User> user=this.userRepository.findByName(userName);
        Optional<Contest> contest=this.contestRepository.findById(contestId);
        if(contest!=null){
            if(user!=null){
                if(user.get().checkIfContestExists(contest.get()) && !user.get().equals(contest.get().getCreator()) && 
                    contest.get().getContestStatus()==ContestStatus.NOT_STARTED){
                        userRegistrationDto = new UserRegistrationDto(contest.get().getName(),user.get().getName(), RegisterationStatus.NOT_REGISTERED);
                        this.userRepository.delete(user.get());
                        user.get().deleteContest(contest.get());
                        this.userRepository.save(user.get());
                }else{
                    throw new InvalidOperationException();
                }
            }else{
                throw new UserNotFoundException();
            }
        }else{
            throw new ContestNotFoundException();
        }

        return userRegistrationDto;
    } */
    
}
