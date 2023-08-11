package com.multicampus.springex.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("oracle")
public class SampleDAO_Oracle_Impl implements SampleDAO {
}
