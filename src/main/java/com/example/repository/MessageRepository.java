package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    //named query
    List<Message> findMessagesByPostedBy(int postedBy);
}
