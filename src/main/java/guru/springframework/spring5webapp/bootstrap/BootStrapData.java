package guru.springframework.spring5webapp.bootstrap;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bookstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234567");
        Publisher mcm = new Publisher("McMilan", "123 Main St", "New York", "NY", "10001"  );
        publisherRepository.save(mcm);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(mcm);
        mcm.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(mcm);

        Author rod = new Author("Rod", "Johnson");
        Book ejb = new Book("J2EE Development without EJB", "6781237");
        Publisher txt = new Publisher("Dummy Pub", "456 Main Ave", "Altanta", "GA", "30001"  );
        rod.getBooks().add(ejb);
        ejb.getAuthors().add(rod);

        ejb.setPublisher(mcm);
        mcm.getBooks().add(ejb);

        authorRepository.save(rod);
        bookRepository.save(ejb);
        publisherRepository.save(mcm);
        publisherRepository.save(txt);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
        System.out.println("Publisher Number of Books: " + mcm.getBooks().size());

    }
}
