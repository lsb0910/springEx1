package com.multicampus.springex.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("maria")
public class SampleDAO_Maria_Impl implements SampleDAO {
}
