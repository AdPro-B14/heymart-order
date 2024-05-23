package id.ac.ui.cs.advprog.heymartorder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Data
@Entity
@Table(name = "keranjangitem")
@Getter
public class KeranjangItem {
    @Id
    @Column(name = "productId")
    private String productId;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    @JsonIgnoreProperties(value = {"keranjangitem", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private KeranjangBelanja keranjangbelanja;


    public static KeranjangItemBuilder getBuilder() {
        return new KeranjangItemBuilder();
    }
}

