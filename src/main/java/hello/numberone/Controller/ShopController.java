package hello.numberone.Controller;

import hello.numberone.dto.ShopCreateRequestDto;
import hello.numberone.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/shops")
    public void createShop(@Valid @RequestBody ShopCreateRequestDto requestDto) { // ★ @Valid 추가
        shopService.createShopWithProduct(requestDto);
    }

    @DeleteMapping("/shops/{shopId}")
    public void deleteShop(@PathVariable("shopId") Long shopId) {
        shopService.deleteShop(shopId);
    }
}