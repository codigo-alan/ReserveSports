package com.example.models.reserve

import org.junit.Test

import org.junit.Assert.*

class ReserveDaoRepositoryTest { //TODO need implement the connect
    private val reserveDaoRepository = ReserveDaoRepository()
    private val newReserve =
        Reserve(1,"2022-12-12T12:12","2022-12-12T12:12",1,1)


    @Test
    fun verifyReserveTest() {
        val verified = reserveDaoRepository.verifyReserve(newReserve)
        assertEquals(true,verified)
    }
}