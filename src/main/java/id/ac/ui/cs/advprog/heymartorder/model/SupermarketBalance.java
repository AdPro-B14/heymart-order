package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "supermarket_balance")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SupermarketBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Long supermarketId;

    BigDecimal amount;
}
