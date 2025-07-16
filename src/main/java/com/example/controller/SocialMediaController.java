package com.example.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.example.entity.*;
import com.example.exception.ClientErrorException;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.UnauthorizedLoginException;
import com.example.exception.UnsuccessfulMessagePostException;
import com.example.exception.UnsuccessfulRegistrationException;
import com.example.service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {
    
    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageSerivce){
        this.accountService = accountService;
        this.messageService = messageSerivce;
    }
    
    // 1: Our API should be able to process new User registrations.
    @PostMapping("register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account){ //no account id
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.registerAccount(account));
        }catch(DuplicateUsernameException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch(UnsuccessfulRegistrationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 2: Our API should be able to process User logins.
    @PostMapping("login")
    public ResponseEntity<Account> registerLogin(@RequestBody Account account){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.login(account));
        }catch(UnauthorizedLoginException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 3: Our API should be able to process the creation of new messages.
    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){ //no message id
        try{
            return ResponseEntity.status(HttpStatus.OK).body(messageService.createMessage(message));
        }catch(UnsuccessfulMessagePostException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 4: Our API should be able to retrieve all messages.
    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessages());
    }

    // 5: Our API should be able to retrieve a message by its ID.
    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable int messageId){
        Message retrievedMessage = messageService.getMessageByMessageId(messageId);
        if(retrievedMessage==null){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(retrievedMessage);
        }
    }

    // 6: Our API should be able to delete a message identified by a message ID.
    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageByMessageId(@PathVariable int messageId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(messageService.deleteMessageByMessageId(messageId));
        } catch(ClientErrorException e){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        
        
    }

    // 7: Our API should be able to update a message text identified by a message ID.
    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Integer> updateMessageByMessageId(@PathVariable int messageId, @RequestBody String messageText){
        try{
            return ResponseEntity.status(200).body(messageService.updateMessageByMessageId(messageId,messageText)); //return # of rows updated if successful
        } catch(ClientErrorException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
    }

    // 8: Our API should be able to retrieve all messages written by a particular user.
    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable int accountId){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessagesByAccountId(accountId));
    }
}
