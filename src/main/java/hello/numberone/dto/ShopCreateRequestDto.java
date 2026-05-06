package hello.numberone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopCreateRequestDto {
    private String shopName;
    private String productName;
}