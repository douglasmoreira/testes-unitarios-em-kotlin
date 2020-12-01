package com.douglas.TestesUnitariosKotlin

import com.douglas.TestesUnitariosKotlin.entidades.Usuario
import org.junit.Assert.*
import org.junit.Test

class AssertTest {

    @Test
    fun test() {
        assertTrue(true)
        assertFalse(false)

        assertEquals(1, 1)
        assertEquals(0.51, 0.51, 0.01)
        assertEquals(Math.PI, 3.14, 0.01)

        assertEquals("bola", "bola")
        assertNotEquals("bola", "casa")
        assertTrue("bola".equals("Bola", ignoreCase = true))
        assertTrue("bola".startsWith("bo"))

        val usuario1 = Usuario("Usuario1")
        val usuario2 = Usuario("Usuario1")
        val usuario3 = usuario2
        val usuario4 = null

        assertEquals(usuario1, usuario2)// compara objeto
        assertSame(usuario3, usuario2)//compara instancia
        assertNotSame(usuario1, usuario2)//compara instancia
        assertNull(usuario4)
        assertNotNull(usuario1)
    }
}