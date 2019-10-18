package controllers;
import models.Message;
import models.MessageList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.MessageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService ;

    public MessageController(MessageService userService) {
        this.messageService = userService;
    }

    /**
     * Index response to indicate that the server is running.
     * @return "Messages"
     */
    @RequestMapping("")
    public String index() {
        return "Messages";
    }

    @PostMapping("/create")
    public ResponseEntity<Message> registerUser(@Valid @RequestBody Message message) {
        return ResponseEntity.ok(messageService.insert(message));
    }

   @PutMapping("/update")
   public ResponseEntity<Message> updateUser(@Valid @RequestBody Message message) {
       return ResponseEntity.ok(messageService.update(message));
    }

    @PostMapping("/delete")
    public Map<String, Boolean> deleteMessage(@Valid @RequestBody  Message message) {
        boolean result = messageService.delete(message) != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", result);
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Message> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @GetMapping("/get/inbox/{username}")
    public ResponseEntity<MessageList> getUserByName(@PathVariable("username") String name) {
        return ResponseEntity.ok(new MessageList(messageService.getUserInbox(name)));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<MessageList> getAll() {
        return ResponseEntity.ok(new MessageList(messageService.getAll()));
    }
}
