package com.crud.springcrud.dtos;

import com.crud.springcrud.models.AddressModel;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record EmployeeRecordDto(@NotBlank String firstName, @NotBlank String lastName, AddressModel address, Date birthDate) {
}
