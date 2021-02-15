package me.smartquiz.api.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Context;
import io.micronaut.core.io.Readable;

@ConfigurationProperties("opennlp.model")
@Context
public class OpenNlpModelConfig {

    private Readable sentModelEn;

    public Readable getSentModelEn() {
        return sentModelEn;
    }

    public void setSentModelEn(Readable sentModelEn) {
        this.sentModelEn = sentModelEn;
    }

}
