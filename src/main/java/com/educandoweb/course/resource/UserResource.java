package com.educandoweb.course.resource;

import com.educandoweb.course.dto.UserPostRequestBodyDTO;
import com.educandoweb.course.dto.UserPutRequestBodyDTO;
import com.educandoweb.course.dto.UserResponseDTO;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.mapper.UserMapper;
import com.educandoweb.course.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        var list = service.findAll();
        var users = list.stream().map(UserMapper::toResponse).toList();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        var obj = service.findById(id);
        return ResponseEntity.ok().body(UserMapper.toResponse(obj));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody UserPostRequestBodyDTO obj){
        var body = service.insert(obj);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(body.getId()).toUri();
//        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
        return ResponseEntity.created(uri).body(UserMapper.toResponse(body));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserPutRequestBodyDTO obj){
        var body = service.update(id,obj);
        return ResponseEntity.ok().body(UserMapper.toResponse(body));
    }
}
