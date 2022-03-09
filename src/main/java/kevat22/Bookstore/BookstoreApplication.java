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
import kevat22.Bookstore.domain.User;
import kevat22.Bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
    public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
		log.info("save a couple of categorys");
		crepository.save(new Category("LAPSET"));
		crepository.save(new Category("AIKUISET"));
		
		
		brepository.save(new Book("Kirja", "Anne", 2022,"ISBN-123",1.23,crepository.findByName("LAPSET").get(0)));
		brepository.save(new Book("Kirja2", "Manne", 2021,"ISBN-456",22.23,crepository.findByName("AIKUISET").get(0)));
		brepository.save(new Book("Kirja3", "Manne", 2021,"ISBN-457",22.24,crepository.findByName("AIKUISET").get(0)));
		brepository.save(new Book("Kirja4", "Manne", 2021,"ISBN-458",22.25,crepository.findByName("AIKUISET").get(0)));
		
		// Create users: admin/admin user/user
		User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		User user3 = new User("anne", "$2a$10$2gZC/6ONPe2eV6EDAN1x5es7R9y57ToKZfTmzSV1Wi.r25imRNf5G", "ADMIN");
		User user4 = new User("vieras", "$2a$10$yYZ3YWUg0lcHCNfja4KmMeZffxgJrBmh9FjigCKw4otIBpydrloz2", "USER");
		urepository.save(user1);
		urepository.save(user2);
		urepository.save(user3);
		urepository.save(user4);
		
		log.info("fetch all books");
		for (Book book : brepository.findAll()) {
			log.info(book.toString());
		}
		};
	}

}
