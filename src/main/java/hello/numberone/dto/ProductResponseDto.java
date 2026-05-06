package hello.numberone.dto;

import hello.numberone.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private String productName;

    public ProductResponseDto(Product product) {
        this.productName = product.getProductName();
    }
}