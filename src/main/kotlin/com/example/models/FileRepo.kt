package com.example.models

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

class FileRepo {
    private val path = Path("files/actions.json")
    var listActions: MutableList<Action> = mutableListOf()
    init {
        listActions = readFile(path)
    }

    private fun listToJsonData(listActions: MutableList<Action>): String {
        val serializer = Json { ignoreUnknownKeys = true }
        return serializer.encodeToString<MutableList<Action>>(listActions)
    }
    private fun readFile(path: Path): MutableList<Action> {
        return if (checkPath(path)) {
            //decode
            val text = path.readText()
            val serializer = Json { ignoreUnknownKeys = true }
            serializer.decodeFromString<MutableList<Action>>(text)
        }else {
            createPath(path = path)
            mutableListOf()
        }
    }

    private fun checkPath(path: Path) = path.toFile().exists()

    private fun createPath(path: Path, jsonData: String = "") {
        path.writeText(jsonData, options = arrayOf(StandardOpenOption.CREATE))
    }

    private fun writeInPath(path: Path, jsonData: String) {
        path.writeText(jsonData, options = arrayOf(StandardOpenOption.WRITE))
    }


}