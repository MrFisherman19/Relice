package com.mrfisherman.relice.Service.Reports.Charts;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ChartBuilder {

    private static final String CHART_API_URL = "https://image-charts.com/chart?";

    private static final String DATA_FORMAT_TYPE = "t:";

    public static byte[] getChartImageFromApi(Chart chart) {
        HttpHeaders headers = new HttpHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CHART_API_URL)
                .queryParam("cht", chart.getChartType().getType())
                .queryParam("chd", DATA_FORMAT_TYPE + chart.getData().stream().map(Object::toString).collect(Collectors.joining(",")))
                .queryParam("chdl", String.join("|", chart.getLabels()))
                .queryParam("chs", chart.getWidth() + "x" + chart.getHeight());

        HttpEntity<?> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> exchange = restTemplate.exchange(builder.build().encode().toUri(),
                HttpMethod.GET,
                request,
                byte[].class);

        return exchange.getBody();
    }



}
