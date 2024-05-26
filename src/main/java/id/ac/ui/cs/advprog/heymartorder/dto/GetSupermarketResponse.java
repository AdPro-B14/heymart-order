package id.ac.ui.cs.advprog.heymartorder.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetSupermarketResponse {
    private Long id;
    private String name;
    private List<String> managerEmails;
}
