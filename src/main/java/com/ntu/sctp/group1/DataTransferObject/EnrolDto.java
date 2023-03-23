package com.ntu.sctp.group1.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnrolDto {

    private String dayOfProgram;
    private String timeOfProgram;
    private Integer program_id;

}
