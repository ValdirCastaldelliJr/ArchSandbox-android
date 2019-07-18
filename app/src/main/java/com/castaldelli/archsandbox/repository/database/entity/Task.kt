package com.castaldelli.archsandbox.repository.database.entity

import androidx.room.*


@Entity(indices = [Index(value = ["description"]) ])
class Task (
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    @ColumnInfo(name = "description")
    var description : String
) {

    @Ignore
    var selected : Boolean = false

    override fun toString(): String = "$id - $description"


}