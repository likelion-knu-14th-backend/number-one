package hello.numberone.wrongnote;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WrongNoteCreateRequestDto {
    private String subject;      // 과목
    private String question;     // 문제 내용
    private String myAnswer;     // 내가 쓴 오답
    private String correctAnswer; // 정답
    private String memo;         // 해설 or 기타 메모
}

