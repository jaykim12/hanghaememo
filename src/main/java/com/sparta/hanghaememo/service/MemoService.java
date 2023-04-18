package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);

        return new MemoResponseDto(memo);
    }
   @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos(){

        List<Memo> memoList =memoRepository.findAllByOrderByModifiedAtDesc();



        return memoList.stream().map(MemoResponseDto::new).collect(Collectors.toList());
    }

    @org.springframework.transaction.annotation.Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );

       if( requestDto.getPassword().equals(memo.getPassword())){
           memo.update(requestDto);
           memoRepository.save(memo);


       }

        MemoResponseDto responseDto = new MemoResponseDto(memo);
        //비밀번호

        return  responseDto;
    }
    @org.springframework.transaction.annotation.Transactional
    public String deleteMemo(Long id,MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );

        if(requestDto.getPassword().equals(memo.getPassword())){

            memoRepository.deleteById(id);
            return "삭제되었습니다";

        }

        return "비밀번호가 틀렸습니다";
    }
}
