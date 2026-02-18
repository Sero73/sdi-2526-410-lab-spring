package com.uniovi.sdi.validators;

import com.uniovi.sdi.entities.Department;
import com.uniovi.sdi.entities.User;
import com.uniovi.sdi.services.DepartmentsService;
import com.uniovi.sdi.services.UsersService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddDepartmentValidator implements Validator {
    private final DepartmentsService departmentsService;

    public AddDepartmentValidator(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faculty", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Error.empty");

        if (department.getCode().length()!=9 ) {
        errors.rejectValue("code", "Error.department.code.length");
        }

        if(!Character.isLetter(department.getCode().toCharArray()[department.getCode().length()-1])){
            errors.rejectValue("code", "Error.department.code.lastCharacter");
        }
        if (departmentsService.getDepartmentByCode(department.getCode()) != null) {
            errors.rejectValue("code", "Error.department.code.duplicate");
        }

        if (department.getCode().startsWith(" ") ||
                department.getCode().endsWith(" ")) {
            errors.rejectValue("code", "Error.department.whitespace");
        }

        if (department.getName().startsWith(" ") ||
                department.getName().endsWith(" ")) {
            errors.rejectValue("name", "Error.department.whitespace");
        }

        if (department.getFaculty().startsWith(" ") ||
                department.getFaculty().endsWith(" ")) {
            errors.rejectValue("faculty", "Error.department.whitespace");
        }

        if (department.getPhone().startsWith(" ") ||
                department.getPhone().endsWith(" ")) {
            errors.rejectValue("phone", "Error.department.whitespace");
        }
    }
}
