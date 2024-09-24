package dev.myorg.backbone.service;

import dev.myorg.backbone.entity.Movie;
import dev.myorg.backbone.entity.Review;
import dev.myorg.backbone.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ReviewRepository reviewRepo;
    public Review createReview(String reviewTxt, String imdbId)
    {
        Review review = reviewRepo.save(new Review(reviewTxt));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review.getId()))
                .first();
        return review;

    }
}
