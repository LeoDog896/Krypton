/*
 * This file is part of the Krypton API, licensed under the MIT license.
 *
 * Copyright (C) 2021 KryptonMC and the contributors to the Krypton project.
 *
 * This project is licensed under the terms of the MIT license.
 * For more details, please reference the LICENSE file in the api top-level directory.
 */
package org.kryptonmc.krypton.api.service

import org.kryptonmc.krypton.api.plugin.Plugin

/**
 * Represents a provider of a service of type [T]
 *
 * Plugins can use these to provide classes to other plugins in a way that
 * allows them to not need to know who they are actually providing the service
 * to (if anyone), which is a neat abstraction layer
 */
interface ServiceProvider<T> {

    /**
     * The plugin that provided this service
     */
    val plugin: Plugin

    /**
     * The class of the service being provided
     */
    val serviceClass: Class<T>

    /**
     * The service provided
     */
    val service: T
}
