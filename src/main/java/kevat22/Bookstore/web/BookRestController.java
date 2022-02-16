package kevat22.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import kevat22.Bookstore.domain.Book;
import kevat22.Bookstore.domain.BookRepository;
import kevat22.Bookstore.domain.Category;
import kevat22.Bookstore.domain.CategoryRepository;

@RestController
public class BookRestController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// RESTful service to get all books, toimii
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public List<Book> BookListRest() {	
        return (List<Book>) repository.findAll();
    }    

    //toimii
    @PostMapping("books")
    Book addBook(@RequestBody Book addBook) {
    	return repository.save(addBook);
    }

    //toimii
    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book addBook,@PathVariable Long id) {
    	addBook.setId(id);
    	return repository.save(addBook);
    }
    
    //toimii
    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
    	repository.deleteById(id);
    	
    }
    
 // RESTful service to get book by id, toimii
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public Optional<Book> findBookRest(@PathVariable("id") Long Id) {	
    	return repository.findById(Id);
    } 
 // RESTful service to get all categorys, toimii
    @RequestMapping(value="/categorys", method = RequestMethod.GET)
    public List<Category> CategoryListRest() {	
        return (List<Category>) crepository.findAll();
    }   
    
 // RESTful service to get category by id, toimii
    @RequestMapping(value="/category/{id}", method = RequestMethod.GET)
    public Optional<Category> findCategoryRest(@PathVariable("id") Long Id) {	
    	return crepository.findById(Id);
    } 
}
