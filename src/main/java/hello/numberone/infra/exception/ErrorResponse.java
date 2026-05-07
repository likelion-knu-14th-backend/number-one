package hello.numberone.infra.exception;

public record ErrorResponse (
    String code,
    String message
){
}
