package com.understanding.spring.data.spring_data.mapper;

import com.understanding.spring.data.spring_data.entity.UserEnt;
import com.understanding.spring.data.spring_data.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
        public UserEnt toUserEnt(UserDTO dto){
            return new UserEnt(dto.getId(), dto.getName(), dto.getEmail());
        }

    public UserDTO toUserDto(UserEnt ent){
        return new UserDTO(ent.getId(), ent.getName(), ent.getEmail());
    }
}
