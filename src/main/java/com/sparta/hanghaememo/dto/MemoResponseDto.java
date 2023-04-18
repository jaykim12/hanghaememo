package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class MemoResponseDto {


    private String username;

    private String title;
    private String contents;
    private LocalDateTime modifiedAt;

    public MemoResponseDto(Memo memo){

        this.title = memo.getTitle();
       this.username = memo.getUsername();
       this.contents = memo.getContents();
       this.modifiedAt = memo.getModifiedAt();




    }

}
