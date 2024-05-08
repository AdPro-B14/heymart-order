package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "customer_balance")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Long customerId;

    BigDecimal amount;
}
