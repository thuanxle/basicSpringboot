package com.kms.baseSpringBoot.batch;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.kms.baseSpringBoot.models.User;

@Component
public class UserWriter extends FlatFileItemWriter<User> {

	public UserWriter() {
		setResource(new FileSystemResource("src/main/resources/users.csv"));
		setLineAggregator(getDelimitedLineAggregator());
	}
	
	public DelimitedLineAggregator<User> getDelimitedLineAggregator() {
		BeanWrapperFieldExtractor<User> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<User>();
		beanWrapperFieldExtractor.setNames(new String[] {"id", "username", "email"});
		
		DelimitedLineAggregator<User> delimitedLineAggregator = new DelimitedLineAggregator<User>();
		delimitedLineAggregator.setDelimiter(",");
		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
		return delimitedLineAggregator;
		
	}
}