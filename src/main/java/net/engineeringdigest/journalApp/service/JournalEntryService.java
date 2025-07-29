package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class JournalEntryService {


    private String dbName;


    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private  UserService userService;
    public void saveEntry(JournalEntry journalEntry){
        JournalEntry saved = journalEntryRepository.save(journalEntry);

    }
    @Transactional
    public void saveEntry(JournalEntry journalEntry,String userName){
        try {
            User user = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            if (user.getJournalEntries() == null) {
                user.setJournalEntries(new ArrayList<>());
            }

            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch(Exception e) {
            System.out.println(e);
            throw  new RuntimeException("An error occured while saving entry",e);
        }

    }

    public List<JournalEntry> getAll(){
        return  journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId myId){
        return journalEntryRepository.findById(myId);
    }

    @Transactional
    public boolean deleteById(ObjectId myId, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed =  user.getJournalEntries().removeIf(x->x.getId().equals(myId));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(myId);
            }
        } catch (Exception e) {
            log.error("Error",e);
            throw new RuntimeException("An error occured while detelting :"+ e);
        }
        return removed;
    }
}
