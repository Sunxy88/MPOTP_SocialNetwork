package FinalProject.Item;

import FinalProject.Member.User;
import FinalProject.Review.Review;
import FinalProject.exceptions.BadEntryException;

import java.util.Iterator;
import java.util.LinkedList;

public class BookItem extends Item {
    private String author;
    private int nbPages;
    private static int nbBook = 0;

    public static int getBookNumber() {
        return nbBook;
    }
    public BookItem(String title, String kind, String author, int nbPages)
            throws BadEntryException {
        if (title == null || kind == null || author == null)
            throw new BadEntryException("NULL");
        else if (title.trim().length() < 1)
            throw new BadEntryException("Titile ne peut pas être vide");
        else if (author.trim().length() < 1)
            throw new BadEntryException("Author ne peut pas être vide");
        else if (nbPages <= 0)
            throw new BadEntryException("Nombre de page dois être positif");

        this.title = title.trim();
        this.type = kind;
        this.author = author.trim();
        this.score = 0;
        this.nbPages = nbPages;
        this.reviews = new LinkedList<>();
        nbBook++;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageNumber() {
        return nbPages;
    }

    public String toString() {
        return "Title: " + getTitle() + "\t" + "Author: " + getAuthor() + "\t"
                + "Type: " + getType()+ "\t" + "Pages: " + getPageNumber();
    }

    public static void main(String[] args) {
        Item i1 = null;
        Item i2 = null;
        try {
            System.out.println("*****TEST CASE 1*****");
            System.out.println("*****Test for Constructor*****");
            i1 = new BookItem("Thinking in Java", "Education",
                    "Bruce Eckel", 878);
            System.out.println(getBookNumber());
            i1 = new BookItem(null, "Education",
                    "Bruce Eckel", 878);
            System.out.println(i1);
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 1*****");
            System.out.println("\n");
        }

        try {
            System.out.println("*****TEST CASE 2*****");
            System.out.println("*****Test for Constructor*****");
            i2 = new BookItem("Thinking in Java", "Education",
                    "Bruce Eckel", -2);
            System.out.println(getBookNumber());
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 2*****");
            System.out.println("\n");
        }

        System.out.println("*****TEST CASE 3*****");
        System.out.println("*****Test for Static Method****");
        System.out.println(BookItem.getBookNumber());
        System.out.println("*****END OF TEST CASE 3*****");
        System.out.println("\n");

        try {
            System.out.println("*****TEST CASE 4*****");
            System.out.println("*****Test for Equals in Item*****");
            i1 = new BookItem("Thinking in Java", "Education",
                    "Bruce Eckel", 878);
            System.out.println("i1:\n" + i1);
            i2 = new BookItem("Thinking in Java", "Education",
                    "Bruce Eckel", 878);
            System.out.println("i2:\n" + i2);
            System.out.println("i1.euqals(i2): " + i1.equals(i2));
            i1 = new BookItem("Head First Java", "Education",
                    "O'Reilly", 878);
            System.out.println("i1:\n" + i1);
            System.out.println("i1.euqals(i2): " + i1.equals(i2));
            System.out.println("i1.euqals(\"haha\")" + i1.equals("haha"));
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 4*****");
            System.out.println("\n");
        }

        Review r1 = null;
        User u1 = null;
        try {
            System.out.println("*****TEST CASE 5*****");
            System.out.println("*****Test for Review*****");
            u1 = new User("Xi", "123", "A cool boy");
            r1 = new Review("Good", 4.8, u1, i1);
            assert (i1 != null);
            double averageScore = i1.addReview(r1);
            r1 = new Review("Excelent", 5.0, u1, i1);
            averageScore = i1.addReview(r1);
            LinkedList<String> comments = i1.getComments();
            System.out.println("Average score1: " + averageScore);


            Iterator it = comments.iterator();
            int i = 0;
            System.out.println("Average score2: " + it.next());
            System.out.println("Commnets: ");
            while (it.hasNext())
                System.out.println("Comment " + (++i) + ": " + it.next());
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 5*****");
            System.out.println("\n");
        }
    }
}
