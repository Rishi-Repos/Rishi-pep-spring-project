package com.example.service;

import com.example.entity.Message;
import com.example.exception.ClientErrorException;
import com.example.exception.UnsuccessfulMessagePostException;
import com.example.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // 3: Our API should be able to process the creation of new messages.
    public Message createMessage(Message message) throws UnsuccessfulMessagePostException{
        String msgTxt = message.getMessageText();
        if(msgTxt.length()<=255 && msgTxt.length()>0 && accountRepository.existsById(message.getPostedBy())){
            return messageRepository.save(message); //messageId automatically generated and returned
        } 
        throw new UnsuccessfulMessagePostException("message text blank or too long, or invalid accountid");
    }

    // 4: Our API should be able to retrieve all messages.
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // 5: Our API should be able to retrieve a message by its ID.
    public Message getMessageByMessageId(int messageId){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }
        return null;        
    }

    // 6: Our API should be able to delete a message identified by a message ID.
    public int deleteMessageByMessageId(int messageId) throws ClientErrorException{
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        messageRepository.deleteById(messageId);
        if(optionalMessage.isEmpty()) throw new ClientErrorException("invalid messageId");
        return 1;
    }

    // 7: Our API should be able to update a message text identified by a message ID.
    public int updateMessageByMessageId(int messageId, String messageText) throws ClientErrorException{
        int messageLength = messageText.length();
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isEmpty() || messageLength==0 || messageLength>255){
            throw new ClientErrorException("invalid messageId or blank messageText or messageText too long");
        } 
        Message message = optionalMessage.get();
        message.setMessageText(messageText);
        messageRepository.save(message);
        return 1;
    }

    // 8: Our API should be able to retrieve all messages written by a particular user.
    public List<Message> getAllMessagesByAccountId(int accountId){
        return messageRepository.findMessagesByPostedBy(accountId);
    }
}
