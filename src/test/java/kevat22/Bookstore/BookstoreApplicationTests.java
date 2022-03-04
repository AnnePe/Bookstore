package kevat22.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kevat22.Bookstore.web.BookController;



@ExtendWith(SpringExtension.class) 
@SpringBootTest
public class BookstoreApplicationTests {//SMOKE TESTING
	@Autowired
	private BookController controller;
	
	

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	 }

	

}
