package hello.numberone.exception;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException() {
        super("해당 상점이 존재하지 않습니다.");
    }
}