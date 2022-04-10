package ru.otus.spring.integration.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.integration.service.ReportService;
import static org.springframework.integration.scheduling.PollerMetadata.DEFAULT_POLLER;


@RequiredArgsConstructor
@IntegrationComponentScan
@Configuration
@EnableIntegration
public class IntegrationConfig {

    private final ReportService reportService;

    @Bean
    public QueueChannel examChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel reportChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow examFlow() {
        return IntegrationFlows.from(examChannel())
                .split()
                .handle(reportService, "generateReport")
                .aggregate()
                .channel(reportChannel())
                .get();
    }
}
