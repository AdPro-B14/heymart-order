package id.ac.ui.cs.advprog.heymartorder.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TopUpBalanceRequest {
    private BigDecimal amount;
}
