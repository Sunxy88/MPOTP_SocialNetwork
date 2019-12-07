package FinalProject.SocialNetwork;

import java.util.Iterator;
import java.util.LinkedList;

import FinalProject.Item.BookItem;
import FinalProject.Item.FilmItem;
import FinalProject.Item.Item;
import FinalProject.Member.User;
import FinalProject.Review.Review;
import FinalProject.exceptions.*;
import FinalProject.exceptions.BadEntryException;


public class SocialNetwork implements ISocialNetwork {

	private LinkedList<User> users;
	private LinkedList<BookItem> books;
	private LinkedList<FilmItem> films;

	/**
	 *
	 * @param login
	 * 			login of the user
	 * @param password
	 * 			password of the user
	 * @return
	 * 			corresponding user
	 * @throws BadEntryException
	 *             <ul>
	 *             <li>if <code>login</code> is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if <code>password</code> is not instantiated or contains less than
	 *             four characters (not taking into account leading or trailing
	 *             blanks)</li>
	 *             <li>if <code>title</code> is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if <code>mark</code> is not greater or equals to 0.0 and lesser or
	 *             equals to 5.0.</li>
	 *             <li>if <code>comment</code> is not instantiated</li>
	 *             </ul>
	 * <br>
	 * @throws NotMemberException
	 *             if <code>login</code> does not match with the login of a registered member
	 *             in <i>SocialNetwork</i> or if <code>password</code> does not correspond to
	 *             his registered password.
	 */
	private User validateMember(String login, String password) throws BadEntryException, NotMemberException {
		User toValidate = new User(login, password, "");
		Iterator itOfUser = users.iterator();
		User exsitedUser = null;
		boolean notMember = true;
		// Check if member exisits
		while (itOfUser.hasNext()) {
			exsitedUser = (User) itOfUser.next();
			if (exsitedUser.equals(toValidate) && exsitedUser.validatePassWord(toValidate)) {
				notMember = false;
				break;
			}
		}
		if (notMember)
			throw new NotMemberException(login + " n'exsite pas.");
		return exsitedUser;
	}


	/**
	 *
	 * @param title
	 * 			title of movie or book
	 * @param item
	 * 			Only two possible values: "Film" or "Book", otherwise something goes wrong
	 * @return
	 * 			corresponding item
	 * @throws NotItemException
	 * 			if item does not exist
	 * @throws BadEntryException
	 * 			if value of item goes wrong
	 */
	private Item itemExist(String title, String item) throws NotItemException, BadEntryException {
		if (title == null || title.trim().length() < 1)
			throw new BadEntryException("Title: " + title + " is not legal");
		if (item == "Book") {
			Iterator it = books.iterator();
			BookItem exist = null;
			while (it.hasNext()) {
				exist = (BookItem) it.next();
				if (exist.getTitle().equalsIgnoreCase(title.trim()))
					return exist;
			}
			throw new NotItemException("Book " + title + " doesn't exist");
		} else if (item == "Film") {
			Iterator it = films.iterator();
			FilmItem exist = null;
			while (it.hasNext()) {
				exist = (FilmItem) it.next();
				if (exist.getTitle().equalsIgnoreCase(title.trim()))
					return exist;
			}
			throw new NotItemException("Film " + title + " doesn't exist");
		} else
			throw new BadEntryException("Wrong item");
	}

	public SocialNetwork() {
		users = new LinkedList<>();
		books = new LinkedList<>();
		films = new LinkedList<>();
	}

	@Override
	public int nbMembers() {
		return users.size();
	}

	@Override
	public int nbFilms() {
		return films.size();
	}

	@Override
	public int nbBooks() {
		return books.size();
	}

	@Override
	public void addMember(String login, String password, String description)
			throws BadEntryException, MemberAlreadyExistsException {
		Iterator it = users.iterator();
		User toAdd = new User(login, password, description);
		// Check if member already exisit
		while (it.hasNext()) {
			if (it.next().equals(toAdd))
				throw new MemberAlreadyExistsException();
		}
		users.add(toAdd);
	}

	@Override
	public void addItemFilm(String login, String password, String title, String kind, String director, String scenarist,
			int duration) throws BadEntryException, NotMemberException, ItemFilmAlreadyExistsException {
		validateMember(login, password);
		// Check if film already exsits
		FilmItem toAdd = new FilmItem(title, kind, director, scenarist, duration);
		Iterator itOfFilm = films.iterator();
		while (itOfFilm.hasNext()) {
			if (itOfFilm.next().equals(toAdd))
				throw new ItemFilmAlreadyExistsException();
		}
		films.add(toAdd);
		return ;
	}

	@Override
	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
		validateMember(login, password);
		BookItem toAdd = new BookItem(title, kind, author, nbPages);
		Iterator itOfBook = books.iterator();
		while (itOfBook.hasNext())
			if (itOfBook.next().equals(toAdd))
				throw new ItemBookAlreadyExistsException();
		books.add(toAdd);
		return ;
	}

	@Override
	public float reviewItemFilm(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		User user = validateMember(login, password);
		Item item = itemExist(title, "Film");
		Review toAdd = new Review(comment, mark, user, item);
		return (float)item.addReview(toAdd);		// The operation here is not proper, but well...
	}

	@Override
	public float reviewItemBook(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		User user = validateMember(login, password);
		Item item = itemExist(title, "Book");
		Review toAdd = new Review(comment, mark, user, item);
		return (float)item.addReview(toAdd);		// The operation here is not proper, but well...
	}

	@Override
	public LinkedList<String> consultItems(String title) throws BadEntryException {
		LinkedList<String> res = new LinkedList<>();
		try {
			Item item = itemExist(title, "Film");
			res.add("Film name: " + item.getTitle() + ", Mark: " + item.getScore());
		} catch (NotItemException e) {
		}

		try {
			Item item = itemExist(title, "Book");
			res.add("Book name: " + item.getTitle() + ", Mark: " + item.getScore());
		} catch (NotItemException e) {

		}
		return res;
	}
}
