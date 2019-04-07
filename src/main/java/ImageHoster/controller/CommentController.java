package ImageHoster.controller;
import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String newCommentImage(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String comment, Comment newComment, HttpSession session) throws IOException {

        User user = (User) session.getAttribute("loggeduser");
        newComment.setUser(user);
        Image image = imageService.getImage(imageId);
        newComment.setImage(image);
        newComment.setText(comment);
        newComment.setCreatedDate(new Date());
        commentService.addComment(newComment);
        return "redirect:/images/";
    }

    @RequestMapping("/images/{imageId}/")
    public String getUserImages(Model model) {
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("images", comments);
        return "images/image";
    }
}