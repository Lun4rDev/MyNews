package com.hernandez.mickael.mynews.enums

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Mickael Hernandez on 16/01/2018.
 */
class SectionTest {

    /** Tests the Section enum values */
    @Test
    fun test(){
        val sections = arrayOf("arts", "politics", "business", "sports", "entrepreneurs", "travel")
        for(i in 0..sections.size-1){
            val res = (Section.values()[i].serialized == sections[i])
            assertTrue(res)
            assert(Section.values()[i].id == i)
        }
    }
}