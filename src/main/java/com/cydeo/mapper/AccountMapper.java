package com.cydeo.mapper;

import com.cydeo.dto.AccountDTO;
import com.cydeo.entity.Account;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Account convertToEntity(AccountDTO dto){

        return modelMapper.map(dto,Account.class);
    }

    public AccountDTO convertToDTO(Account entity){

        return modelMapper.map(entity,AccountDTO.class);
    }
}
