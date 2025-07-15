package com.example.service;

import com.example.entity.Message;
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
        if(msgTxt.length()<=255 && msgTxt.length()>0 && accountRepository.findAccountByAccountId(message.getPostedBy())!=null){
            return messageRepository.save(message); //messageId automatically generated and returned
        } 
        throw new UnsuccessfulMessagePostException("message text blank or too long, or invalid accountid");
    }

    // 4: Our API should be able to retrieve all messages.
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // 5: Our API should be able to retrieve a message by its ID.
    public Message getMessageByMessageId(long messageId){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }
        return null;        
    }
}
