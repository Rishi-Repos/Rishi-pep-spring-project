package com.example.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.example.entity.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {
    
    // 1: Our API should be able to process new User registrations.
    @PostMapping(value = "/register/")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account){ //no account id
        return null;
    }

    // 2: Our API should be able to process User logins.
    @PostMapping(value = "/login/")
    public ResponseEntity<Account> registerLogin(@RequestBody Account account){
        return null;
    }

    // 3: Our API should be able to process the creation of new messages.
    @PostMapping(value = "/messages/")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){ //no message id
        return null;
    }

    // 4: Our API should be able to retrieve all messages.
    @GetMapping(value = "/messages/")
    public ResponseEntity<List<Message>> getAllMessages(){
        return null;
    }

    // 5: Our API should be able to retrieve a message by its ID.
    @GetMapping(value = "/messages/{messageId}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable int messageId){
        return null;
    }

    // 6: Our API should be able to delete a message identified by a message ID.
    @DeleteMapping(value = "/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageByMessageId(@PathVariable int messageId){
        return null; //return # of rows deleted if successful
    }

    // 7: Our API should be able to update a message text identified by a message ID.
    @PatchMapping(value = "/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageByMessageId(@PathVariable int messageId, @RequestBody String messageText){
        return null; //return # of rows updated if successful
    }

    // 8: Our API should be able to retrieve all messages written by a particular user.
    @GetMapping(value = "/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable int accountId){
        return null;
    }
}
