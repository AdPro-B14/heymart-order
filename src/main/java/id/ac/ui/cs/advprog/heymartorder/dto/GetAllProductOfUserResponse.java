package id.ac.ui.cs.advprog.heymartorder.dto;

import lombok.Builder;

import java.util.HashMap;

@Builder
public class GetAllProductOfUserResponse {
    public HashMap<String, Integer> productMap;
}
