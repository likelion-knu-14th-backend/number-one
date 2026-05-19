package hello.numberone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopCreateRequestDto {
    @NotBlank(message = "상점 이름은 필수입니다.")
    private String shopName;

    @NotBlank(message = "상품 이름은 필수입니다.")
    private String productName;
}