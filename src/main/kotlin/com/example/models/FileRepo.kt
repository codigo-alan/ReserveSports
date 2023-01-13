package com.example.models

import com.example.models.reserve.Reserve
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readText

class FileRepo {
    private val path = Path("files/reserves.json")
    var listReserves: MutableList<Reserve> = mutableListOf()
    init {
        listReserves = readFile(path)
    }
    fun readFile(path: Path): MutableList<Reserve> {
        return if (checkPath(path)) {
            //decode
            val text = path.readText()
            val serializer = Json { ignoreUnknownKeys = true }
            serializer.decodeFromString<MutableList<Reserve>>(text)
        }else {
            mutableListOf()
        }
    }

    private fun checkPath(path: Path) = path.toFile().exists()

   /* private fun listToJsonData(listTasks: MutableList<Task>): String {
        val serializer = Json { ignoreUnknownKeys = true }
        return serializer.encodeToString<List<Task>>(listTasks)
    }




    fun createPath(path: Path, jsonData: String) {
        path.writeText("", options = arrayOf(StandardOpenOption.CREATE))
    }

    fun writeInPath(path: Path, jsonData: String) {
        path.writeText(jsonData, options = arrayOf(StandardOpenOption.WRITE))
    }

    fun appendInPath(path: Path, jsonData: String) { //left verify
        path.writeText(jsonData, options = arrayOf(StandardOpenOption.APPEND))
    }*/


}