package kevat22.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import kevat22.Bookstore.domain.Book;
import kevat22.Bookstore.domain.BookRepository;
import kevat22.Bookstore.domain.Category;
import kevat22.Bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
    public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
		log.info("save a couple of categorys");
		crepository.save(new Category("LAPSET"));
		crepository.save(new Category("AIKUISET"));
		
		
		brepository.save(new Book("Kirja", "Anne", 2022,"ISBN-123",1.23,crepository.findByName("LAPSET").get(0)));
		brepository.save(new Book("Kirja2", "Manne", 2021,"ISBN-456",22.23,crepository.findByName("AIKUISET").get(0)));
		
		log.info("fetch all books");
		for (Book book : brepository.findAll()) {
			log.info(book.toString());
		}
		};
	}

}
