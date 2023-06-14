package pl.orange.NextDoorBook.security.payloads;

public record LoginRequest(
        String username,
        String password) {

}
