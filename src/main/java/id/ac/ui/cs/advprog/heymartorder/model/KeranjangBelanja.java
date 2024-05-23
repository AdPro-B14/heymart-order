package id.ac.ui.cs.advprog.heymartorder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangItem;

@Data
@Entity
@Table(name = "keranjangbelanja")
@Getter
public class KeranjangBelanja {
    @Id
    @Setter
    private Long id;

    @Setter
    private Long supermarketId;

    @OneToMany(mappedBy = "keranjangbelanja", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"keranjangbelanja", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @JsonManagedReference
    private List<KeranjangItem> listKeranjangItem = new ArrayList<>();

    public static KeranjangBelanjaBuilder getBuilder() {
        return new KeranjangBelanjaBuilder();
    }
}
