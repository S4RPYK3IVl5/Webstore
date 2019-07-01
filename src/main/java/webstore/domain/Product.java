package webstore.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Positive
    private int cost;

    @NotNull
    @Positive
    private int weight;

    @ElementCollection(targetClass = Type.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "product_type", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private Set<Type> types;
}