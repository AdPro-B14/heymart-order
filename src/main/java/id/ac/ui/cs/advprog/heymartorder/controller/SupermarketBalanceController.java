package id.ac.ui.cs.advprog.heymartorder.controller;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.service.SupermarketBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supermarket-balance")
@RequiredArgsConstructor
public class SupermarketBalanceController {
}
