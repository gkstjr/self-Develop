package com.selfdev.board;

import lombok.Data;

@Data
public class WriteForm {


    private String type;

    private String time;

    private String daily;

    private String executionAt;

    private String title;

    private String content;
}
