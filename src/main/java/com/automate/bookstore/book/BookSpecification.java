package com.automate.bookstore.book;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookSpecification {
    public Specification<Book> buildFindAllByStringSpecs(String category,
                                                         String author,
                                                         String title,
                                                         Integer priceMin,
                                                         Integer priceMax,
                                                         Integer ratingAbove) {
        List<Specification<Book>> specs = new ArrayList<Specification<Book>>();
            specs.add(Optional.ofNullable(category).map(this::filterByCategory).orElse(null));
            specs.add(Optional.ofNullable(author).map(this::filterByAuthor).orElse(null));
            specs.add(Optional.ofNullable(title).map(this::filterByTitle).orElse(null));
            specs.add(filterByPrice(priceMin, priceMax));
            specs.add(filterByRating(ratingAbove));
        return Specification.allOf(specs);
    }

    private Specification<Book> filterByRating(int ratingAbove) {
        return (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(root.get("rating"), ratingAbove);
        };
    }

    private Specification<Book> filterByPrice(int priceMin, int priceMax) {
        return (root, query, cb) -> {
            return cb.between(root.get("price"), priceMin, priceMax);
        };
    }

    private Specification<Book> filterByCategory(String category) {
        return (root, query, cb) -> {
            return cb.equal(cb.lower(root.get("category")), cb.lower(cb.literal(category)));
        };
    }

    private Specification<Book> filterByAuthor(String author) {
        return (root, query, cb) -> {
            return cb.like(cb.lower(root.get("author")), cb.lower(cb.literal("%" + author + "%")));
        };
    }

    private Specification<Book> filterByTitle(String title) {
        return (root, query, cb) -> {
            return cb.like(cb.lower(root.get("title")), cb.lower(cb.literal("%" + title + "%")));
        };
    }
}
