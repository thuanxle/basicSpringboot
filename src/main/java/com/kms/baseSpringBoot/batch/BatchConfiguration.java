package com.kms.baseSpringBoot.batch;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;

import com.kms.baseSpringBoot.models.User;
import com.kms.baseSpringBoot.repository.UserRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

 @Autowired
 public JobBuilderFactory jobBuilderFactory;
 
 @Autowired
 public StepBuilderFactory stepBuilderFactory;
 
 @Autowired
 UserReader userReader;
 
 @Autowired
 UserWriter userWriter;
 

 @Bean
 public UserItemProcessor processor(){
  return new UserItemProcessor();
 }
 
 @Bean
 public Step step1() {
  return stepBuilderFactory.get("step1").<User, User> chunk(10)
    .reader(userReader)
    .processor(processor())
    .writer(userWriter)
    .build();
 }
 
 @Bean
 public Job exportUserJob() {
  return jobBuilderFactory.get("exportUserJob")
    .incrementer(new RunIdIncrementer())
    .flow(step1())
    .end()
    .build();
 }
 
}