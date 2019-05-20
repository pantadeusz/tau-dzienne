package pl.puzniakowski.shdemo.domain;

import javax.persistence.*;

@Entity(name = "Book")
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name = "book.all", query = "Select p from Book p"),
        //@NamedQuery(name = "car.unsold", query = "Select c from Book c where c.sold = false")
        @NamedQuery(name = "book.findAvailableBooksByAuthor", query = "Select c from Book c where c.author like :authorNameFragment")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;


    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Book))
            return false;

        return
                id != null &&
                        id.equals(((Book) o).getId());
    }

    @Override
    public int hashCode() {
        return 1337;
    }
}
