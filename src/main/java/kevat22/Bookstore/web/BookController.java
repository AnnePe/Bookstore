package kevat22.Bookstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kevat22.Bookstore.domain.Book;
import kevat22.Bookstore.domain.BookRepository;
import kevat22.Bookstore.domain.CategoryRepository;


@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	
	@RequestMapping(value = "/login")
	public String login(){
		return "/login";
	}
	
	//näytä kaikki kirjat myös ilman endpointia
	@RequestMapping(value = {"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")//tarkastetaan oikeus
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    	repository.deleteById(bookId);
	        return "redirect:../booklist";
	    }   
	@PreAuthorize("hasAuthority('ADMIN')")//tarkastetaan oikeus 
	@RequestMapping(value = "/add")
	    public String addBook(Model model){
	    	model.addAttribute("book", new Book());
	    	model.addAttribute("categorys", crepository.findAll());
	    	return "/addBook";
	    } 
	
	@PreAuthorize("hasAuthority('ADMIN')")//tarkastetaan oikeus 
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(@Valid Book book, BindingResult bindingResult, Model model ){
		if (bindingResult.hasErrors()) {
			System.out.println("Error");
			model.addAttribute("categorys", crepository.findAll());
			return ("/addbook");
		}
	        repository.save(book);
	        return "redirect:/booklist";
	    } 
	@PreAuthorize("hasAuthority('ADMIN')")//tarkastetaan oikeus
	    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	    public String editBook(@PathVariable("id") Long bookId, Model model) {
	    	model.addAttribute("book", repository.findById(bookId));
	    	model.addAttribute("categorys", crepository.findAll());
	    	return "editbook";
	    }   
	
	  //Tallennus kun kirja editoidaan ja validointi 
	@PreAuthorize("hasAuthority('ADMIN')")//tarkastetaan oikeus
	    @RequestMapping(value = "/saveE", method = RequestMethod.POST)
	    public String saveE(@Valid Book book, BindingResult bindingResult, Model model ){
		if (bindingResult.hasErrors()) {
			System.out.println("Error");
			model.addAttribute("categorys", crepository.findAll());
			return ("/editBook");
		}
	        repository.save(book);
	        return "redirect:/booklist";
	    } 
	   
}
