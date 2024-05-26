package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "keranjanglog")
@Getter
public class KeranjangLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String action;
    private String productId;
    private LocalDateTime timestamp;

    public KeranjangLog() {
    }

    public KeranjangLog(Long userId, String action, String productId) {
        this.userId = userId;
        this.action = action;
        this.productId = productId;
        this.timestamp = LocalDateTime.now();
    }
}
