package com.currency.api.service;

import com.currency.api.parser.Instrument;
import com.currency.api.parser.Parser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author herasimau on 28.08.2016.
 */


@Service
public class CurrencyService implements ApplicationListener<BrokerAvailabilityEvent> {

    @Value("${consume}")
    private String type;

    @Autowired
    private final MessageSendingOperations<String> messagingTemplate;


    private AtomicBoolean brokerAvailable = new AtomicBoolean();




    @Autowired
    public CurrencyService(MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        this.brokerAvailable.set(event.isBrokerAvailable());

    }

    @Scheduled(cron = "${aud.usd}")
    public void sendAudUsd() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/aud.usd", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.AUD_USD).getBytes(),getHeaders()));

    }

    @Scheduled(cron = "${eur.usd}")
    public void sendEurUsd() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/eur.usd", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.EUR_USD).getBytes(),getHeaders()));

    }

    @Scheduled(cron = "${eur.gbp}")
    public void sendEurGbp() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/eur.usd", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.EUR_GBP).getBytes(),getHeaders()));

    }

    @Scheduled(cron = "${eur.jpy}")
    public void sendEurJpy() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/eur.jpy", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.EUR_JPY).getBytes(),getHeaders()));

    }
    @Scheduled(cron = "${eur.chf}")
    public void sendEurChf() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/eur.chf", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.EUR_CHF).getBytes(),getHeaders()));

    }

    @Scheduled(cron = "${gbp.usd}")
    public void sendGbpUsd() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/gbp.usd", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.GBP_USD).getBytes(),getHeaders()));

    }

    @Scheduled(cron = "${gbp.jpy}")
    public void sendGbpJpy() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/gbp.jpy", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.GBP_JPY).getBytes(),getHeaders()));

    }
    @Scheduled(cron = "${usd.jpy}")
    public void sendUsdJpy() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/usd.jpy", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.USD_JPY).getBytes(),getHeaders()));

    }

    @Scheduled(cron = "${usd.chf}")
    public void sendUsdChf() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/usd.chf", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.USD_CHF).getBytes(),getHeaders()));

    }

    @Scheduled(cron = "${usd.cad}")
    public void sendUsdCad() throws UnsupportedEncodingException, JsonProcessingException {

        messagingTemplate.send("/topic/usd.cad", MessageBuilder.createMessage(new Parser(type).getByInstrument(Instrument.USD_CAD).getBytes(),getHeaders()));

    }

    public MessageHeaders getHeaders(){
        Map<String,Object> headers = new HashMap<>();
        headers.put("content-type", Collections.singletonList("application/json"));
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        return messageHeaders;
    }




}