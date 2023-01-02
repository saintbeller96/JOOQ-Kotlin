package com.saintbeller96.kotlinjooq;

import com.saintbeller96.kotlinjooq.sample.Sample;
import com.saintbeller96.kotlinjooq.sample.SampleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class MyController {

    private final SampleRepository sampleRepository;

    public MyController(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @GetMapping("/samples")
    public List<Sample> getSamples() {
        return sampleRepository.findAll();
    }
}
