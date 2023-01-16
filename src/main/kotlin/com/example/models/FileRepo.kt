package com.example.models

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

class FileRepo {
    private val path = Path("./files/actions_v2.json")
    var listActions: MutableList<Action>
    init {
        listActions = readFileCoded(path)
    }

    fun writeFile() {
        val codedText = listToJsonData(this.listActions)
        writeFileCoded(this.path, codedText)
    }

    private fun listToJsonData(listActions: MutableList<Action>): String {
        val serializer = Json { ignoreUnknownKeys = true }
        return serializer.encodeToString<MutableList<Action>>(listActions)
    }
    private fun readFileCoded(path: Path): MutableList<Action> {
        return if (checkPath(path)) {
            val text = path.readText()
            val serializer = Json { ignoreUnknownKeys = true }
            serializer.decodeFromString<MutableList<Action>>(text)
        }else {
            createPath(path = path)
            mutableListOf()
        }
    }

    fun checkPath(path: Path) = path.toFile().exists()

    fun createPath(path: Path, jsonData: String = "[]"): Boolean  {
        return try {
            Files.createDirectories(path.parent)
            path.writeText(jsonData, options = arrayOf(StandardOpenOption.CREATE))
            true
        }catch(e: Exception) {
            false
        }

    }

    private fun writeFileCoded(path: Path, jsonData: String) {
        path.writeText(jsonData, options = arrayOf(StandardOpenOption.WRITE))
    }


}