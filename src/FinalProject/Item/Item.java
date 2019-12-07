package FinalProject.Item;

import FinalProject.Review.Review;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public abstract class Item {
    protected String title;
    protected String type;
    protected LinkedList<Review> reviews;
    protected double score;

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public double getScore() {
        return score;
    }

    /**
    * @return A list of comments, the first element is the score of corresponding item.
    * */
    public LinkedList<String> getComments() {
        LinkedList<String> comments = new LinkedList<>();
        String Score = "Score: " + score;
        String comment;
        comments.add(Score);
        for (Review review: reviews) {
            if ((comment = review.getComment()) != null) {
                comments.add(comment);
            }
        }
        return comments;
    }

    public double addReview(Review review) {
        // This line of code may overflow
        score = (score * reviews.size() + review.getScore() ) / (reviews.size() + 1);
        reviews.add(review);
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return title.equalsIgnoreCase(item.title);
    }

    public abstract String toString();
}
