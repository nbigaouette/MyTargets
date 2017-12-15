/*
 * Copyright (C) 2017 Florian Dreier
 *
 * This file is part of MyTargets.
 *
 * MyTargets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2
 * as published by the Free Software Foundation.
 *
 * MyTargets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package de.dreier.mytargets.shared.models.db

import android.annotation.SuppressLint
import android.os.Parcelable
import com.raizlabs.android.dbflow.annotation.*
import com.raizlabs.android.dbflow.structure.BaseModel
import de.dreier.mytargets.shared.AppDatabase
import de.dreier.mytargets.shared.models.IIdSettable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Table(database = AppDatabase::class)
data class Shot(
        @Column(name = "_id")
        @PrimaryKey(autoincrement = true)
        override var id: Long? = null,

        // The index of the shot in the containing end
        @Column
        var index: Int = 0,

        @ForeignKey(tableClass = End::class, references = arrayOf(ForeignKeyReference(columnName = "end", columnType = Long::class, foreignKeyColumnName = "_id", referencedGetterName = "getId", referencedSetterName = "setId")), onDelete = ForeignKeyAction.CASCADE)
        var endId: Long? = null,

        @Column
        var x: Float = 0.toFloat(),

        @Column
        var y: Float = 0.toFloat(),

        @Column
        var scoringRing: Int = NOTHING_SELECTED,

        // Is the actual number of the arrow not its index, arrow id or something else
        @Column
        var arrowNumber: String? = null
) : BaseModel(), IIdSettable, Comparable<Shot>, Parcelable {

    constructor(i: Int) : this(index = i)

    override fun compareTo(other: Shot): Int {
        return if (other.scoringRing == scoringRing) {
            0
        } else if (other.scoringRing >= 0 && scoringRing >= 0) {
            scoringRing - other.scoringRing
        } else {
            other.scoringRing - scoringRing
        }
    }

    companion object {
        val NOTHING_SELECTED = -2
        val MISS = -1
    }
}