package com.ntu.sctp.group1.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnrolEditDto {

    private  int id;
    private String dayOfProgram;
    private String timeOfProgram;

}
