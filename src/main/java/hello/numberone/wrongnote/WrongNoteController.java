package hello.numberone.wrongnote;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wrong-notes")
public class WrongNoteController {

    private final List<WrongNoteResponseDto> noteStore = new ArrayList<>();
    private Long nextId = 1L;

    // POST - 오답노트 등록
    @PostMapping
    public WrongNoteResponseDto addNote(@RequestBody WrongNoteCreateRequestDto request) {
        WrongNoteResponseDto note = new WrongNoteResponseDto(
                nextId++,
                request.getSubject(),
                request.getQuestion(),
                request.getMyAnswer(),
                request.getCorrectAnswer(),
                request.getMemo()
        );
        noteStore.add(note);
        return note;
    }

    // GET - 전체 조회
    @GetMapping
    public List<WrongNoteResponseDto> getAllNotes() {
        return noteStore;
    }

    // GET - 단건 조회
    @GetMapping("/{id}")
    public WrongNoteResponseDto getNote(@PathVariable Long id) {
        for (WrongNoteResponseDto note : noteStore) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    // PUT - 오답노트 수정
    @PutMapping("/{id}")
    public WrongNoteResponseDto updateNote(
            @PathVariable Long id,
            @RequestBody WrongNoteCreateRequestDto request
    ) {
        for (int i = 0; i < noteStore.size(); i++) {
            WrongNoteResponseDto note = noteStore.get(i);

            if (note.getId().equals(id)) {
                WrongNoteResponseDto updatedNote = new WrongNoteResponseDto(
                        id,
                        request.getSubject(),
                        request.getQuestion(),
                        request.getMyAnswer(),
                        request.getCorrectAnswer(),
                        request.getMemo()
                );
                noteStore.set(i, updatedNote);
                return updatedNote;
            }
        }
        return null;
    }

    // DELETE - 단건 삭제
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteStore.removeIf(note -> note.getId().equals(id));
    }

    // DELETE - 과목별 전체 삭제 (부분 삭제)
    @DeleteMapping("/subject/{subject}")
    public void deleteNotesBySubject(@PathVariable String subject) {
        noteStore.removeIf(note -> note.getSubject().equals(subject));
    }
}
