package com.ecommerce.app.inventory.dynomodb;

import com.ecommerce.app.inventory.config.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;

@Configuration
public class DynamoDbConfiguration {

    final Properties properties;

    public DynamoDbConfiguration(Properties properties) {
        this.properties = properties;
    }



    @Bean
    public DynamoDbClient dynamoDbClient() {

        DynamoDbClientBuilder builder =  DynamoDbClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(properties.getAccesskey(), properties.getSecretkey())))
                .region(Region.of(properties.getRegion()));

        if (!StringUtils.isEmpty(properties.getEndpoint())){
            builder.endpointOverride(URI.create(properties.getEndpoint()));
        }

        return builder.build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

/*    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(properties.getRegion())
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        properties.getAccesskey(),
                                        properties.getSecretkey()
                                )
                        )
                )
                .build();
    }

*/
}

