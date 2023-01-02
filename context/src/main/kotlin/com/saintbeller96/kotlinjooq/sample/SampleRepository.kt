package com.saintbeller96.kotlinjooq.sample

import com.saintbeller96.kotlinjooq.public_.tables.Sample.SAMPLE
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

interface SampleRepository {
    fun findById(id: String): Sample?
    fun findAll(): List<Sample>
}

@Repository
internal class SampleJooqRepository(
    private val dslContext: DSLContext
) : SampleRepository {
    override fun findById(id: String): Sample? {
        return dslContext.select()
            .from(SAMPLE)
            .where(SAMPLE.ID.eq(id))
            .fetchOneInto(Sample::class.java)
    }

    override fun findAll(): List<Sample> {
        return dslContext.select()
            .from(SAMPLE)
            .fetchInto(Sample::class.java)
    }
}




