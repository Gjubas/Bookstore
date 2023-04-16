package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.AppUser;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository,
			UserRepository urepository) {
		return (args) -> {
			log.info("Save some categories");
			Category category1 = new Category("Art");
			categoryRepository.save(category1);
			Category category2 = new Category("Fantasy");
			categoryRepository.save(category2);
			Category category3 = new Category("Nature");
			categoryRepository.save(category3);
			log.info("Save some books");
			bookRepository.save(new Book("Helmipöllö", "Erkki Korpimäki", 2023, 0001, 34.95, category3));
			bookRepository.save(new Book("Shark: Fear and Beauty", "Jean-Marie Ghislain", 2014, 0002, 25.0, category1));

			urepository.save(new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@bookstore.com", "USER"));
			urepository.save(new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@bookstore.com", "ADMIN"));

			log.info("Fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
