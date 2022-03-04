package kevat22.Bookstore;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import kevat22.Bookstore.domain.Book;
import kevat22.Bookstore.domain.BookRepository;
import kevat22.Bookstore.domain.Category;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	 private BookRepository repository;

	@Test
	public void findByTitleShouldReturnAuthor() {//toimii
		List<Book> books = repository.findByTitle("Kirja");
		//assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Anne");
		}
	 @Test
	 public void createNewBook() {
	 Book book = new Book("ANnenTESti", "xnne", 2021,"ISBN-456",22.23, new Category("LAPSET"));
	 
	 repository.save(book);
	 assertThat(book.getId()).isNotNull();
	}
	 @Test
	    public void deleteNewBook() {
			List<Book> books = repository.findByTitle("Kirja");
			Book book = books.get(0);
			repository.delete(book);
			List<Book> newBooks = repository.findByTitle("Kirja");
			assertThat(newBooks).hasSize(0);
	     }
}
