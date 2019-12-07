package FinalProject.Member;

import FinalProject.Item.FilmItem;
import FinalProject.Item.Item;
import FinalProject.Review.Review;
import FinalProject.exceptions.BadEntryException;
import FinalProject.exceptions.NotMemberException;
import java.util.Iterator;
import java.util.LinkedList;

public class User {
    private String logIn;
    private String passWord;
    private String description;
    private LinkedList<Review> reviews;
    private static int nbUser = 0;

    public User(String logIn, String passWord, String description) throws BadEntryException {
        if (logIn == null || passWord == null || description == null)
            throw new BadEntryException("NULL");
        else if (logIn.trim().length() < 1)
            throw new BadEntryException("Le login ne peut pas être vide");
        else if (passWord.trim().length() < 4)
            throw new BadEntryException("Le mot de pass ne peut pas être vide");


        this.logIn = logIn.trim();
        this.passWord = passWord;
        this.description = description.trim();
        this.reviews = new LinkedList<>();
        nbUser++;
    }

    public String getLogIn() {
        return logIn;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getDescription() {
        return description;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public LinkedList<String> getReview() {
        LinkedList<String> comments = new LinkedList<>();
        Iterator it = reviews.iterator();
        while (it.hasNext()) {
            comments.add(it.next().toString());
        }
        return comments;
    }

    public String toString() {
        return "User name: " + getLogIn() + "\t" + "Description: " + getDescription();
    }

    public boolean validatePassWord(User user) throws BadEntryException, NotMemberException {
        if (!equals(user))
            throw new BadEntryException("Login not match");
        else if (!getPassWord().equals(user.passWord))
            throw new NotMemberException("Password not correct");
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return logIn.equalsIgnoreCase(user.getLogIn());
    }

    public static int getNbUser() {
        return nbUser;
    }

    public static void main(String[] args) {
        User u1 = null;

        try {
            System.out.println("*****TEST CASE 1*****");
            System.out.println("*****Test for Constructor*****");
            u1 = new User("Xi", "123", "A cool boy");
            System.out.println(u1);
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 1*****");
            System.out.println("\n");
        }

        try {
            System.out.println("*****TEST CASE 2*****");
            System.out.println("*****Test for Constructor*****");
            u1 = new User(null, "123", "A cool boy");
            System.out.println(u1);
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 2*****");
            System.out.println("\n");
        }

        try {
            System.out.println("*****TEST CASE 3*****");
            System.out.println("*****Test for Constructor*****");
            u1 = new User("  90", "===", "   ");
            System.out.println(u1);
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 3*****");
            System.out.println("\n");
        }

        User u2 = null;
        try {
            System.out.println("*****TEST CASE 4*****");
            System.out.println("*****Test for equals*****");
            u1 = new User("Xi", "123", "A cool boy");
            System.out.println("u1:\n" + u1);
            u2 = new User("Xixi", "123", "A cooler boy");
            System.out.println("u2:\n" + u2);
            System.out.println("u1.equals(u2): " + u1.equals(u2));

            u2 = new User("Xi", "123", "A cooler boy");
            System.out.println("u2:\n" + u2);
            System.out.println("u1.equals(u2): " + u1.equals(u2));
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 4*****");
            System.out.println("\n");
        }

        Review r1 = null;
        Item i1 = null;
        try {
            System.out.println("*****TEST CASE 5*****");
            System.out.println("*****Test for addReview and getReview*****");
            u1 = new User("Xi", "123", "A cool boy");
            i1 = new FilmItem("Titanic", "Romance", "James Cameron",
                    "James Cameron", 227);
            r1 = new Review("Good", 4.8, u1, i1);
            i1.addReview(r1);
            u1.addReview(r1);
            r1 = new Review("Excelent", 5, u1, i1);
            i1.addReview(r1);
            u1.addReview(r1);
            LinkedList<String> comments = u1.getReview();
            Iterator it = comments.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            System.out.println("u1:\n" + u1);
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 5*****");
            System.out.println("\n");
        }
    }
}
