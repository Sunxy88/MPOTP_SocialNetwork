package FinalProject.Item;

import FinalProject.Member.User;
import FinalProject.Review.Review;
import FinalProject.exceptions.BadEntryException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class FilmItem extends Item {

    private String director;
    private String scenarist;
    private int duration;
    private static int nbFilm = 0;

    public FilmItem(String title, String type, String director,
                    String scenarist, int duration) throws BadEntryException {
        if (title == null || type == null || director == null || scenarist == null)
            throw new BadEntryException("NULL");
        else if (title.trim().length() < 1)
            throw new BadEntryException("Titile ne peut pas être vide");
        else if (director.trim().length() < 1)
            throw new BadEntryException("Réalisateur ne peut pas être vide");
        else if (scenarist.trim().length() < 1)
            throw new BadEntryException("Scénariste ne peut pas être vide");
        else if (duration <= 0)
            throw new BadEntryException("Nombre de page dois être positif");

        this.title = title.trim();
        this.type = type;
        this.reviews = new LinkedList<>();
        this.score = 0;
        this.director = director.trim();
        this.scenarist = scenarist.trim();
        this.duration = duration;
        nbFilm++;
    }

    public String getDirector() {
        return director;
    }

    public String getScenarist() {
        return scenarist;
    }

    public int getDuration() {
        return duration;
    }

    public static int getNbFilm() {
        return nbFilm;
    }

    public String toString() {
        return "Title: " + getTitle() + "\t" + "Director: " + getDirector() + "\t"
                + "Type: " + getTitle() + "Scenarist: "+ getScenarist() + "\t" +
                "Duration: " + getDuration();
    }

    public static void main(String[] args) {
        Item f1 = null;
        try {
            System.out.println("*****TEST CASE 1*****");
            System.out.println("*****Test for constractor");
            f1 = new FilmItem("Titanic", "Romance", "James Cameron",
                    "James Cameron", 227);
            System.out.println("f1:\n" + f1);
            f1 = new FilmItem(" ", " ", " ", " ", 9);
            System.out.println(f1);

        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 1*****");
            System.out.println("\n");
        }

        try {
            System.out.println("*****TEST CASE 2*****");
            System.out.println("*****Test for constractor");
            f1 = new FilmItem(null, "Romance", "James Cameron",
                    "James Cameron", 227);
            System.out.println("f1:\n" + f1);

        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 2*****");
            System.out.println("\n");
        }

        try {
            System.out.println("*****TEST CASE 3*****");
            System.out.println("*****Test for constractor");
            f1 = new FilmItem("Titanic", "Romance", "James Cameron",
                    "James Cameron", Integer.MIN_VALUE);
            System.out.println("f1:\n" + f1);

        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 3*****");
            System.out.println("\n");
        }

        System.out.println("*****TEST CASE 4*****");
        System.out.println("*****Test for Static Method");
        System.out.println(FilmItem.getNbFilm());
        System.out.println("*****END OF TEST CASE 4*****");
        System.out.println("\n");

        User u1 = null;
        Review r1 = null;
        try {
            System.out.println("*****TEST CASE 5*****");
            System.out.println("*****Test for getComment");
            f1 = new FilmItem("Titanic", "Romance", "James Cameron",
                    "James Cameron", Integer.MIN_VALUE);
            u1 = new User("Xi", "123", "A cool boy");
            r1 = new Review("Good", 4.8, u1, f1);
            assert (f1 != null);
            double averageScore = f1.addReview(r1);
            r1 = new Review("Excelent", 5.0, u1, f1);
            averageScore = f1.addReview(r1);
            LinkedList<String> comments = f1.getComments();
            System.out.println("Average score1: " + averageScore);
            System.out.println("f1:\n" + f1);
            Iterator it = comments.iterator();
            System.out.println("Average score 1: " + averageScore);
            System.out.println("Average score 2: " + f1.getScore());
            System.out.println("Average score 3: " + it.next());
            for (int i = 1; it.hasNext(); i++)
                System.out.println("Comment " + i + ": " + it.next());

        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 5*****");
            System.out.println("\n");
        }
        Item f2 = null;
        try {
            System.out.println("*****TEST CASE 6*****");
            System.out.println("*****Test for equals*****");
            f1 = new FilmItem("Titanic", "Romance", "James Cameron",
                    "James Cameron", 194);
            System.out.println("f1:\n" + f1);
            f2 = new FilmItem("Titanic", "Romance", "James Cameron",
                    "James Cameron", 194);
            System.out.println("f2:\n" + f2);
            System.out.println("f1.euqals(f2)" + Objects.equals(f1, f2));
            f2 = new FilmItem("Avatar", "Fiction", "James Cameron",
                    "James Cameron", 162);
            System.out.println("f2:\n" + f2);
            System.out.println("f1.euqals(f2)" + Objects.equals(f1, f2));
        } catch (BadEntryException e) {
            System.out.println(e);
        } finally {
            System.out.println("*****END OF TEST CASE 6*****");
            System.out.println("\n");
        }

    }
}
