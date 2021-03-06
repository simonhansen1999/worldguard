/*
 * WorldGuard, a suite of tools for Minecraft
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldGuard team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldguard.internal.cause;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods to handle {@code Cause}s.
 */
public final class Causes {

    private Causes() {
    }

    /**
     * Get the first player that is in the list of causes.
     *
     * @param causes a list of causes, where the originating causes are at the beginning
     * @return the player or null
     */
    @Nullable
    public static Player getInvolvedPlayer(List<? extends Cause<?>> causes) {
        for (Cause cause : causes) {
            if (cause instanceof PlayerCause) {
                return ((PlayerCause) cause).get();
            }
        }

        return null;
    }

    /**
     * Create a list of causes from a list of objects representing causes.
     *
     * @param cause an array of causes, where the originating causes are at the beginning
     * @return a list of causes, where the originating causes are at the beginning
     */
    public static List<? extends Cause<?>> create(Object ... cause) {
        List<Cause<?>> causes = new ArrayList<Cause<?>>(cause.length);
        for (Object o : cause) {
            if (o instanceof Player) {
                causes.add(new PlayerCause((Player) o));
            } else {
                causes.add(new UnknownCause(o));
            }
        }
        return causes;
    }

}
