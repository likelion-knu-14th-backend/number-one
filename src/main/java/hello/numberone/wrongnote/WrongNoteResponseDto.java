package hello.numberone.wrongnote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WrongNoteResponseDto {
    private Long id;
    private String subject;
    private String question;
    private String myAnswer;
    private String correctAnswer;
    private String memo;
}

