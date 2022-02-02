package kevat22.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import kevat22.Bookstore.domain.Book;
import kevat22.Bookstore.domain.BookRepository;


@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			
		Book b1=new Book("Kirja", "Anne", 2022,"ISBN-123",1.23);
		Book b2=new Book("Kirja2", "Manne", 2021,"ISBN-456",22.23);
		
		repository.save(b1);
		repository.save(b2);
		};
	}


}
