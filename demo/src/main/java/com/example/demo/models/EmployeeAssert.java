package com.example.demo.models;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class EmployeeAssert extends AbstractAssert<EmployeeAssert, EmployeeDTO> {

    private EmployeeAssert(EmployeeDTO actual) {
        super(actual, EmployeeAssert.class);
    }

    public static EmployeeAssert assertThatEmployee(EmployeeDTO actual) {
        return new EmployeeAssert(actual);
    }

    public EmployeeAssert hasName(String name) {
        isNotNull();

        Assertions.assertThat(actual.getName())
                .overridingErrorMessage("Expected name to be <%s> but was <%s>",
                        name,
                        actual.getName()
                )
                .isEqualTo(name);

        return this;
    }

    public EmployeeAssert hasSalary(double salary) {
        isNotNull();

        Assertions.assertThat(actual.getSalary())
                .overridingErrorMessage("Expected salary to be <%s> but was <%s>",
                        salary,
                        actual.getSalary()
                )
                .isEqualTo(salary);

        return this;
    }

    public EmployeeAssert hasNoId() {
        isNotNull();

        Assertions.assertThat(actual.getId())
                .overridingErrorMessage("Expected id to be <null> but was <%d>",
                        actual.getId()
                )
                .isNull();

        return this;
    }

}
