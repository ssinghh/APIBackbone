package dev.myorg.backbone.controller;

import dev.myorg.backbone.entity.Review;
import dev.myorg.backbone.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/vi/movies")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestParam  String reviewBody , @RequestParam String imdbId ) {
        return new ResponseEntity<Review>(reviewService.createReview( reviewBody, imdbId ), HttpStatus.OK);
    }
}
