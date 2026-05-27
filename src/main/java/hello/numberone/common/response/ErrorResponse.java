package hello.numberone.common.response;

public record ErrorResponse(
        String code,
        String message
) {
}