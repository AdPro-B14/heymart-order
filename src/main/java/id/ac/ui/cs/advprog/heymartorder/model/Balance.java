package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@MappedSuperclass
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    BigDecimal amount;
}