import java.util.ArrayList;

public class BookCollection implements Books {

  private ArrayList<Books> books = new ArrayList();

  public void addBook(Books book) {
    books.add(book);
  }

  public void checkout() {
    books.forEach(Books::checkout);
  }

  public void returnBook() {
    books.forEach(Books::returnBook);
  }

}

