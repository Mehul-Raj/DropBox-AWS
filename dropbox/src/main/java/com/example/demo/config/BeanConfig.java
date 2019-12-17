package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class BeanConfig {

	@Value("${aws.keyId}")
	private String awsKeyId;

	@Value("${aws.accessKey}")
	private String accessKey;

	@Value("${aws.region}")
	private String region;

	@Value("${aws.bucket.name}")
	private String bucketName;

	

	public String getAwsKeyId() {
		return awsKeyId;
	}

	public void setAwsKeyId(String awsKeyId) {
		this.awsKeyId = awsKeyId;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

//	@Bean
	public AmazonS3 awsS3Client() {
		region = "ap-southeast-1";

		System.out.println("region" + region);
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsKeyId, accessKey);
		return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	}

}
