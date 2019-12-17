package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.demo.config.BeanConfig;


public class AwsS3GetFileList {

	/*
	 * @Autowired BeanConfig config;
	 * 
	 * static String sufix = "//";
	 * 
	 * public boolean getDocumentList() { boolean flag = false; String bucketName =
	 * config.getBucketName(); try { // maxKeys is set to 2 to demonstrate the use
	 * of // ListObjectsV2Result.getNextContinuationToken() ListObjectsV2Request req
	 * = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(2);
	 * ListObjectsV2Result result;
	 * 
	 * do { result = //config.awsS3Client().listObjectsV2(req);
	 * 
	 * for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
	 * System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(),
	 * objectSummary.getSize()); } // If there are more than maxKeys keys in the
	 * bucket, get a continuation token // and list the next objects. flag = true; }
	 * while (result.isTruncated()); } catch (AmazonServiceException e) { // The
	 * call was transmitted successfully, but Amazon S3 couldn't process // it, so
	 * it returned an error response. flag = false; e.printStackTrace(); } catch
	 * (SdkClientException e) { // Amazon S3 couldn't be contacted for a response,
	 * or the client // couldn't parse the response from Amazon S3. flag = false;
	 * e.printStackTrace(); } return flag; }
	 */
}
