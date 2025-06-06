package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {
    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^\\+[0-9]{11,13}$", message = "Номер телефона должен начинаться с '+' и содержать от 11 до 13 цифр")
    private String phoneNumber;

    @Pattern(regexp = "^[0-9]{4}$")
    private String clubCard;

    @Future(message = "Дата события должна быть в будущем")
    private LocalDate cardValidUntil;

}
// END
