package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Feedback;
import za.ac.cput.service.IFeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:8080") // Adjust the port based on your Vue.js frontend's server
public class FeedbackController {

    @Autowired
    private IFeedbackService feedbackService;

    // Create a new feedback entry
    @PostMapping("/create")
    public ResponseEntity<Feedback> create(@RequestBody Feedback feedback) {
        Feedback createdFeedback = feedbackService.create(feedback);
        return ResponseEntity.ok(createdFeedback);
    }

    // Read a feedback entry by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Feedback> read(@PathVariable Integer id) {
        Feedback feedback = feedbackService.read(id);
        return feedback != null ? ResponseEntity.ok(feedback) : ResponseEntity.notFound().build();
    }

    // Update an existing feedback entry
    @PutMapping("/update")
    public ResponseEntity<Feedback> update(@RequestBody Feedback feedback) {
        Feedback updatedFeedback = feedbackService.update(feedback);
        return updatedFeedback != null ? ResponseEntity.ok(updatedFeedback) : ResponseEntity.notFound().build();
    }

    // Delete a feedback entry by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean deleted = feedbackService.delete(id);
        return deleted ? ResponseEntity.ok("Feedback deleted successfully") : ResponseEntity.notFound().build();
    }

    // Get all feedback entries
    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAll() {
        List<Feedback> feedbackList = feedbackService.getAll();
        return ResponseEntity.ok(feedbackList);
    }
}
