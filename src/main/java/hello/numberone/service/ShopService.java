package hello.numberone.service;

import hello.numberone.dto.ShopCreateRequestDto;
import hello.numberone.entity.Product;
import hello.numberone.entity.Shop;
import hello.numberone.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    @Transactional
    public void createShopWithProduct(ShopCreateRequestDto request) {
        Shop shop = new Shop();
        shop.setShopName(request.getShopName());


        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setShop(shop);

        shop.setProduct(product);
        shopRepository.save(shop);
    }

    @Transactional
    public void deleteShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상점이 존재하지 않습니다."));

        shopRepository.delete(shop);
    }
}