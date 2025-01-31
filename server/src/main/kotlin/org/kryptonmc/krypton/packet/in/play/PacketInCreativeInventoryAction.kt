/*
 * This file is part of the Krypton project, licensed under the GNU General Public License v3.0
 *
 * Copyright (C) 2021 KryptonMC and the contributors of the Krypton project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.kryptonmc.krypton.packet.`in`.play

import io.netty.buffer.ByteBuf
import org.kryptonmc.krypton.entity.Slot
import org.kryptonmc.krypton.packet.state.PlayPacket
import org.kryptonmc.krypton.util.readNBTCompound
import org.kryptonmc.krypton.util.readVarInt

/**
 * Sent by the client to indicate that they have performed an action in the creative inventory.
 *
 * The creative inventory works very different to the survival inventory, mainly that in vanilla,
 * the Notchian server deletes an item from a player's inventory, and when you place an item back
 * into your inventory, the server recreates the item.
 */
class PacketInCreativeInventoryAction : PlayPacket(0x28) {

    /**
     * The inventory slot that the player clicked
     */
    var slot: Short = 0; private set

    /**
     * The item that was clicked
     */
    lateinit var clickedItem: Slot private set

    override fun read(buf: ByteBuf) {
        slot = buf.readShort()

        if (!buf.readBoolean()) {
            clickedItem = Slot(false)
            return
        }
        clickedItem = Slot(true, buf.readVarInt(), buf.readByte(), buf.readNBTCompound())
    }
}
