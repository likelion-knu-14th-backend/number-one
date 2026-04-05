package hello.numberone;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    private final List<ToDoListResponseDto> toDoList = new ArrayList<>();

    //POST
    @PostMapping
    public ToDoListResponseDto createTask(@RequestBody ToDoListRequestDto request){

        ToDoListResponseDto task = new ToDoListResponseDto(
                request.getId(),
                request.getTitle(),
                request.getIsCompleted(),
                request.getDueDate()
        );
        toDoList.add(task);
        return task;
    }

    //GET
    @GetMapping
    public List<ToDoListResponseDto> getTasks(){
        return toDoList;
    }

    //GET
    @GetMapping("/{id}")
    public ToDoListResponseDto getTask(@PathVariable Long id){
        for (ToDoListResponseDto task : toDoList){
            if (task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

    //PUT
    @PutMapping("/{id}")
    public ToDoListResponseDto updateTask(
            @PathVariable Long id,
            @RequestBody ToDoListRequestDto request
    ){
        for (int i =0; i < toDoList.size(); i++){
            ToDoListResponseDto task = toDoList.get(i);

            if(task.getId().equals(id)){
                ToDoListResponseDto updatedTask = new ToDoListResponseDto(
                        request.getId(),
                        request.getTitle(),
                        request.getIsCompleted(),
                        request.getDueDate()
                );

                toDoList.set(i, updatedTask);
                return updatedTask;
            }
        }
        return null;
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        for (int i =0; i < toDoList.size(); i++){
            ToDoListResponseDto task = toDoList.get(i);

            if(task.getId().equals(id)){
                toDoList.remove(i);
            }
        }
    }

}
