package webstore.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @PositiveOrZero
    private int cost;

    @NotNull
    @PositiveOrZero
    private int weight;

    @NotNull
    private String description;

    @ElementCollection(targetClass = Type.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "product_type", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private Set<Type> types;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value= FetchMode.SELECT)
    @JoinTable(
            name = "liked_product",
            joinColumns = { @JoinColumn(name = "liked_product_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> likedByUser;

    public List<String> getTypes() {

        List<String> stringsTypes = new LinkedList<>();

        for (Type type : types){
            stringsTypes.add(type.getValue());
        }

        return stringsTypes;
    }
}