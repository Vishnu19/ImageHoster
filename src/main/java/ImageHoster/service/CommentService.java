package ImageHoster.service;

import ImageHoster.model.Comment;


import ImageHoster.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //Call the  method in the commentRepository class to persist the user record in the database
    public void addComment(Comment newComment) {
        commentRepository.addComment(newComment);
    }

    //The method calls the getImage() method in the Repository and passes the id of the image to be fetched
    public List<Comment> getAllComments() {
        return commentRepository.getAllComments();
    }



}