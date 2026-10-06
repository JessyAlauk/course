package com.educandoweb.course.service;

import com.educandoweb.course.dto.UserPostRequestBodyDTO;
import com.educandoweb.course.dto.UserPutRequestBodyDTO;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.mapper.UserMapper;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.service.exceptions.DataBaseException;
import com.educandoweb.course.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById (Long id){
        var obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(UserPostRequestBodyDTO obj){
        var user = UserMapper.toSave(obj);
        return userRepository.save(user);
    }

    public void delete(Long id){
        try{

        userRepository.findById(id)
                .ifPresentOrElse(s -> userRepository.deleteById(s.getId()),
                        () -> {
                            throw new ResourceNotFoundException(id);
                        });
        } catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }catch (Exception e){
            throw new ResourceNotFoundException(id);
        }

    }

    public User update(Long id, UserPutRequestBodyDTO obj){
        try {
            var oldUser = userRepository.getReferenceById(id);
            var newUser = new UserPutRequestBodyDTO(id, obj.name(), obj.email(), obj.phone(), oldUser.getPassword());
            var updatedUser = UserMapper.toSave(newUser);
            return userRepository.save(updatedUser);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            throw new ResourceNotFoundException(id);
        }
    }
}
