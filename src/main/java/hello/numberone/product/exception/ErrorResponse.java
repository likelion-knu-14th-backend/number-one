package hello.numberone.product.exception;

public record ErrorResponse(
        String code,
        String message
) {
}