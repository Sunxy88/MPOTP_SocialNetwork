package FinalProject.Review;

import FinalProject.Item.BookItem;
import FinalProject.Item.Item;
import FinalProject.Member.User;
import FinalProject.exceptions.BadEntryException;

public class Review {
    private String comment;
    private double score;
    private User member;
    private Item item;

    public Review(String comment, double score, User member, Item item)
            throws BadEntryException {
        if (comment == null || member == null || item == null)
            throw new BadEntryException("NULL");
        if (comment.trim().length() < 1)
            throw new BadEntryException("La commentaire ne peut pas être vide");
        else if (score < 0 || score > 5)
            throw new BadEntryException("La noté dois être inférieur à 5 et supérieur à 0");
        this.comment = comment;
        this.score = score;
        this.member = member;
        this.item = item;
    }

    public double getScore() {
        return score;
    }

    public Item getItem() {
        return item;
    }

    public User getMember() {
        return member;
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        String str = "Comment: " + comment + "\t" + "Score: " + score + "\t"
                + "Member: " + member.getLogIn() + "\t" + "Item: " + item.getTitle();
        return str;
    }

    public static void main(String[] args) {
        Review r1 = null;
        Item i1 = null;
        User u1 = null;

        try {
            System.out.println("*****TEST CASE 1*****");
            i1 = new BookItem("Thinking in Java", "Education",
                    "Bruce Eckel", 878);
            u1 = new User("Xi", "123", "A cool boy");
            r1 = new Review("Good", 4.8, u1, i1);
            System.out.println(r1);
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 1*****");
            System.out.println("\n");
        }

        try {
            System.out.println("*****TEST CASE 2*****");
            i1 = new BookItem("Thinking in Java", "Education",
                    "Bruce Eckel", 878);
            u1 = new User("Xi", "123", "A cool boy");
            r1 = new Review("  ", 4.8, u1, i1);
            System.out.println(r1);
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 2*****");
            System.out.println("\n");
        }

        try {
            System.out.println("*****TEST CASE 3*****");
            i1 = new BookItem("Thinking in Java", "Education",
                    "Bruce Eckel", 878);
            u1 = new User("Xi", "123", "A cool boy");
            r1 = new Review("Really Good", 5.2, u1, i1);
            System.out.println(r1);
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 3*****");
            System.out.println("\n");
        }

        try {
            System.out.println("*****TEST CASE 4*****");
            i1 = new BookItem("Thinking in Java", "Education",
                    "Bruce Eckel", 878);
//            u1 = new User("Xi", "123", "A cool boy");
            r1 = new Review("Really Good", 4.2, null, i1);
            System.out.println(r1);
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 4*****");
            System.out.println("\n");
        }
    }
}
