package br.com.desafio.messaging;

import br.com.desafio.config.AwsConfig;
import br.com.desafio.domain.adapter.PaymentQueue;
import br.com.desafio.domain.model.PaymentItemModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class AwsSqsQueue implements PaymentQueue {

    private AwsConfig awsConfig;

    @Override
    public void sendMessage(PaymentItemModel paymentItemModel) {
        ObjectMapper objectMapper = new ObjectMapper();
        String messageBody = null;
        try {
            messageBody = objectMapper.writeValueAsString(paymentItemModel);
        } catch (JsonProcessingException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info(e.getMessage());
        }
        sendMessage(messageBody);
    }

    private void sendMessage(String message) {
        try {
            SqsClient sqsClient = getSqsClient();

            SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                    .queueUrl(awsConfig.getQueueUrl())
                    .messageBody(message)
                    .delaySeconds(5)
                    .build();

            sqsClient.sendMessage(sendMsgRequest);
            sqsClient.close();

        } catch (SqsException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info(e.awsErrorDetails().errorMessage());
        }
    }

    private SqsClient getSqsClient() {
        Region region = Region.of(awsConfig.getRegion());
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(awsConfig.getAccessKeyId(), awsConfig.getSecretAccessKey());
        return SqsClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }
}