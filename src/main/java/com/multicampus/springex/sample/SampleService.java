package com.multicampus.springex.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor    //생성자 주입방식
public class SampleService {
   /* @Autowired
    private SampleDAO sampleDAO; */

    //생성자 주입방식
    @Qualifier("maria") //사용할 이름 !
    private final SampleDAO sampleDAO;

}
