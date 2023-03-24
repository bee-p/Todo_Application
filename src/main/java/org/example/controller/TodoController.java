package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.TodoModel;
import org.example.model.TodoRequest;
import org.example.model.TodoResponse;

import org.example.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j              // 로그 표시를 위함
@CrossOrigin        // cors 이슈를 막기 위함
@AllArgsConstructor
@RestController     // controller라는 것을 알려주기 위함
@RequestMapping("/")    // base url 설정
public class TodoController {

    private final TodoService service;

    @PostMapping
    // 추가 역할
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        log.info("CREATE");

        if (ObjectUtils.isEmpty(request.getTitle()))
            return ResponseEntity.badRequest().build();

        // order 값이 없을 시 기본값 넣음
        if (ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);

        // completed 값이 없을 시 기본값 넣음
        if (ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);

        TodoModel result = this.service.add(request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    // 한 개만 읽어오는 역할
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        log.info("READ ONE");

        TodoModel result = this.service.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping
    // 전체를 읽어오는 역할
    public ResponseEntity<List<TodoResponse>> readAll() {
        log.info("READ ALL");

        List<TodoModel> list = this.service.searchAll();
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                .collect(Collectors.toList());    // TodoResponse 리스트로 변환

        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    // 수정 역할
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        log.info("UPDATE");

        TodoModel result = this.service.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    // 한 개만 삭제하는 역할
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        log.info("DELETE ONE");

        this.service.deleteById(id);

        // Delete는 void로 리턴값이 따로 없기 때문에 매개변수를 보내지는 X
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    // 모두 삭제하는 역할
    public ResponseEntity<?> deleteAll() {
        log.info("DELETE ALL");

        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
