package co.edu.unicauca.asae.backend.configuracionSeguridad.fachadaServices.DTO.response;

public class MessageResponseDTO {
  private String message;

  public MessageResponseDTO(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
