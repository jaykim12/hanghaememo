package com.sparta.hanghaememo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private String username;
    private String password;
    private String title;
    private String contents;
}
