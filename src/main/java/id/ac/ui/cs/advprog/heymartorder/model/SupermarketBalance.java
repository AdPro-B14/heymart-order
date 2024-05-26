package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "supermarket_balance")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SupermarketBalance extends Balance {
    Long supermarketId;
}