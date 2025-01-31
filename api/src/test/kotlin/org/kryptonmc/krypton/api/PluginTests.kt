/*
 * This file is part of the Krypton API, licensed under the MIT license.
 *
 * Copyright (C) 2021 KryptonMC and the contributors to the Krypton project.
 *
 * This project is licensed under the terms of the MIT license.
 * For more details, please reference the LICENSE file in the api top-level directory.
 */
package org.kryptonmc.krypton.api

import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class PluginTests {

    @Test
    fun `test register commands calls correctly`() {
        plugin.registerCommand(command)
        plugin.registerCommands(command)
        plugin.registerCommands(setOf(command))
        verify { commandManagerMock.register(command) }
        verify { commandManagerMock.register(*arrayOf(command)) }
        verify { commandManagerMock.register(setOf(command)) }
    }

    @Test
    fun `test register listener calls correctly`() {
        val event = Any()
        plugin.registerListener(event)
        verify { eventBusMock.register(event) }
    }

    @Test
    fun `test plugin description file values`() {
        assertEquals(description.name, "Test")
        assertEquals(description.main, "org.kryptonmc.test.TestPlugin")
        assertEquals(description.version, "1.1.1")
        assertEquals(description.description, "I am a test plugin!")
        assertEquals(description.authors, listOf("BomBardyGamer"))
        assertEquals(description.dependencies, listOf("EventTester"))
    }
}
