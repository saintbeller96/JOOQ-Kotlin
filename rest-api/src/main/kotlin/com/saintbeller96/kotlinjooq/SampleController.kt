package com.saintbeller96.kotlinjooq

import com.saintbeller96.kotlinjooq.sample.Sample
import com.saintbeller96.kotlinjooq.sample.SampleRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    val sampleRepository: SampleRepository
) {

    @GetMapping
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/samples")
    fun getSamples(): List<Sample> {
        return sampleRepository.findAll()
    }
}
