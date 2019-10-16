package com.example.autonyilvantartologic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Min(1)
    private long id;

    @Pattern(regexp = "[A-Z]{3}-[0-9]{3}")
    @Size(max = 7, min = 7)
    private String licensePlateNumber;

}
