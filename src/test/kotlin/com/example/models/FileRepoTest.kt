package com.example.models

import org.junit.Test

import org.junit.Assert.*
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.exists

class FileRepoTest {
    private val fileRepo = FileRepo()
    private val path = Path("/files/actions_v2.json")
    @Test
    fun checkPathTest() {
        assertTrue(!fileRepo.checkPath(path))
    }

    @Test
    fun createPathTest() {
        Files.createDirectories(path.parent)
        assertTrue(fileRepo.createPath(path))
    }
}