package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;

@Data
@Entity
@Table(name = "keranjangbelanja")
@Getter
public class KeranjangBelanja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private Long supermarketId;

    @Setter @ElementCollection
    private HashMap<String, Integer> productMap;

    public static KeranjangBelanjaBuilder getBuilder() {
        return new KeranjangBelanjaBuilder();
    }
}
